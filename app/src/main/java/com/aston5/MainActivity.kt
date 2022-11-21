package com.aston5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.aston5.databinding.ActivityMainBinding
import com.aston5.model.Contact
import com.aston5.model.ContactsService

class MainActivity : AppCompatActivity(), AppContract {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactAdapter
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ContactAdapter()
        openFrag(PersonItem.newInstance(), R.id.fragment_container_view)
        dataModel.message.observe(this, {
        })


//        val contact = intent.getParcelableExtra<Contact>("contact")
//        if (contact != null) {
//            val nameD : TextView = findViewById(R.id.nameD)
//            val surnameD : TextView = findViewById(R.id.surnameD)
//            val numberD : TextView = findViewById(R.id.number)
//            val photoD : ImageView = findViewById(R.id.photoD)
//
//            nameD.text = contact.name
//            surnameD.text = contact.surname
//            numberD.text = contact.number.toString()
//
//
//            openFrag(EditContactInformationFragment.newInstance(contact), R.id.fragment_container_view)
//        }

    }

    private fun openFrag(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }

    override val contactsService: ContactsService
    get() = (applicationContext as App).contactsService

    override fun launchContactDetailsScreen(contact: Contact) {
        val fragment: EditContactInformationFragment = EditContactInformationFragment.newInstance(contact)
        supportFragmentManager.beginTransaction()
            .replace(R.id.personLayout, fragment)
            .addToBackStack(null)
            .commit()

    }
}