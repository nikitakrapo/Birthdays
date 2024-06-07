package com.nikitakrapo.birthdays

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nikitakrapo.birthdays.account.models.RegistrationErrorType
import strings.R

@Composable
internal fun RegistrationErrorType.getText() = stringResource(
    id = when (this) {
        RegistrationErrorType.EMAIL_WRONG_FORMAT -> R.string.registration_error_email_wrong_format
        RegistrationErrorType.EMAIL_ALREADY_IN_USE -> R.string.registration_error_email_already_in_use
        RegistrationErrorType.WEAK_PASSWORD -> R.string.registration_error_weak_password
        RegistrationErrorType.NETWORK -> R.string.registration_error_network
        RegistrationErrorType.UNKNOWN -> R.string.registration_error_unknown
    },
)