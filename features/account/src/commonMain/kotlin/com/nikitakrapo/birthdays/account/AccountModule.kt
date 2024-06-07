package com.nikitakrapo.birthdays.account

import org.koin.dsl.module

val accountModule = module {
    single<AccountManager> { AccountManager() }
}
