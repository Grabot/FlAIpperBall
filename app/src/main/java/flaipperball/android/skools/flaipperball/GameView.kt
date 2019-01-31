package flaipperball.android.skools.flaipperball

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import java.util.logging.Logger

class GameView(context : Context) : View(context){

    val LOG = Logger.getLogger(this.javaClass.name)

    val paint: Paint
    val gridPaint: Paint
    val table: Table
    val ball: Ball
    val flipLeft: Flip
    val flipRight: Flip

    val tileSizeRatio = 30
    var tileSize = 0

    var maxX = 0
    var maxY = 0

    init {
        paint = Paint()
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.BLUE

        gridPaint = Paint()
        gridPaint.color = Color.WHITE
        gridPaint.strokeWidth = resources.displayMetrics.density * 2
        gridPaint.style = Paint.Style.STROKE

        ball = Ball(1.0, 1000.0f, vector(0.0, 0.0), point(0.0, 0.0), 9.81f, paint)
        val tablePoints = arrayOf(
            point(-10000.0, -14000.0),
            point(10000.0, -14000.0),
            point(10000.0, 14000.0),
            point(-10000.0, 14000.0)
        )
        table = Table(tablePoints, gridPaint)
        //simple box which would be the playground

        val flipLeftPoints = arrayOf(
            point(-2400.0, 0.0),
            point(-2250.0, 450.0),
            point(-1950.0, 600.0),
            point(1200.0, 450.0),
            point(2400.0, 300.0),
            point(2700.0, 150.0),
            point(2700.0, -150.0),
            point(2400.0, -300.0),
            point(1200.0, -450.0),
            point(-1950.0, -600.0),
            point(-2250.0, -450.0)
        )
        flipLeft = Flip(point(0.0, 0.0), 0.0f, flipLeftPoints, paint)
        val flipRightPoints = arrayOf(
            point(2400.0, 0.0),
            point(2250.0, 450.0),
            point(1950.0, 600.0),
            point(-1200.0, 450.0),
            point(-2400.0, 300.0),
            point(-2700.0, 150.0),
            point(-2700.0, -150.0),
            point(-2400.0, -300.0),
            point(-1200.0, -450.0),
            point(1950.0, -600.0),
            point(2250.0, -450.0)
        )
        flipRight = Flip(point(0.0, 0.0), 0.0f, flipRightPoints, paint)

        // Created the flippers correctly, now move them to the correct spot. We can do this immediately, but also like this.
        flipLeft.translate(point(-3200.0, 13000.0))
        flipRight.translate(point(3200.0, 13000.0))

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawColor(Color.BLACK)

        ball.draw(canvas, width, height, tileSize)
        flipLeft.draw(canvas, width, height, tileSize)
        flipRight.draw(canvas, width, height, tileSize)
        table.draw(canvas, width, height, tileSize)


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
//        flipLeft.rotate(1.0)
        flipLeft.rotate(-1.0, point(-2000.0, 0.0))
        flipRight.rotate(-1.0, point(2000.0, 0.0))
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