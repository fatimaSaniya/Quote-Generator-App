package com.example.quotegenerator.ui.theme

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

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
                    if (quotes.isNotEmpty()) {
                        currentQuote.value = quotes.random()
                    } else {
                        currentQuote.value = "No quotes found."
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("QuoteViewModel", "Error getting documents: ", exception)
                    currentQuote.value = "Failed to fetch quotes."
                }
        }
    }
}
