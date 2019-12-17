package n7.pizza.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch
import n7.pizza.data.pizzaSource.model.Pizza
import n7.pizza.ui.pizzas.domain.usecase.GetAllPizzaUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllPizzaUseCase: GetAllPizzaUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
             getAllPizzaUseCase.getAll()
        }
    }

}