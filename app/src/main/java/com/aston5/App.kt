package com.aston5

import android.app.Application
import com.aston5.model.ContactsService

class App : Application() {

    val contactsService = ContactsService()
}