package com.nikitakrapo.birthdays.cms

import org.koin.dsl.module

val pushModule = module {
    single<PushTokenInteractor> { PushTokenInteractorImpl(get()) }
}
