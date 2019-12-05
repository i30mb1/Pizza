package n7.pizza.util

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.math.floor

fun getErrorEmote(): String? {
    val emotes = arrayOf(
        "('.')",
        "('x')",
        "(>_<)",
        "(>.<)",
        "(;-;)",
        "\\(o_o)/",
        "(O_o)",
        "(o_0)",
        "(≥o≤)",
        "(≥o≤)",
        "(·.·)",
        "(·_·)"
    )
    return emotes.random()
}

/**
 * Converts Double to time. f.eks. 4.5 becomes "04"
 */
fun numberToTime(time: Double): String? {
    val timeInt = floor(time).toInt()
    return if (timeInt < 10) {
        "0$timeInt"
    } else {
        "" + timeInt
    }
}

/**
 * Creates a bitmap with rounded corners.
 *
 * @param bitmap The bitmap
 * @param i the corner radius in pixels
 * @return The bitmap with rounded corners
 */
fun getRoundedCornerBitmap(bitmap: Bitmap?, i: Int): Bitmap? {
    if (bitmap == null) {
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }
    val output = Bitmap.createBitmap(
        bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(output)
    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)
    val rectF = RectF(rect)
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawRoundRect(rectF, i.toFloat(), i.toFloat(), paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)
    return output
}

/**
 * Animates the background color of a view from one color to another color.
 *
 * @param v The view to animate
 * @param toColor The To Color
 * @param fromColor The From Color
 * @param duration The Duration of the animation
 * @return the animator
 */
fun animateBackgroundColorChange(
    v: View?,
    toColor: Int,
    fromColor: Int,
    duration: Long
): Animator? {
    val colorFade: ObjectAnimator =
        ObjectAnimator.ofObject(v, "backgroundColor", ArgbEvaluator(), fromColor, toColor)
    colorFade.duration = duration
    colorFade.start()
    return colorFade
}

/**
 * @param view The view to get the color from
 * @param defaultColor The color to return if the view's background isn't a ColorDrawable
 * @return The color
 */
fun getBackgroundColorFromView(view: View, defaultColor: Int): Int {
    var color = defaultColor
    val background = view.background
    if (background is ColorDrawable) {
        color = background.color
    }
    return color
}

/**
 * Decodes a byte array to a bitmap and returns it.
 */
fun getBitmapFromByteArray(bytes: ByteArray): Bitmap? {
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

/**
 * Hides the onscreen keyboard if it is visisble
 */
fun hideKeyboard(activity: Activity) { // Check if no view has focus:
    val view = activity.currentFocus
    if (view != null) {
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/**
 * Shows the soft keyboard
 */
fun showKeyboard(activity: Activity) { // Check if no view has focus:
    val view = activity.currentFocus
    if (view != null) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(
            view.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0
        )
    }
}

fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun saveImageToStorage(
    image: Bitmap,
    key: String?,
    context: Context
) {
    try { // Create an ByteArrayOutputStream and feed a compressed bitmap image in it
        val byteStream = ByteArrayOutputStream()
        image.compress(
            Bitmap.CompressFormat.PNG, 100, byteStream
        ) // PNG as only format with transparency
        // Create a FileOutputStream with out key and set the mode to private to ensure
// Only this app and read the file. Write out ByteArrayOutput to the file and close it
        val fileOut: FileOutputStream = context.openFileOutput(key, Context.MODE_PRIVATE)
        fileOut.write(byteStream.toByteArray())
        byteStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getImageFromStorage(key: String?, context: Context): Bitmap? {
    val fileIn: InputStream = context.openFileInput(key)
    return BitmapFactory.decodeStream(fileIn)
}

fun doesStorageFileExist(
    key: String?,
    context: Context
): Boolean {
    val file: File = context.getFileStreamPath(key)
    return file.exists()
}
