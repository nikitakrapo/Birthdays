package com.nikitakrapo.birthdays.account.models

enum class RegistrationErrorType {
    EMAIL_WRONG_FORMAT,
    EMAIL_ALREADY_IN_USE,
    WEAK_PASSWORD,
    NETWORK,
    UNKNOWN,
    ;

    val isAboutUsername: Boolean
        get() = when (this) {
            EMAIL_WRONG_FORMAT,
            EMAIL_ALREADY_IN_USE,
            WEAK_PASSWORD,
            NETWORK,
            UNKNOWN -> false
        }

    val isAboutEmail: Boolean
        get() = when (this) {
            EMAIL_WRONG_FORMAT,
            EMAIL_ALREADY_IN_USE -> true
            UNKNOWN,
            WEAK_PASSWORD,
            NETWORK -> false
        }

    val isAboutPassword: Boolean
        get() = when (this) {
            WEAK_PASSWORD -> true
            EMAIL_WRONG_FORMAT,
            EMAIL_ALREADY_IN_USE,
            UNKNOWN,
            NETWORK -> false
        }
}