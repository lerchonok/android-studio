package ru.itmo.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
//import ru.itmo.notes.db.MyAdapter
import ru.itmo.notes.db.MyDbManager
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.notes.db.MyAdapter

class MainActivity : ComponentActivity() {

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)

    val rcView: RecyclerView by lazy { findViewById(R.id.rcView) }
    val tvNoElements: TextView by lazy { findViewById(R.id.tvNoElements) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun onClickNew(view: View) {
        val i = Intent(this,EditActivity::class.java)
        startActivity(i)
    }

    fun init() {
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = myAdapter
    }

    fun fillAdapter() {

        val list = myDbManager.readDbData()
        myAdapter.updateAdapter(list)
        if (list.size > 0) {
            tvNoElements.visibility = View.GONE
        } else tvNoElements.visibility = View.VISIBLE

    }
}