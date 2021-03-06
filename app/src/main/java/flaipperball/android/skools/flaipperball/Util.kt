package flaipperball.android.skools.flaipperball


fun point(x: Double, y: Double): Point {
    return Point(x, y)
}

data class Point(var x: Double, var y: Double) {

    fun distanceTo(p: Point): Double {
        return Math.sqrt(Math.pow((x - p.x), 2.0) + Math.pow((y - p.y), 2.0))
    }

    operator fun plus(vec: Vec2): Point {
        return Point(x + vec.x, y + vec.y)
    }

    operator fun plus(p: Point): Point {
        return Point(x + p.x, y + p.y)
    }

    operator fun div(a: Double): Point {
        return Point(x / a, y / a)
    }
    operator fun times(a: Double): Point {
        return Point(x * a, y * a)
    }
}

fun zeroPoint(): Point {
    return Point(0.0, 0.0)
}

fun vector(x: Double, y: Double): Vec2 {
    return vector(point(x, y))
}

fun vector(p: Point): Vec2 {
    return Vec2(p)
}

fun zeroVector(): Vec2 {
    return Vec2(zeroPoint())
}

data class Vec2(val p: Point) {
    val x = p.x
    val y = p.y

    operator fun plus(vec: Vec2): Vec2 {
        return Vec2(p + vec.p)
    }

    operator fun div(a: Double): Vec2 {
        return Vec2(p / a)
    }

    operator fun times(a: Double): Vec2 {
        return Vec2(p * a)
    }
}

fun rotateAPoint(point: Point, center: Point, degree: Double): Point {
    val angle = Math.toRadians(degree)

    // difference
    var x1 = point.x - center.x
    var y1 = point.y - center.y

    // rotation
    x1 = x1 * Math.cos(angle) - y1 * Math.sin(angle)
    y1 = x1 * Math.sin(angle) + y1 * Math.cos(angle)

    val newX = x1 + center.x
    val newY = y1 + center.y

    return point(newX, newY)
}
