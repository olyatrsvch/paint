package com.example.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.paint.MainActivity.Companion.isRandomMode
import com.example.paint.MainActivity.Companion.isReplaceMode
import com.example.paint.MainActivity.Companion.paintBrush
import com.example.paint.MainActivity.Companion.path
import java.lang.Math.random
import kotlin.math.absoluteValue

class PaintView : View {

    var params: ViewGroup.LayoutParams? = null
    var initX = 0f
    var initY = 0f
    private val colors = listOf(Color.WHITE, Color.CYAN, Color.MAGENTA, Color.LTGRAY, Color.GRAY)

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        with(paintBrush) {
            isAntiAlias = true
            color = currentBrush
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeWidth = 10f
        }
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        super.onTouchEvent(event)

        val x = event.x
        val y = event.y

        if (isReplaceMode) {

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    path.fillType

                    initX = x
                    initY = y
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    path.offset(x - initX, y - initY)

                    initX = x
                    initY = y
                }
            }

        } else {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    path.moveTo(x, y)
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    path.lineTo(x, y)
                    pathList.add(path)
                    colorList.add(
                        if (isRandomMode) colors.random() else currentBrush
                    )
                }
                else -> return false
            }
        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)

        for (i in pathList.indices) {
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }

    }


    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK
    }
}