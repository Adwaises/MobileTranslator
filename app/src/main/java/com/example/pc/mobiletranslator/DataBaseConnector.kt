package com.example.pc.mobiletranslator

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseConnector(var context: Context): SQLiteOpenHelper(context, "database",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE history (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " text VARCHAR(256));"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(text : String){
        val db = this.getWritableDatabase()
        var cv = ContentValues()
        cv.put("text",text)
        var result = db.insert("history",null,cv)
    }

    fun readData(): ArrayList<String>{
        var list = ArrayList<String>()
        val db = this.readableDatabase
        var query = "Select * from " + "history order by id desc"
        val results = db.rawQuery(query,null)
        if (results.moveToFirst()){
            do {
                val text = results.getString(1).toString()
                list.add(text)
            }while (results.moveToNext())
        }

        results.close()
        db.close()
        return list
    }

    fun deleteAllData(){
        val db = this.writableDatabase
        db.delete("history", null, null)
        db.close()
    }

}