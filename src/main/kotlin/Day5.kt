import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.count
import util.readInputFileLines
import java.awt.Point
import java.lang.Integer.min
import kotlin.math.max

class Day5 {
    data class Vent(val start: Point, val end: Point)

    fun execute() {
        var (width, height) = 0 to 0
        val vents = readInputFileLines(5).map { line ->
            line.split(" -> ").let {
                val (startX, startY) = it[0].split(",").map { value -> value.toInt() }
                val (endX, endY) = it[1].split(",").map { value -> value.toInt() }
                if (startX > width || endX > width) width = max(startX, endX)
                if (startY > height || endY > height) height = max(startY, endY)
                return@let Vent(Point(startX, startY), Point(endX, endY))
            }
        }
        val matrix = mk.zeros<Int>(width + 1, height + 1)
        vents.forEach { v ->
            if (v.start.x == v.end.x && v.start.y != v.end.y) {
                (min(v.start.y, v.end.y)..max(v.start.y, v.end.y)).forEach { matrix[v.start.x, it]++ }
            } else if (v.start.y == v.end.y && v.start.x != v.end.x) {
                (min(v.start.x, v.end.x)..max(v.start.x, v.end.x)).forEach { matrix[it, v.start.y]++ }
            } else {
                // Part 2
                val yRange = if (v.start.y < v.end.y) (v.start.y..v.end.y) else (v.start.y downTo v.end.y)
                val xRange = if (v.start.x < v.end.x) (v.start.x..v.end.x) else (v.start.x downTo v.end.x)
                xRange.forEachIndexed { index, it ->
                    matrix[it, yRange.toList()[index]]++
                }
            }
        }
        println(matrix.count { it >= 2 })
    }
}