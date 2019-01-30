package flaipperball.android.skools.flaipperball

import android.graphics.Canvas
import android.graphics.Paint

class Ball(val mass: Double, val radius: Float, var velocity: Vec2, var pos: Point, val gravity: Float,  val paint: Paint) {

    fun draw(canvas: Canvas, width: Int, height: Int, tileSize: Int) {

        val x = width / 2 + pos.x.toFloat() / 1000 * tileSize
        val y = height / 2 + pos.y.toFloat() / 1000 * tileSize
        canvas.drawCircle(x, y, radius / 1000 * tileSize, paint)
    }

    fun applyForces() {
        gravityForce()
        frictionForce(0.999)
    }

    fun gravityForce() {
        val force = vector(0.0, gravity.toDouble())
        val acc = force / mass

        velocity += acc
        pos += velocity

        // This should be removed with actual collision detecting
        if (pos.y > 14000) {
            velocity *= -1.0
        }
    }

    fun frictionForce(friction: Double) {
        velocity *= friction
    }
}