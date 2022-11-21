package com.aston5

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.aston5.databinding.FragmentEditContactInformationBinding
import com.aston5.model.Contact
import com.aston5.model.ContactsService


class EditContactInformationFragment : Fragment() {
    lateinit var binding: FragmentEditContactInformationBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContactAdapter
    private lateinit var newArrayList: ArrayList<Contact>

    private val contactsService: ContactsService
        get() = (context?.applicationContext as App).contactsService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditContactInformationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }



    companion object {

        @JvmStatic
        fun newInstance() = EditContactInformationFragment() }



}