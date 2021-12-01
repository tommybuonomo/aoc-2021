import util.readInputFileIntLines

class Day1 {
    fun execute() {
        val inputNumbers = readInputFileIntLines(1)
        println(inputNumbers.zipWithNext().count { it.second > it.first })
        println(inputNumbers.windowed(3, 1).zipWithNext().count { it.second.sum() > it.first.sum() })
    }
}