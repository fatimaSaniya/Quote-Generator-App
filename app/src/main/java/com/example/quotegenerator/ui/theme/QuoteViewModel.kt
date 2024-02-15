package com.example.quotegenerator.ui.theme

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

//class QuoteViewModel : ViewModel() {
//    // Replace this with your actual list of quotes
//    private val quotes = listOf("Quote 1", "Quote 2", "Quote 3")
//    var currentQuote = MutableLiveData<String>()
//
//    init {
//        getRandomQuote()
//    }
//
//    fun getRandomQuote() {
//        viewModelScope.launch {
//            currentQuote.value = quotes.random()
//        }
//    }
//}

class QuoteViewModel : ViewModel() {
    private val db = Firebase.firestore
    var currentQuote = MutableLiveData<String>()

    init {
        getRandomQuote()
    }

    fun getRandomQuote() {
        viewModelScope.launch {
            db.collection("Quotes")
                .get()
                .addOnSuccessListener { documents ->
                    val quotes = documents.map { it.getString("quote") ?: "" }
                    currentQuote.value = quotes.random() // This line is causing the error
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }
}
