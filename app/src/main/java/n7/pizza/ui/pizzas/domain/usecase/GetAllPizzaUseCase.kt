package n7.pizza.ui.pizzas.domain.usecase

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.delay
import n7.pizza.data.pizzaSource.remote.PizzaRepository
import javax.inject.Inject

class GetAllPizzaUseCase @Inject constructor(
    private val pizzaRepository: PizzaRepository
) {

    suspend fun getAll() {
        pizzaRepository.writeInDatabase()
        pizzaRepository.writeInFireStore()

        delay(3000)
        pizzaRepository.readFromDatabase()
        kotlin.runCatching {
            pizzaRepository.readFromFireStore()

        }.onSuccess {

        }
    }

}