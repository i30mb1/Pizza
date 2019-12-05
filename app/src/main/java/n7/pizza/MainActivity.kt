package n7.pizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var fadeIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            fadeIn = true
        }
//        containerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
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

    companion object {
        const val START_DELAY_ANIMATION = 600L
        const val DURATION_ANIMATION = 1000L
    }
}
