package com.nikitakrapo.trips.network

interface AuthorizationTokenProvider {
    suspend fun getToken(): String?
}