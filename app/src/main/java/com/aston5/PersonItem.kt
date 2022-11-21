package com.aston5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aston5.databinding.FragmentPersonItemBinding
import com.aston5.model.ContactsListener
import com.aston5.model.ContactsService


class PersonItem : Fragment() {
    lateinit var binding: FragmentPersonItemBinding

    private lateinit var adapter: ContactAdapter

    private val contactsService: ContactsService
        get() = (context?.applicationContext as App).contactsService

    override fun onDestroy() {
        super.onDestroy()
        contactsService.removeListener(contactsListener)
    }

    private val contactsListener: ContactsListener = {
        adapter.contacts = it
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ContactAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = adapter
        contactsService.addListener(contactsListener)
    }


    companion object {
        @JvmStatic
        fun newInstance() = PersonItem()
    }
}