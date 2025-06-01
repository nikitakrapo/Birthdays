package com.nikitakrapo.birthdays.account

import com.nikitakrapo.birthdays.account.cache.UserCache
import com.nikitakrapo.birthdays.account.data.toUser
import com.nikitakrapo.birthdays.account.models.User
import com.nikitakrapo.birthdays.account.result.LoginResult
import com.nikitakrapo.birthdays.account.result.RegistrationErrorType
import com.nikitakrapo.birthdays.account.result.RegistrationResult
import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.network.result.map
import com.nikitakrapo.birthdays.repositories.user.UserRepository
import com.nikitakrapo.birthdays.utils.coroutines.collectIn
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import dev.gitlive.firebase.auth.auth
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate

class FirebaseAccountManager(
    private val userRepository: UserRepository,
) : AccountManager {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val auth by lazy { Firebase.auth }
    private val userCache by lazy { UserCache() }

    private val firebaseUser = auth.authStateChanged
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = auth.currentUser,
        )

    private val userFlow: MutableStateFlow<User?> = MutableStateFlow(userCache.user)
    override val user: StateFlow<User?> = userFlow.asStateFlow()

    init {
        firebaseUser.collectIn(scope) {
            val userInfoResult = userRepository.getUser().map { it.toUser() }
            when (userInfoResult) {
                is NetworkResult.Success -> userFlow.value = userInfoResult.data
                is NetworkResult.Error -> userFlow.value = null
            }
        }
        userFlow.collectIn(scope) {
            userCache.user = it
        }
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password)
            val userInfoResult = userRepository.getUser().map { it.toUser() }
            when (userInfoResult) {
                is NetworkResult.Success -> {
                    userFlow.value = userInfoResult.data
                    LoginResult.Success(userInfoResult.data)
                }
                is NetworkResult.Error -> LoginResult.UnknownError(userInfoResult.exception.message)
            }
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
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            val userInfoResult = userRepository.createUser(username, birthday).map { it.toUser() }
            when (userInfoResult) {
                is NetworkResult.Success -> {
                    userFlow.value = userInfoResult.data
                    RegistrationResult.Success(userInfoResult.data)
                }
                is NetworkResult.Error -> RegistrationResult.Error(userInfoResult.exception.message)
            }
        } catch (e: FirebaseAuthWeakPasswordException) {
            e.printStackTrace()
            RegistrationResult.Error("Weak password", RegistrationErrorType.WEAK_PASSWORD)
        } catch (e: Exception) {
            e.printStackTrace()
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