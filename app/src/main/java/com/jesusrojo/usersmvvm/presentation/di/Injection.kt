package com.jesusrojo.usersmvvm.presentation.di

import android.content.res.Resources
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.presentation.ui.activity.UsersAdapter

class Injection {

    companion object {

        fun provideUsersAdapter(values: ArrayList<User>,
                                resources: Resources?,
                                listener: (User) -> Unit
        ): UsersAdapter = UsersAdapter(values, resources, listener)


//        fun provideSharedPreferences(context: Context): SharedPreferences =
//            PreferenceManager.getDefaultSharedPreferences(context)
    }
}