package n7.pizza.ui.pizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import n7.pizza.databinding.FragmentPizzasBinding

/**
 * A simple [Fragment] subclass.
 */
class PizzasFragment : Fragment() {

    lateinit var binding: FragmentPizzasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPizzasBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}
