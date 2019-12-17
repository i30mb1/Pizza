package n7.pizza.data.pizzaSource.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import n7.pizza.data.pizzaSource.model.Pizza
import javax.inject.Inject

class PizzaRepository @Inject constructor(
    private val database: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {

    suspend fun writeInDatabase() {
        database.getReference("pizza")
            .setValue("hello")
    }

    suspend fun readFromDatabase() {
        database.getReference("pizza")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0
               }
            })
    }

    suspend fun writeInFireStore() {
        firestore.collection("pizzaCollection")
            .document()
            .set(hashMapOf("name" to "хуица"))
    }

    suspend fun readFromFireStore() {
        val await = firestore.collection("pizzaCollection")
//            .document("1")
            .get().await()
        await.toObjects(Pizza::class.java)
    }

    companion object {
        const val ROOT_PATH = "pizza"
    }

}