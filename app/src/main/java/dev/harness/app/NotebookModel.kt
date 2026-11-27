package dev.harness.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class NotebookModel {
    var notes: List<String> by mutableStateOf(emptyList())
        private set
    var draft: String by mutableStateOf("")

    fun delete(index: Int) {
        notes = notes.filterIndexed { i, _ -> i != index }
    }

    fun add() {
        notes = listOf(draft) + notes
        draft = ""
    }
}
