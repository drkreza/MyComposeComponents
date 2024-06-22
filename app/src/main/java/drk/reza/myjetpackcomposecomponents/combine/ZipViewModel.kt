package drk.reza.myjetpackcomposecomponents.combine

// MyViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ZipViewModel : ViewModel() {
    private val _flow1 = MutableStateFlow(0)
    private val _flow2 = MutableStateFlow("")

    // Combine the two flows using zip
    val combinedFlow: Flow<String> = _flow1.zip(_flow2) { number, text ->
        "Number: $number, Text: $text"
    }

    init {
        // Simulate emission of values for demonstration
        simulateEmissions()
    }

    private fun simulateEmissions() {
        viewModelScope.launch {
            // Emit values for flow1
            listOf(1, 2, 3).forEach {
                _flow1.value = it
                kotlinx.coroutines.delay(1000L) // Delay to simulate asynchronous updates
            }
        }

        viewModelScope.launch {
            // Emit values for flow2
            listOf("A", "B").forEach {
                _flow2.value = it
                kotlinx.coroutines.delay(1500L) // Delay to simulate asynchronous updates
            }
        }
    }
}
