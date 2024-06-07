package com.nikitakrapo.birthdays.network

interface AuthorizationTokenProvider {
    suspend fun getToken(): String?
}