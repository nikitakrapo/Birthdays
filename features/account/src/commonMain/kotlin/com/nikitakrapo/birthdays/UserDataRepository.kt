package com.nikitakrapo.birthdays

interface UserDataRepository {
    suspend fun completedWizard(): Boolean
}