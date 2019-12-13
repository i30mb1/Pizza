package n7.pizza

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.transition.doOnEnd
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import n7.pizza.databinding.ActivitySplashBinding
import n7.pizza.util.animateBackgroundColorChange
import n7.pizza.util.getColorFromAttr

class SplashActivity : AppCompatActivity() {

    private var fadeIn = false
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        if (savedInstanceState == null) {
            fadeIn = true
        }
        binding.root.doOnPreDraw {
            lifecycleScope.launchWhenResumed {
                beginAnimationPizza()
            }
        }
        binding.root.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        binding.ivLogo.setOnClickListener {
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        if (fadeIn) {
            // Note: normally should use window animations for this, but there's a bug
            // on Samsung devices where the wallpaper is animated along with the window for
            // windows showing the wallpaper (the wallpaper _should_ be static, not part of
            // the animation).
            window.decorView.run {
                alpha = 0f
                animate().cancel()
                animate().setStartDelay(START_DELAY_ANIMATION).alpha(1f).duration = DURATION_ANIMATION
            }
            fadeIn = false
        }
    }

    private fun beginAnimationPizza() {
        val set = TransitionSet().apply {
            duration = 150
            startDelay = START_DELAY_ANIMATION
            ordering = TransitionSet.ORDERING_SEQUENTIAL
            interpolator = DecelerateInterpolator()

            addTransition(Slide(Gravity.BOTTOM).apply {
                addTarget(binding.ivLogoBot)
            })
            addTransition(Slide(Gravity.TOP).apply {
                addTarget(binding.ivLogoTop)
            })
            addTransition(Slide(Gravity.END).apply {
                addTarget(binding.ivLogoTopRight)
            })
            addTransition(Slide(Gravity.END).apply {
                addTarget(binding.ivLogoBotRight)
            })
            addTransition(Slide(Gravity.START).apply {
                addTarget(binding.ivLogoTopLeft)
            })
            addTransition(Slide(Gravity.START).apply {
                addTarget(binding.ivLogoBotLeft)
                doOnEnd {
                    binding.ivLogo.visibility = View.VISIBLE
                    TransitionManager.beginDelayedTransition(binding.root as ViewGroup)

                    binding.ivLogo.animate().rotation(-100F).setDuration(DURATION_ANIMATION / 2).start()
                    binding.tvLogo.visibility = View.VISIBLE
                    lifecycleScope.launch {
                        delay(DELAY_BEFORE_FINISH_ACTIVITY)
                        finish()
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    }
                }
            })
        }

        TransitionManager.beginDelayedTransition(binding.root as ViewGroup, set)

        animateBackgroundColorChange(
            binding.view,
            getColorFromAttr(R.attr.colorOnPrimary),
            getColorFromAttr(R.attr.myBackgroundColor),
            DURATION_ANIMATION
        )
        binding.ivLogoBot.visibility = View.VISIBLE
        binding.ivLogoBotRight.visibility = View.VISIBLE
        binding.ivLogoBotLeft.visibility = View.VISIBLE
        binding.ivLogoTop.visibility = View.VISIBLE
        binding.ivLogoTopRight.visibility = View.VISIBLE
        binding.ivLogoTopLeft.visibility = View.VISIBLE
    }

    companion object {
        const val DELAY_BEFORE_FINISH_ACTIVITY = 1000L
        const val START_DELAY_ANIMATION = 600L
        const val DURATION_ANIMATION = 1000L
    }
}
