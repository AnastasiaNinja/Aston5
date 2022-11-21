package com.aston5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.aston5.databinding.FragmentEditContactInformationBinding
import com.aston5.model.Contact
import com.aston5.model.ContactsService
import com.bumptech.glide.Glide


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
        binding.nameD.setText(contact.name, TextView.BufferType.EDITABLE)
        binding.surnameD.setText(contact.surname)
        binding.numberD.setText(contact.number.toString())
        binding.photoD.drawable
        if(contact.image.isNotBlank()) {
            Glide.with(binding.photoD.context)
                .load(contact.image)
                .circleCrop()
                .placeholder(R.drawable.ic_contact_avatar)
                .error(R.drawable.ic_contact_avatar)
                .into(binding.photoD)
        } else {
            binding.photoD.setImageResource(R.drawable.ic_contact_avatar)
        }

        binding.button.setOnClickListener {
            val newContact = Contact(
                contact.id,
                binding.nameD.text.toString(),
                binding.surnameD.text.toString(),
                binding.numberD.text.toString().toLong(), contact.image
            )
            contactsService.updateContact(newContact)
            if(!context?.isTablet()!!) {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
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