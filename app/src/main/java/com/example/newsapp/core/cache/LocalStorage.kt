package com.example.newsapp.core.cache

import com.example.newsapp.core.app.App
import com.example.newsapp.core.utils.BooleanPreference
import com.example.newsapp.core.utils.StringPreference
import com.securepreferences.SecurePreferences


class LocalStorage {
    private val KEY = "SKAOJXJDSFIJDSDSF342s4j4dsfadsasg"
    private val securePref = SecurePreferences(App.instance, KEY, "local_storage.xml")

    var country: String by StringPreference(securePref, "ru")


}