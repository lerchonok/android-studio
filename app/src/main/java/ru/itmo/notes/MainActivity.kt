package ru.itmo.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import ru.itmo.notes.db.MyDbManager

class MainActivity : ComponentActivity() {
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        val dataList = myDbManager.readDbData()
       /* for (item in dataList) {
            text.toString(item)
            text.toString("\n")
        }*/
    }

    fun onClickNew(view: View) {
        val i = Intent(this,EditActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}