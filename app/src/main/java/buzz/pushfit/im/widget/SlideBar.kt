package buzz.pushfit.im.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.sp

/**
 * Created by yuequan on 2017/10/28.
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    var sectionHeight = 0f
    val paint = Paint()

    companion object {
        private val SECTIONS = arrayOf(
                "A", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //计算每个字符的高度
        sectionHeight = h / SECTIONS.size*1.0f
    }
    init {
        paint.apply {
            color=Color.WHITE
            textSize=sp(12).toFloat()
            textAlign=Paint.Align.CENTER
        }

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制字母
        val x = width *1.0f/ 2//起始位置x
        var y = sectionHeight //起始位置y
        SECTIONS.forEach {
            canvas.drawText(it, x, y,paint)
            y+=sectionHeight
        }

    }
}