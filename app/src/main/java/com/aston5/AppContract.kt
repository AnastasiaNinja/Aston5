package com.aston5

import androidx.fragment.app.Fragment
import com.aston5.model.Contact
import com.aston5.model.ContactsService

fun Fragment.contract(): AppContract = requireActivity() as AppContract

interface AppContract {

    val contactsService: ContactsService

    fun launchContactDetailsScreen(contact: Contact)
}