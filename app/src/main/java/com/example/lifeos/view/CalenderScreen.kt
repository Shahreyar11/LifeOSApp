package com.example.lifeos.view


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import java.time.*
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    val pickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant().toEpochMilli()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(11.dp)

    ) {
        // 🔘 Button to open full calendar
        Button(
            onClick = { showDatePicker = true },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Calendar")
        }

        Spacer(modifier = Modifier.height(6.dp))

        // 📅 Horizontal Week View
        WeekCalendarBar(
            selectedDate = selectedDate,
            onDateSelected = onDateSelected
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 📍 Display selected date below
        Text(
            text = "Selected Date: ${
                selectedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            }",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    // 📆 Full calendar dialog
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    pickerState.selectedDateMillis?.let {
                        val newDate = Instant.ofEpochMilli(it)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        onDateSelected(newDate)
                    }
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = pickerState)
        }
    }
}



