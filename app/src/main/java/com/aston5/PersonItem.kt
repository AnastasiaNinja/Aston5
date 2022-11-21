package com.aston5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aston5.databinding.FragmentPersonItemBinding
import com.aston5.model.Contact
import com.aston5.model.ContactsListener
import com.aston5.model.ContactsService
import kotlin.contracts.contract


class PersonItem : Fragment() {
    private val dataModel : DataModel by activityViewModels()
    lateinit var binding: FragmentPersonItemBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContactAdapter
    private lateinit var newArrayList: ArrayList<Contact>

    private val contactsService: ContactsService
    get() = (context?.applicationContext as App).contactsService





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


    }

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
        fun newInstance() = PersonItem() }
}