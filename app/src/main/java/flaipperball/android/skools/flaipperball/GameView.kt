package flaipperball.android.skools.flaipperball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import java.util.logging.Logger

class GameView(context : Context) : View(context){

    val LOG = Logger.getLogger(this.javaClass.name)

    val paint: Paint
    val gridPaint: Paint
    val ball: Ball

    val tileSizeRatio = 50
    var tileSize = 0

    var maxX = 0
    var maxY = 0

    init {
        paint = Paint()
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.BLUE

        ball = Ball(1.0, 1000.0f, vector(0.0, 0.0), point(0.0, 0.0), 9.81f, paint)

        gridPaint = Paint()
        gridPaint.color = Color.WHITE
        gridPaint.strokeWidth = resources.displayMetrics.density * 2
        gridPaint.style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawColor(Color.BLACK)

        ball.draw(canvas, width, height, tileSize)

//        drawGrid(canvas)
        step()
        invalidate()
    }

    fun step() {
        applyForces()
    }

    fun applyForces() {
        // Here we define, for instance, gravity affecting the playing field.
        ball.applyForces()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        tileSize = Math.max(width, height) / tileSizeRatio
        maxX = width / tileSize
        maxY = height / tileSize
    }

    private fun drawGrid(canvas: Canvas) {
        for (x in 0..maxX) {
            for (y in 0..maxY) {
                canvas.drawLine(0f, y.toFloat() * tileSize, width.toFloat(), y.toFloat() * tileSize, gridPaint)
            }
            canvas.drawLine(x.toFloat() * tileSize, 0f, x.toFloat() * tileSize, height.toFloat(), gridPaint)
        }
    }
}