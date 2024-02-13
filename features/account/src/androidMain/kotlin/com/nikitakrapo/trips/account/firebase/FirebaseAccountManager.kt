package com.nikitakrapo.trips.account.firebase

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.trips.account.Account
import com.nikitakrapo.trips.account.AccountManager
import com.nikitakrapo.trips.account.LoginResult
import com.nikitakrapo.trips.account.RegistrationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

internal class FirebaseAccountManager : AccountManager {

    private val firebaseAuth = Firebase.auth

    private val accountFlow = MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener {
            val user = it.currentUser?.toDomainModel()
            accountFlow.value = user
        }
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return wrapWithLoginResult {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .await()
                .user
                ?.toDomainModel()
        }
    }

    private suspend fun wrapWithLoginResult(
        login: suspend () -> Account?,
    ): LoginResult {
        return try {
            val account = login()
            account?.let {
                LoginResult.Success(it)
            } ?: LoginResult.UnknownError(Exception("Firebase returned null user"))
        } catch (e: Exception) {
            LoginResult.UnknownError(e)
        }
    }

    override suspend fun register(email: String, password: String): RegistrationResult {
        return wrapWithRegistrationResult {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()
                .user
                ?.toDomainModel()
        }
    }

    private suspend fun wrapWithRegistrationResult(
        registration: suspend () -> Account?,
    ): RegistrationResult {
        return try {
            val account = registration()
            account?.let {
                RegistrationResult.Success(it)
            } ?: RegistrationResult.UnknownError(Exception("Firebase returned null user"))
        } catch (e: Exception) {
            RegistrationResult.UnknownError(e)
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    private fun FirebaseUser.toDomainModel(): Account {
        return Account(
            uid = uid,
        )
    }
}
