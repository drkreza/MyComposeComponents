package drk.reza.myjetpackcomposecomponents.combine

// MyViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MergeViewModel : ViewModel() {
    private val _flow1 = MutableStateFlow(0)
    private val _flow2 = MutableStateFlow("")

    // Merge the two flows
    val mergedFlow: Flow<String> = merge(
        _flow1.map { "Number: $it" },
        _flow2.map { "Text: $it" }
    )

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