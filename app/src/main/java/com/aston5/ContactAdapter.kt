package com.aston5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aston5.databinding.ContactItemBinding
import com.aston5.model.Contact
import com.bumptech.glide.Glide

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    var onItemClick : ((Contact) -> Unit)? = null

    var contacts: List<Contact> = emptyList()
    set(newValue) {
        field = newValue
        notifyDataSetChanged()
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
        holder.bind(contacts[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(contacts[position])
        }
    }

    override fun getItemCount(): Int = contacts.size

}