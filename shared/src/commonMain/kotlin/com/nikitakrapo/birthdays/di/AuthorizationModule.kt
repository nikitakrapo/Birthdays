package com.nikitakrapo.birthdays.di

import com.nikitakrapo.birthdays.account.AccountManager
import com.nikitakrapo.birthdays.account.FirebaseAccountManager
import org.koin.dsl.module

internal val authorizationModule = module {
    single<AccountManager> { FirebaseAccountManager() }
}