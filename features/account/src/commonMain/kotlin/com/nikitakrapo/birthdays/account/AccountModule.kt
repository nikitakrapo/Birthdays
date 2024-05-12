package com.nikitakrapo.birthdays.account

import com.nikitakrapo.birthdays.UserDataRepository
import com.nikitakrapo.birthdays.UserDataRepositoryFake
import org.koin.dsl.module

val accountModule = module {
    factory<UserDataRepository> { UserDataRepositoryFake() }
}
