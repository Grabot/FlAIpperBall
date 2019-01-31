package flaipperball.android.skools.flaipperball

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Flip(var pos: Point, var rotate: Float, var points: Array<Point>, val paint: Paint) {

    fun draw(canvas: Canvas, width: Int, height: Int, tileSize: Int) {

        val path = Path()
        path.moveTo(
            (width / 2 + ((points.get(0).x.toFloat() / 1000 * tileSize) + (pos.x / 1000)* tileSize)).toFloat(),
            (height / 2 + (points.get(0).y.toFloat() / 1000 * tileSize) + (pos.y / 1000)* tileSize).toFloat())

        for (p in points.slice(1 until points.size)) {
            val x = (width / 2 + (p.x.toFloat() / 1000 * tileSize) + (pos.x / 1000)* tileSize).toFloat()
            val y = (height / 2 + (p.y.toFloat() / 1000 * tileSize) + (pos.y / 1000) * tileSize).toFloat()
            path.lineTo(x, y)
        }

        path.close()

        canvas.drawPath(path, paint)
    }

    fun translate(p: Point) {
        pos += p
    }

    fun rotate(degree: Double, center: Point) {
        for (p in points) {
            val rot = rotateAPoint(p, center, degree)
            p.x = rot.x
            p.y = rot.y
        }
    }

}