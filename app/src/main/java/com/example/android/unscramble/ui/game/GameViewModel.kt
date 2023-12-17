package com.example.android.unscramble.ui.game

import android.provider.Settings.System.getString
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameViewModel : ViewModel() {

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }
    }


    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }


    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    private var _count = 0
    val count: Int
        get() = _count

    private var _score = 0
    val score: Int
        get() = _score

    private var currentWordCount = 0


    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
}