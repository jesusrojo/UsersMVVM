#### UsersMVVM

Android playground project to learn the [recommended app architecture](https://developer.android.com/jetpack/guide).

The app fetch `Users` from the web [jsonplaceholder](https://jsonplaceholder.typicode.com>).

The End-Point is [this url](https://jsonplaceholder.typicode.com/users).

The datas are displayed in the `Activity` and the details in a `DialogFragment`.

The `Activity` use `LiveData` to observe the `ViewModel` with `MutableLiveData`.

Use:
- [Kotlin](https://kotlinlang.org/)
- MVVM
- UseCases:
    - FetchUsersUseCase
    - DeleteAllUsersUseCase
- Repositories:
  - CacheRepository (memory)
  - LocalRepository (Android [Room](https://developer.android.com/jetpack/androidx/releases/room))
  - RemoteRepository ([Retrofit](https://github.com/square/retrofit))
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [DataBinding](https://developer.android.com/topic/libraries/data-binding/)
- Hilt [Hilt-Training](https://developer.android.com/training/dependency-injection/hilt-android), [Hilt-Release](https://developer.android.com/jetpack/androidx/releases/hilt)
- [Leak Canary](https://github.com/square/leakcanary)
- [Timber](https://github.com/JakeWharton/timber)
- Unit Test [Android Unit Test Training](https://developer.android.com/training/testing/unit-testing/)
- [Espresso Test](https://developer.android.com/training/testing/ui-testing/espresso-testing) ([Barista](https://github.com/AdevintaSpain/Barista))
