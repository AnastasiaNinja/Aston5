package com.aston5.model
import com.github.javafaker.Faker

typealias ContactsListener = (contacts: List<Contact>) -> Unit

class ContactsService {

    private var contacts: MutableList<Contact> = mutableListOf<Contact>()

    private val listeners: MutableSet<ContactsListener> = mutableSetOf<ContactsListener>()

    init {
        val faker: Faker = Faker.instance()
        IMAGES.shuffle()
        contacts = (1..150).map { Contact(
            id = it.toLong(),
            name = faker.name().firstName(),
            surname = faker.name().lastName(),
            number = faker.number().randomNumber(6, true),
            image = IMAGES[it % IMAGES.size]
        ) }.toMutableList()
    }

    fun getContacts() : List<Contact> {
        return contacts
    }

    fun deleteContact(contact: Contact) {
        val indexToDelete: Int = contacts.indexOfFirst { it.id == contact.id }
        if (indexToDelete != -1) {
            contacts.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun updateContact(newContact : Contact) {
        val indexToUpdate: Int = contacts.indexOfFirst { it.id == newContact.id }
        if (indexToUpdate != -1) {
            contacts[indexToUpdate] = newContact
            notifyChanges()
        }
    }

    fun addListener(listener: ContactsListener) {
        listeners.add(listener)
        listener.invoke(contacts)
    }

    fun removeListener(listener: ContactsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(contacts) }
    }

    companion object {
        private val IMAGES: MutableList<String> = mutableListOf(
            "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1668210039927-82341df16e20?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1668009960194-8f8d6535c770?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1667857481427-382ee0b5207e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1666265972013-d538b0e27388?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1667829186138-55120b70d731?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1646186431760-396ef8d346cb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80",
            "https://images.unsplash.com/photo-1666983998531-622f19bca9a8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1667611209778-ba82bd262af3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1667337779078-475436e7d96f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1655726272769-5f55557e1d5a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://images.unsplash.com/photo-1660566140553-3fe8172933dd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80",
        )
    }
}