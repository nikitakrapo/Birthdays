package com.nikitakrapo.birthdays.account.info

actual fun AccountInfoRepository(): AccountInfoRepository = FirebaseAccountInfoRepository()