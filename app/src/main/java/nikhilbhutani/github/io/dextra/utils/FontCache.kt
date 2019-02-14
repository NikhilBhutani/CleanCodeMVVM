package nikhilbhutani.github.io.dextra.utils

import android.content.Context
import android.graphics.Typeface
import nikhilbhutani.github.io.dextra.R
import java.util.*


object FontCache {

    private val fontCache = HashMap<String, Typeface>()

    fun getTypeface(context: Context, fontName: String): Typeface? {
        var typeface: Typeface? = fontCache[fontName]
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(
                    context.assets,
                    getFilePath(context, fontName)
                )
            } catch (e: Exception) {
                return null
            }

            fontCache[fontName] = typeface
        }
        return typeface
    }

    private fun getFilePath(context: Context, fontName: String?): String {
        return if (fontName == null) {
            "fonts/JosefinSans-Light.ttf"
        } else if (fontName.contentEquals(context.getString(R.string.font_josefin_light))) {
            "fonts/JosefinSans-Light.ttf"
        } else if (fontName.contentEquals(context.getString(R.string.font_josefin_light))) {
            "fonts/JosefinSans-Regular.ttf"
        } else if (fontName.contentEquals(context.getString(R.string.font_josefin_bold))) {
            "fonts/JosefinSans-Bold.ttf"
        } else if (fontName.contentEquals(context.getString(R.string.font_josefin_regular))) {
            "fonts/JosefinSans-SemiBold.ttf"
        } else {
            // no matching font found
            "fonts/JosefinSans-Regular.ttf"
        }
    }

}
