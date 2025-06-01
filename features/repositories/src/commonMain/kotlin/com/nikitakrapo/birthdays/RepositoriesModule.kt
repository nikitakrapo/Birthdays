package com.nikitakrapo.birthdays

import com.nikitakrapo.birthdays.remote.BirthdaysApi
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepositoryImpl
import com.nikitakrapo.birthdays.repositories.profile.FakeProfileRepository
import com.nikitakrapo.birthdays.repositories.profile.ProfileRepository
import com.nikitakrapo.birthdays.repositories.user.UserRepository
import com.nikitakrapo.birthdays.repositories.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    factory<BirthdaysApi> { BirthdaysApi(get()) }
    single<ProfileRepository> { FakeProfileRepository() }
    factory<BirthdaysRepository> { BirthdaysRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
}