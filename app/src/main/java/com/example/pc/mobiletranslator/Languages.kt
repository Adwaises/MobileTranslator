package com.example.pc.mobiletranslator

public class Languages {
    private val langs = arrayOf("Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian",
            "Azerbaijan", "Bashkir", "Basque", "Belarussian", "Bengali", "Bosnian", "Bulgarian"
            , "Catalan", "Cebuano", "Chinese", "Croatian", "Czech", "Danish", "Dutch", "English",
            "Esperanto", "Estonian", "Finnish", "French", "Galician", "Georgian", "German", "Greek",
            "Gujarati", "Haitian", "Hebrew", "Hindi", "Hungarian", "Icelandic", "Indonesian", "Irish",
            "Italian", "Japanese", "Javanese", "Kannada", "Kazakh", "Korean", "Kyrgyz", "Latin", "Latvian",
            "Lithuanian", "Luxembourg", "Macedonian", "Malagasy", "Malay", "Malayalam", "Maltese", "Maori",
            "Marathi", "Mari", "Mari", "Mongolian", "Nepali", "Norwegian", "Papiamento", "Persian", "Polish",
            "Portuguese", "Punjabi", "Romanian", "Russian", "Scottish", "Serbian", "Sinhala", "Slovak",
            "Slovenian", "Spanish", "Spit", "Sundanese", "Swahili", "Swedish", "Tagalog", "Tajik", "Tamil",
            "Tatar", "Telugu", "Thai", "Turkish", "Udmurt", "Ukrainian", "Urdu", "Uzbek", "Vietnamese", "Welsh",
            "Yiddish")

    private val codes = arrayOf("af", "sq", "am", "ar", "hy", "az", "ba", "eu",
            "be", "bn", "bs", "bg", "ca", "ceb", "zh", "hr", "cs", "da", "nl", "en",
            "eo", "et", "fi", "fr", "gl", "ka", "de", "el", "gu", "ht", "he", "hi",
            "hu", "is", "id", "ga", "it", "ja", "jv", "kn", "kk", "ko", "ky", "la",
            "lv", "lt", "lb", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mhr", "mrj",
            "mn", "ne", "no", "pap", "fa", "pl", "pt", "pa", "ro", "ru", "gd", "sr",
            "si", "sk", "sl", "es", "xh", "su", "sw", "sv", "tl", "tg", "ta", "tt",
            "te", "th", "tr", "udm", "uk", "ur", "uz", "vi", "cy", "yi")

    fun getLangs(): Array<String> {
        return langs
    }

    fun getCodes(i: Int): String {
        return codes[i]
    }
}