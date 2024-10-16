package com.nikitakrapo.birthdays.account

import com.nikitakrapo.birthdays.account.models.User
import com.nikitakrapo.birthdays.account.models.toUser
import com.nikitakrapo.birthdays.account.result.LoginResult
import com.nikitakrapo.birthdays.account.result.RegistrationErrorType
import com.nikitakrapo.birthdays.account.result.RegistrationResult
import com.nikitakrapo.birthdays.account.result.UpgradeAccountResult
import com.nikitakrapo.birthdays.utils.coroutines.mapState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.EmailAuthProvider
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import dev.gitlive.firebase.auth.auth
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate

class FirebaseAccountManager : AccountManager {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val auth by lazy { Firebase.auth }

    private val firebaseUser = auth.authStateChanged
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = auth.currentUser,
        )
    override val user: StateFlow<User?> = firebaseUser
        .mapState { it?.toUser() }

    override suspend fun registerAnonymously(): LoginResult {
        return try {
            val result = auth.signInAnonymously()
            val user = result.user?.toUser()
            user?.let { LoginResult.Success(it) } ?: LoginResult.UnknownError(null)
        } catch (e: Exception) {
            LoginResult.UnknownError(e.message)
        }
    }

    override suspend fun upgradeAccount(email: String, password: String): UpgradeAccountResult {
        val currentUser = firebaseUser.value ?: return UpgradeAccountResult.NotLoggedIn
        if (!currentUser.isAnonymous) return UpgradeAccountResult.AlreadyUpgraded

        return try {
            val credential = EmailAuthProvider.credential(email = email, password = password)
            val result = currentUser.linkWithCredential(credential)
            val user = result.user?.toUser()
            user?.let { UpgradeAccountResult.Success(it) } ?: UpgradeAccountResult.UnknownError(null)
        } catch (e: Exception) {
            UpgradeAccountResult.UnknownError(e.message)
        }
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password)
            val user = result.user?.toUser()
            user?.let { LoginResult.Success(it) } ?: LoginResult.UnknownError(null)
        } catch (e: Exception) {
            LoginResult.UnknownError(e.message)
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        birthday: LocalDate,
        password: String
    ): RegistrationResult {
        Napier.e { "username and birthday fields not used for registration" }
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            val user = result.user?.toUser()
            user?.let { RegistrationResult.Success(it) } ?: RegistrationResult.Error(null)
        } catch (e: FirebaseAuthWeakPasswordException) {
            RegistrationResult.Error("Weak password", RegistrationErrorType.WEAK_PASSWORD)
        } catch (e: Exception) {
            RegistrationResult.Error(null)
        }
    }

    override suspend fun logout() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            Napier.e("Error while logging out", e)
        }
    }

    override suspend fun getToken(): String? {
        return try {
            firebaseUser.value?.getIdToken(forceRefresh = false)
        } catch (e: Exception) {
            Napier.e("Error while getting token", e)
            null
        }
    }
}