package com.nikitakrapo.birthdays

class UserDataRepositoryFake : UserDataRepository {
    override suspend fun completedWizard(): Boolean {
        return false
    }
}
