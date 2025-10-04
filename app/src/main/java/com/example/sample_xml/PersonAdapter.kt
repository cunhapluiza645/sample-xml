package com.example.sample_xml

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private val originalList: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(), Filterable {

    private var filteredList: MutableList<Person> = originalList.toMutableList()

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.personName)
        val image: ImageView = itemView.findViewById(R.id.personImage)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val person = filteredList[position]
                    val context = itemView.context
                    val intent = Intent(context, PersonDetailActivity::class.java).apply {
                        putExtra("name", person.name)
                        putExtra("email", person.email)
                        putExtra("mobile", person.mobile)
                        putExtra("imageResId", person.imageResId)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = filteredList[position]
        holder.name.text = person.name
        holder.image.setImageResource(person.imageResId)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val search = query?.toString()?.lowercase()?.trim() ?: ""
                val results = if (search.isEmpty()) {
                    originalList
                } else {
                    originalList.filter {
                        it.name.lowercase().contains(search)
                    }
                }
                return FilterResults().apply { values = results }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(query: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<Person>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }
}
