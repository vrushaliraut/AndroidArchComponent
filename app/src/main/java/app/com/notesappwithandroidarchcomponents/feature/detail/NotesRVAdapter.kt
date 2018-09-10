package app.com.notesappwithandroidarchcomponents.feature.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import app.com.notesappwithandroidarchcomponents.R
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import app.com.notesappwithandroidarchcomponents.data.Notes

internal class NotesRVAdapter(items: List<Notes>) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>(), Filterable {

    var mutableList = items
    var originalList = items
    var rvAdapterItemClickListener: RVAdapterItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_rv_note, parent, false), rvAdapterItemClickListener)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItemAtPos(position)?.apply {
            holder.tvNoteTitle.text = title
            holder.tvNoteText.text = note
            holder.tvElapsed.text = recordedAt.toString()
        }
    }


    fun updateItems(list: List<Notes>) {
        originalList = list
        mutableList = list
        notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mutableList = originalList
                } else {
                    val filteredList = ArrayList<Notes>()
                    originalList.filterTo(filteredList) { it.title.toLowerCase().contains(charString.toLowerCase()) }
                    originalList.filterTo(filteredList) { it.note.toLowerCase().contains(charString.toLowerCase()) }
                    mutableList = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = mutableList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                try {
                    mutableList = filterResults.values as ArrayList<Notes>
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun setOnItemClickListener(rvAdapterItemClickListener: RVAdapterItemClickListener) {
        this.rvAdapterItemClickListener = rvAdapterItemClickListener
    }

    fun getItemAtPos(pos: Int): Notes? {
        return mutableList[pos]
    }

    class ViewHolder(itemView: View, rvAdapterItemClickListener: RVAdapterItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var tvNoteTitle: TextView = itemView.findViewById(R.id.tvNoteTitle)
        var tvNoteText: TextView = itemView.findViewById(R.id.tvNoteText)
        var tvElapsed: TextView = itemView.findViewById(R.id.tvElapsed)

        init {
            itemView.setOnClickListener { rvAdapterItemClickListener?.onClick(adapterPosition) }
        }
    }
}
