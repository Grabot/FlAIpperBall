package flaipperball.android.skools.flaipperball

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Table(val paint: Paint) {


    //simple box which would be the playground
    val tablePoints = arrayOf(
        point(10000.0, -14000.0),
        point(10000.0, 14000.0),
        point(-10000.0, 14000.0),
        point(-10000.0, -14000.0)
    )

    fun draw(canvas: Canvas, width: Int, height: Int, tileSize: Int) {

        val path = Path()
        path.moveTo(width / 2 + tablePoints.get(0).x.toFloat() / 1000 * tileSize, height / 2 + -tablePoints.get(0).y.toFloat() / 1000 * tileSize)

        for (p in tablePoints.slice(1 until tablePoints.size)) {
            val x = width / 2 + p.x.toFloat() / 1000 * tileSize
            val y = height / 2 + -p.y.toFloat() / 1000 * tileSize
            path.lineTo(x, y)
        }

        path.close()

//        for (f in 0..9)
//            canvas.drawCircle ((width / 2) .toFloat (), (hieght / 2) .toFloat (), (f * 15) .toFloat (), brush1)

        canvas.drawPath(path, paint)
    }
}