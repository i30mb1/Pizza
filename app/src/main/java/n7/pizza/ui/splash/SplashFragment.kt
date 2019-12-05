package n7.pizza.ui.splash

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.transition.doOnEnd
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import n7.pizza.MainActivity.Companion.DURATION_ANIMATION
import n7.pizza.MainActivity.Companion.START_DELAY_ANIMATION
import n7.pizza.R
import n7.pizza.databinding.SplashFragmentBinding
import n7.pizza.util.animateBackgroundColorChange
import n7.pizza.util.getColorFromAttr

class SplashFragment : Fragment() {

    lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.root.doOnPreDraw {
            lifecycleScope.launchWhenResumed {
                beginAnimationPizza()
            }
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

                    binding.ivLogo.animate().rotation(-100F).setDuration(600).start()
                    binding.tvLogo.visibility = View.VISIBLE
                }
            })
        }

        TransitionManager.beginDelayedTransition(binding.root as ViewGroup, set)

        animateBackgroundColorChange(
            binding.view,
            requireContext().getColorFromAttr(R.attr.colorOnPrimary),
            requireContext().getColorFromAttr(R.attr.myBackgroundColor),
            DURATION_ANIMATION
        )
        binding.ivLogoBot.visibility = View.VISIBLE
        binding.ivLogoBotRight.visibility = View.VISIBLE
        binding.ivLogoBotLeft.visibility = View.VISIBLE
        binding.ivLogoTop.visibility = View.VISIBLE
        binding.ivLogoTopRight.visibility = View.VISIBLE
        binding.ivLogoTopLeft.visibility = View.VISIBLE
    }
}
