package ru.itmo.notes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import java.util.ArrayList

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String, uri: String, time:String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMAGE_URI, uri)
            put(MyDbNameClass.COLUMN_NAME_TIME, time)

        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun removeFromDb(id: String) {
        var selection = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selection, null)
    }

    fun updateItem(title: String, content: String, uri: String, id: Int, time: String) {

        var selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMAGE_URI, uri)
            put(MyDbNameClass.COLUMN_NAME_TIME, time)

        }
        db?.update(MyDbNameClass.TABLE_NAME, values, selection, null)
    }

    @SuppressLint("Range")
    fun readDbData() : ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null,
            null, null, null, null)

        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataTitle = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                val dataCont = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CONTENT))
                val dataUri = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_IMAGE_URI))
                val dataId = cursor?.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val time = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TIME))
                var item = ListItem()

                item.title = dataTitle!!
                item.desc = dataCont!!
                item.uri = dataUri!!
                item.id = dataId!!
                item.time = time!!
                dataList.add(item)
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDb() {
        myDbHelper.close()
    }


}