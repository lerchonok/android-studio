package ru.itmo.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    fun onClickAddImage(view: View) {
        mainImageLayout.visibility = View.VISIBLE
        fl_photo.visibility = View.GONE
    }
}