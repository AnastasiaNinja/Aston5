package com.aston5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.aston5.databinding.ContactItemBinding
import com.aston5.model.Contact
import com.bumptech.glide.Glide

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactHolder>(), View.OnClickListener {

    var contacts: List<Contact> = emptyList()
    set(newValue) {
        field = newValue
        notifyDataSetChanged()
    }

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
        holder.bind(contacts[position])
        holder.itemView.setOnClickListener { view ->
            val activity: AppCompatActivity = view.context as AppCompatActivity
            val fragment = EditContactInformationFragment()
            val bundle = Bundle()
            bundle.putParcelable(
                EditContactInformationFragment.ARG_CONTACT,
                contacts[holder.bindingAdapterPosition]
            )
            fragment.arguments = bundle
            var id = R.id.fragment_container_view
            if (view.context.isTablet()) {
                id = R.id.fragment_edit_container_view
            }
            val transaction = activity.supportFragmentManager.beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int = contacts.size



}