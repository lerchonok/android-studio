package ru.itmo.notes

import android.os.Bundle
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

    fun onClickNew(view: TextView) {
        /*tvTest.text = ""
        myDbManager.insertToDb(edTitle.text.toString(), edContent.text.toString())
        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            tvTest.append(item)
            tvTest.append("\n")
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}