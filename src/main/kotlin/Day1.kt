import util.readInputFileIntLines
import util.zipWithNextBy3

class Day1 {
    fun execute() {
        val inputNumbers = readInputFileIntLines(1)
        println(inputNumbers.zipWithNext().count { it.second > it.first })
        println(inputNumbers.zipWithNextBy3().count { it.second > it.first })
    }
}