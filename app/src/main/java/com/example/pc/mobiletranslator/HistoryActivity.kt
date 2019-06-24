package com.example.pc.mobiletranslator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class HistoryActivity : AppCompatActivity() {

    companion object {
        val LIST_HISTORY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        
        val list = intent.getStringArrayListExtra(LIST_HISTORY)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
    }
}