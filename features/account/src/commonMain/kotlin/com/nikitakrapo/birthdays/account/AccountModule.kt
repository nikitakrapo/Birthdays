package com.nikitakrapo.birthdays.account

import com.nikitakrapo.birthdays.account.info.AccountInfoRepository
import org.koin.dsl.module

val accountModule = module {
    single<AccountManager> { AccountManager() }
    single<AccountInfoRepository> { AccountInfoRepository() }
}
