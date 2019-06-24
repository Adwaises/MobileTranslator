package com.example.pc.mobiletranslator

import android.content.Context
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class TranslatorBackgroundTask
internal constructor(
        private var ctx: Context) : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String): String? {
        val textToBeTranslated = params[0]
        val languagePair = params[1]

        try {
            val yandexKey = "trnsl.1.1.20190621T152145Z.34e4c3f52258adbb.2aa259de70e614d3d1bc791753c296066adaf9ab"
            val yandexUrl = ("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                    + "&text=" + textToBeTranslated + "&lang=" + languagePair)
            val yandexTranslateURL = URL(yandexUrl)

            val httpJsonConnection = yandexTranslateURL.openConnection() as HttpURLConnection
            val inputStream = httpJsonConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            var resultString = bufferedReader.readText()

            bufferedReader.close()
            inputStream.close()
            httpJsonConnection.disconnect()

            resultString = resultString.substring(resultString.indexOf("[")+2,resultString.indexOf("]")-1)
            
            return resultString

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: String) {}

    override fun onProgressUpdate(vararg values: Void) {
        super.onProgressUpdate(*values)
    }
}