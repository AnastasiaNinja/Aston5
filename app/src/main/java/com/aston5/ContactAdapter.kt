package com.aston5

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.ViewBindingAdapter.setOnLongClickListener
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.aston5.databinding.ContactItemBinding
import com.aston5.model.Contact
import com.aston5.model.ContactsService
import com.bumptech.glide.Glide
import java.nio.file.DirectoryStream.Filter

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactHolder>(), View.OnClickListener, Filterable {

    var contacts: List<Contact> = emptyList()
        set(newValue) {
            field = newValue
            filteredContacts = newValue
            notifyDataSetChanged()
        }
    private var filteredContacts: List<Contact> = emptyList()


    override fun onClick(v: View) {
    }



    class ContactHolder(item : View): RecyclerView.ViewHolder(item) {

        val binding = ContactItemBinding.bind(item)


        fun bind(contact: Contact) = with(binding){
            contactName.text = contact.name
            surName.text = contact.surname
            number.text = contact.number.toString()
            if(contact.image.isNotBlank()) {
                Glide.with(imageView.context)
                    .load(contact.image)
                    .circleCrop()
                    .placeholder(R.drawable.ic_contact_avatar)
                    .error(R.drawable.ic_contact_avatar)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.ic_contact_avatar)
            }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(filteredContacts[position])
        holder.itemView.setOnClickListener { view ->
            val activity: AppCompatActivity = view.context as AppCompatActivity
            val fragment = EditContactInformationFragment()
            val bundle = Bundle()
            bundle.putParcelable(
                EditContactInformationFragment.ARG_CONTACT,
                filteredContacts[holder.bindingAdapterPosition]
            )
            fragment.arguments = bundle
            var id = R.id.fragment_container_view
            if (view.context.isTablet()) {
                id = R.id.fragment_edit_container_view
            }
            activity.supportFragmentManager.beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null).commit()
        }

    }

    override fun getItemCount(): Int = filteredContacts.size


    override fun getFilter(): android.widget.Filter {
        return object: android.widget.Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {

                val filterResults = FilterResults()
                val ongoingFilter: List<Contact>
                if(charsequence == null || charsequence.length < 0){
                    ongoingFilter = contacts
                } else {
                    val searchChr = charsequence.toString().lowercase()
                    ongoingFilter = mutableListOf()
                    for(item in contacts) {
                        if(item.name.lowercase().contains(searchChr) || item.surname.lowercase().contains(searchChr)) {
                            ongoingFilter.add(item)
                        }
                    }

                }
                filterResults.count = ongoingFilter.size
                filterResults.values = ongoingFilter
               return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {

                filteredContacts = filterResults!!.values as ArrayList<Contact>
                notifyDataSetChanged()
            }

        }
    }


}


