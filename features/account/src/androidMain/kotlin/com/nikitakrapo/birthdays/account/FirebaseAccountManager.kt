package com.nikitakrapo.birthdays.account

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.birthdays.account.FirebaseDocuments.BIRTHDAY_FIELD
import com.nikitakrapo.birthdays.account.FirebaseDocuments.USERNAME_FIELD
import com.nikitakrapo.birthdays.account.FirebaseDocuments.USERS_COLLECTION
import com.nikitakrapo.birthdays.account.models.LoginResult
import com.nikitakrapo.birthdays.account.models.RegistrationErrorType
import com.nikitakrapo.birthdays.account.models.RegistrationResult
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format

internal class FirebaseAccountManager : AccountManager {

    private val firebaseAuth = Firebase.auth
    private val firebaseFirestore = Firebase.firestore

    private val accountFlow = MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val firebaseUser = firebaseAuth.signInWithEmailAndPassword(email, password)
                .await()
                .user
                ?: return LoginResult.UnknownError("Firebase returned null user")
            onUserChanged(firebaseUser)
            LoginResult.Success(firebaseUser.toDomainModel())
        } catch (e: Exception) {
            LoginResult.UnknownError(e.message.orEmpty())
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        birthday: LocalDate,
        password: String,
    ): RegistrationResult {
        return try {
            val firebaseUser = firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()
                .user
                ?: return RegistrationResult.Error(
                    message = "Firebase returned null user",
                    type = RegistrationErrorType.UNKNOWN,
                )
            val userDataSetResult = setUserData(
                uid = firebaseUser.uid,
                username = username,
                birthday = birthday,
            )
            userDataSetResult.fold(
                onSuccess = {
                    Napier.d { "User ${firebaseUser.uid} successfully logged in" }
                    onUserChanged(firebaseUser)
                    RegistrationResult.Success(firebaseUser.toDomainModel())
                },
                onFailure = {
                    Napier.e(it) { "Failed to set user data for ${firebaseUser.uid}. Deleting" }
                    firebaseUser.delete().await()
                    RegistrationResult.Error(
                        message = it.message ?: "Failed to set user data",
                        type = RegistrationErrorType.UNKNOWN,
                    )
                },
            )
        // Should be before FirebaseAuthInvalidCredentialsException bc extends it
        } catch (e: FirebaseAuthWeakPasswordException) {
            RegistrationResult.Error(
                message = e.message.orEmpty(),
                type = RegistrationErrorType.WEAK_PASSWORD,
            )
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            RegistrationResult.Error(
                message = e.message.orEmpty(),
                type = RegistrationErrorType.EMAIL_WRONG_FORMAT,
            )
        } catch (e: FirebaseAuthUserCollisionException) {
            RegistrationResult.Error(
                message = e.message.orEmpty(),
                type = RegistrationErrorType.EMAIL_ALREADY_IN_USE,
            )
        } catch (e: FirebaseNetworkException) {
            RegistrationResult.Error(
                message = e.message.orEmpty(),
                type = RegistrationErrorType.NETWORK,
            )
        } catch (e: Exception) {
            Napier.e(e) { "Unknown exception when authenticating" }
            RegistrationResult.Error(
                message = e.message.orEmpty(),
                type = RegistrationErrorType.UNKNOWN,
            )
        }
    }

    private suspend fun setUserData(
        uid: String,
        username: String,
        birthday: LocalDate,
    ): Result<Unit> {
        val params = mapOf(
            USERNAME_FIELD to username,
            BIRTHDAY_FIELD to birthday.format(LocalDate.Formats.ISO),
        )
        return runCatching {
            val userDataDocument = firebaseFirestore.collection(USERS_COLLECTION).document(uid)
            userDataDocument.set(params).await()
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
        onUserChanged(null)
    }

    override suspend fun getToken(): String? {
        return try {
            firebaseAuth.currentUser
                ?.getIdToken(false)
                ?.await()
                ?.token
        } catch (e: Exception) {
            Napier.e("AccountManager.getToken() error", e)
            null
        }
    }

    private fun onUserChanged(firebaseUser: FirebaseUser?) {
        accountFlow.value = firebaseUser?.toDomainModel()
    }

    private fun FirebaseUser.toDomainModel(): Account {
        return Account(
            uid = uid,
            email = email,
        )
    }
}
