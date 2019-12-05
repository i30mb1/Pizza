package n7.pizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import n7.pizza.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding
    private var finishActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (finishActivity) {
            finish()
        } else {
            finishActivity = true

            Snackbar.make(binding.root, getString(R.string.all_press_again_for_exit), Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.bottomNav)
                .show()
            lifecycleScope.launch {
                delay(2000)
                finishActivity = false
            }
        }
    }

}
