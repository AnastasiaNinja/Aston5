package com.aston5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.aston5.databinding.FragmentEditContactInformationBinding
import com.aston5.model.Contact
import com.aston5.model.ContactsService


class EditContactInformationFragment : Fragment() {
    private lateinit var binding: FragmentEditContactInformationBinding

    private val contactsService: ContactsService
        get() = (context?.applicationContext as App).contactsService

    private val contact : Contact
        get() = requireArguments().getParcelable<Contact>(ARG_CONTACT) as Contact


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditContactInformationBinding.inflate(inflater)
        binding.nameD.text = contact.name
        binding.surnameD.text = contact.surname
        binding.numberD.text = contact.number.toString()
        return binding.root
    }


    companion object {

        const val ARG_CONTACT = "ARG_CONTACT"

        @JvmStatic
        fun newInstance(contact: Contact): EditContactInformationFragment {
            val fragment = EditContactInformationFragment()
            fragment.arguments = bundleOf(ARG_CONTACT to contact)
            return fragment
        }
    }
}