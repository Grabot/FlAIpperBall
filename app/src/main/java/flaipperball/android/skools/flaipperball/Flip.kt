package flaipperball.android.skools.flaipperball

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Flip(var pos: Array<Point>, val paint: Paint) {

    fun draw(canvas: Canvas, width: Int, height: Int, tileSize: Int) {

        val path = Path()
        path.moveTo(width / 2 + pos.get(0).x.toFloat() / 1000 * tileSize, height / 2 + pos.get(0).y.toFloat() / 1000 * tileSize)

        for (p in pos.slice(1 until pos.size)) {
            val x = width / 2 + p.x.toFloat() / 1000 * tileSize
            val y = height / 2 + p.y.toFloat() / 1000 * tileSize
            path.lineTo(x, y)
        }

        path.close()

        canvas.drawPath(path, paint)
    }
}