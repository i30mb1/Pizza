package n7.pizza.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {

    init {

        viewModelScope.launch {
            suspendCoroutine { continuation ->

            }

            callbackFlow {

            }

            suspendCancellableCoroutine {

            }
        }

    }

}