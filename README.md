# birthdays-mobile

This is a mobile part of Birthdays application built on [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html).

At the moment all the logic is shared between platforms but UI is ready only for Android. 
When all the business logic would be finalised, I'll start working on iOS UI.

You can also check other parts:
- [Backend](https://github.com/nikitakrapo/birthdays-backend) - Ktor Backend
- [Database](https://github.com/nikitakrapo/birthdays-database) - SQL DB migrations

## Tech stack


| Name           | Description                                            |
|----------------|--------------------------------------------------------|
| Navigation     | [Decompose](https://github.com/arkivanov/Decompose)    |
| Architecture   | [MVI Kotlin](https://github.com/arkivanov/MVIKotlin)   |
| UI             | Android - Jetpack Compose, iOS - SwiftUI               |
| Data storage   | [SQLDelight](https://github.com/sqldelight/sqldelight) |
| Authentication | Firebase                                               |

## Features

| Name            | Description                                                                   |
|-----------------|-------------------------------------------------------------------------------|
| Authorization   | Ability to register/login with custom user data                               |
| Local birthdays | You are able to add your friends' birthdays, and they will be saved on server |

## How to build & start project

Soon you'll be able to check the app by downloading it from Google Play,
but if you really want to test it right now, here's the way to build the app on your own.

To build the app, you'll need to:
1. Register a project in firebase, enable Auth & Analytics, set up mobile apps and download json
2. Replace androidApp/google-services.json with google-services.json from your project
3. Launch ./gradlew build

For tutorials regarding how to build backend app and set up DB, follow these links:
- [Backend](https://github.com/nikitakrapo/birthdays-backend)
- [Database](https://github.com/nikitakrapo/birthdays-database)
