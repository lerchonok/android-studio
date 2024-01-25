package ru.itmo.notes

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.itmo.notes.db.MyDbManager
import android.content.Intent as Intent

class EditActivity : AppCompatActivity() {

   val myDbManager = MyDbManager(this)

    lateinit var fl_photo: ImageView
    lateinit var mainImageLayout: ConstraintLayout
    lateinit var edTitle: TextView
    lateinit var edDesc: TextView
    lateinit var fl_changeImage: Button

    val imageRequestCode = 10
    var tempImageURI = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        fl_photo = findViewById(R.id.fl_photo)
        mainImageLayout = findViewById(R.id.mainImageLayout)
        edTitle = findViewById(R.id.edTitle)
        edDesc = findViewById(R.id.edDesc)
        fl_changeImage = findViewById(R.id.fl_changeImage)
    }

    fun onClickAddImage(view: View) {
        mainImageLayout.visibility = View.VISIBLE
        fl_photo.visibility = View.GONE
    }

    fun onClickDeleteImage(view: View) {
        mainImageLayout.visibility = View.GONE
        fl_photo.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == imageRequestCode) {
            fl_photo.setImageURI(data?.data)
            tempImageURI = data?.data.toString()
        }
    }


    fun onClickSave(view: View) {
        val myTitle = edTitle.text.toString()
        val myDesc = edDesc.text.toString()

        if (myTitle != "" && myDesc != "") {
            myDbManager.insertToDb(myTitle, myDesc, tempImageURI)
        }
    }

    fun onClickChange(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imageRequestCode)
   }
}