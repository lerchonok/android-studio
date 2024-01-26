package ru.itmo.notes.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.notes.EditActivity
import ru.itmo.notes.R
import java.util.ArrayList

class MyAdapter(listMain: ArrayList<ListItem>, contextM: Context) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var listArray = listMain
    var context = contextM

    class MyHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val context = contextV

        fun setData(item: ListItem) {

            tvTitle.text = item.title
            itemView.setOnClickListener {
                val intent = Intent(context, EditActivity::class.java).apply {

                    putExtra(IntentConstants.I_TITLE_KEY, item.title)
                    putExtra(IntentConstants.I_DESC_KEY, item.desc)
                    putExtra(IntentConstants.I_URI_KEY, item.uri)

                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.rc_item, parent, false), context)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }

    fun updateAdapter(listtItems: List<ListItem>) {
        listArray.clear()
        listArray.addAll(listtItems)
        notifyDataSetChanged()
    }
}