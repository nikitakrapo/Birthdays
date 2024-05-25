package com.nikitakrapo.birthdays.account.info

interface AccountInfoRepository {

    suspend fun getAccountInfo(
        uid: String,
    ): Result<AccountInfo>

    suspend fun setAccountInfo(
        uid: String,
        info: AccountInfo,
    ): Result<Unit>
}