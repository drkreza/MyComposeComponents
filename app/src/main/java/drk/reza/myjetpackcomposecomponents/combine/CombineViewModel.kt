package drk.reza.myjetpackcomposecomponents.combine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CombineViewModel : ViewModel() {
    private val _flow1 = MutableStateFlow(0)
    private val _flow2 = MutableStateFlow("")

    // Combine the two flows
    val combinedFlow: Flow<String> = _flow1.combine(_flow2) { number, text ->
        "Number: $number, Text: $text"
    }

    // Function to simulate data updates
    fun updateData(number: Int, text: String) {
        viewModelScope.launch {
            _flow1.value = number
            _flow2.value = text
        }
    }
}