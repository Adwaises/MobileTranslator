package com.example.pc.mobiletranslator

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    var db = DataBaseConnector(this)

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

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
        val translationResult = translatorBackgroundTask.execute(textToBeTranslated, languagePair)
        return translationResult.get()
    }

    fun clickButton(view : View){
        editText.onEditorAction(EditorInfo.IME_ACTION_DONE)
        db.insertData(createLine())
        if(spinnerTo.selectedItem.toString() == "English"){
            imageMicro.visibility = View.VISIBLE
            textViewMicro.visibility = View.VISIBLE
        } else {
            imageMicro.visibility = View.INVISIBLE
            textViewMicro.visibility = View.INVISIBLE
        }
    }

    private fun createLine() : String{
        val langFrom = spinnerFrom.selectedItem.toString()
        val langTo = spinnerTo.selectedItem.toString()
        val codeFrom = Languages().getCodes(Languages().getLangs().indexOf(langFrom))
        val codeTo = Languages().getCodes(Languages().getLangs().indexOf(langTo))
        val translatedText = Translate(editText.text.toString(),codeFrom + "-" + codeTo);
        textViewResult.text = translatedText
        return editText.text.toString() + " ("+codeFrom+") -> " + translatedText + " ("+codeTo+")";
    }

    fun clickImage(view : View){
        val temp = spinnerTo.getSelectedItemPosition()
        spinnerTo.setSelection(spinnerFrom.getSelectedItemPosition())
        spinnerFrom.setSelection(temp)
    }

    fun clickHistory(view : View){
        val intent = Intent(this,HistoryActivity::class.java)
        startActivity(intent)
    }

    fun clickMicro(view: View){
        val text = textViewResult!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    public override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    fun shareIt(view: View){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, "Результат перевода:\r\n" + createLine())
        startActivity(Intent.createChooser(intent,"Поделиться"))
    }

}
