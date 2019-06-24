package com.example.pc.mobiletranslator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        val key = "trnsl.1.1.20190621T152145Z.34e4c3f52258adbb.2aa259de70e614d3d1bc791753c296066adaf9ab"
//        val api = YTranslateApiImpl(key)
//        val translation = api.translationApi().translate(
//                "Как дела?", Direction.of(Language.RU, Language.EN)
//        )



//        val translation = Translate("Привет","ru-en")
//        System.out.print(translation)
    }

    private fun Translate(textToBeTranslated: String, languagePair: String): String {
        val translatorBackgroundTask = TranslatorBackgroundTask(this)
        val translationResult = translatorBackgroundTask.execute(textToBeTranslated, languagePair) // Returns the translated text as a String
        return translationResult.get()
    }

    fun clickButton(view : View){
        val text = editText.text.toString()
        val translation = Translate(text,"ru-en")
        textViewResult.text = translation;
    }

}
