package com.nikitakrapo.birthdays.account.info

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.birthdays.account.FirebaseDocuments.BIRTHDAY_FIELD
import com.nikitakrapo.birthdays.account.FirebaseDocuments.USERNAME_FIELD
import com.nikitakrapo.birthdays.account.FirebaseDocuments.USERS_COLLECTION
import kotlinx.coroutines.tasks.await
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format

class FirebaseAccountInfoRepository : AccountInfoRepository {

    private val firebaseFirestore = Firebase.firestore

    override suspend fun getAccountInfo(
        uid: String,
    ): Result<AccountInfo> {
        val documentRef = firebaseFirestore.collection(USERS_COLLECTION).document(uid)
        val documentSnapshot = runCatching { documentRef.get().await() }
        return documentSnapshot.fold(
            onSuccess = {
                it.extractAccountInfo()?.let { info -> Result.success(info) }
                    ?: Result.failure(IllegalStateException("Wrong user data document"))
            },
            onFailure = { Result.failure(it) },
        )
    }

    override suspend fun setAccountInfo(
        uid: String,
        info: AccountInfo,
    ): Result<Unit> {
        val documentRef = firebaseFirestore.collection(USERS_COLLECTION).document(uid)
        return runCatching { documentRef.set(
            mapOf(
                USERNAME_FIELD to info.username,
                BIRTHDAY_FIELD to info.birthday.format(LocalDate.Formats.ISO),
            )
        ).await() }
    }

    private fun DocumentSnapshot.extractAccountInfo(): AccountInfo? {
        val birthday = getString(BIRTHDAY_FIELD)?.let {
            try {
                LocalDate.parse(it, LocalDate.Formats.ISO)
            } catch (e: IllegalArgumentException) {
                null
            }
        } ?: return null
        return AccountInfo(
            username = getString(USERNAME_FIELD) ?: return null,
            birthday = birthday,
        )
    }
}