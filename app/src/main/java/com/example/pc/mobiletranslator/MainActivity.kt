package com.example.pc.mobiletranslator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listHistory = ArrayList<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        val key = "trnsl.1.1.20190621T152145Z.34e4c3f52258adbb.2aa259de70e614d3d1bc791753c296066adaf9ab"
//        val api = YTranslateApiImpl(key)
//        val translation = api.translationApi().translate(
//                "Как дела?", Direction.of(Language.RU, Language.EN)
//        )


        val languages: Array<String> = Languages().getLangs()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.setAdapter(adapter)
        spinnerTo.setAdapter(adapter)
        spinnerFrom.setSelection(languages.indexOf("Russian"))
        spinnerTo.setSelection(languages.indexOf("English"))

    }

    private fun Translate(textToBeTranslated: String, languagePair: String): String {
        val translatorBackgroundTask = TranslatorBackgroundTask(this)
        val translationResult = translatorBackgroundTask.execute(textToBeTranslated, languagePair) // Returns the translated text as a String
        return translationResult.get()
    }

    fun clickButton(view : View){
        val langFrom = spinnerFrom.selectedItem.toString()
        val langTo = spinnerTo.selectedItem.toString()
        val codeFrom = Languages().getCodes(Languages().getLangs().indexOf(langFrom))
        val codeTo = Languages().getCodes(Languages().getLangs().indexOf(langTo))
        val translatedText = Translate(editText.text.toString(),codeFrom + "-" + codeTo);
        textViewResult.text = translatedText

        listHistory.add(editText.text.toString() + " ("+codeFrom+") -> " + translatedText + " ("+codeTo+")")
    }

    fun clickImage(view : View){
        val temp = spinnerTo.getSelectedItemPosition()
        spinnerTo.setSelection(spinnerFrom.getSelectedItemPosition())
        spinnerFrom.setSelection(temp)
    }

    fun historyClick(){
        
    }


}
