package n7.pizza.ui.pizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import n7.pizza.databinding.FragmentPizzasBinding
import n7.pizza.di.injector
import n7.pizza.util.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PizzasFragment : Fragment() {

    private lateinit var binding: FragmentPizzasBinding
    private val viewmodel by viewModel {
        injector.mainViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzasBinding.inflate(layoutInflater, container, false)

        viewmodel
        return binding.root
    }

}
