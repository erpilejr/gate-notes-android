package dev.harness.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

private const val PRICE = 12.50

fun formatPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    format.currency = Currency.getInstance("NZD")
    return format.format(PRICE)
}

@Composable
fun AppRoot() {
    val model = remember { NotebookModel() }
    Notebook(
        notes = model.notes,
        draft = model.draft,
        onDraft = { model.draft = it },
        onAdd = { model.add() },
        price = formatPrice()
    )
}

@Composable
fun Notebook(
    notes: List<String>,
    draft: String,
    onDraft: (String) -> Unit,
    onAdd: () -> Unit,
    price: String
) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = draft,
                onValueChange = onDraft,
                modifier = Modifier.weight(1f).testTag("note-input")
            )
            Button(onClick = onAdd, modifier = Modifier.testTag("add-button")) {
                Text("Add")
            }
        }
        LazyColumn(Modifier.weight(1f).fillMaxWidth()) {
            itemsIndexed(notes) { i, note ->
                Text(note, Modifier.testTag("note-$i"))
            }
        }
        Text(price, Modifier.testTag("price"))
    }
}
