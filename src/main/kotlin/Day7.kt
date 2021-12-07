import util.readInputFileLines
import kotlin.math.absoluteValue

class Day7 {
    fun execute() {
        val positions = readInputFileLines(7)[0].split(",").map { it.toInt() }
        val max = positions.maxOf { it }

        // Part 1
        println((1..max).map { finalPosition -> positions.sumOf { (it - finalPosition).absoluteValue } }.minOf { it })

        // Part 2
        println((1..max).map { finalPosition -> positions.sumOf { (it - finalPosition).absoluteValue.triangleNumber() } }
            .minOf { it })
    }

    private fun Int.triangleNumber() = (this * (this + 1)) / 2
}