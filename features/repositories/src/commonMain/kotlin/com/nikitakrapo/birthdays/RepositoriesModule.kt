package com.nikitakrapo.birthdays

import com.nikitakrapo.birthdays.remote.BirthdaysApi
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import com.nikitakrapo.birthdays.repositories.birthdays.FakeBirthdaysRepository
import com.nikitakrapo.birthdays.repositories.profile.FakeProfileRepository
import com.nikitakrapo.birthdays.repositories.profile.ProfileRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory<BirthdaysApi> { BirthdaysApi(get()) }
    single<ProfileRepository> { FakeProfileRepository() }
    factory<BirthdaysRepository> { FakeBirthdaysRepository() }
}