import util.readInputFileLines
import util.toIntByBytes

class Day3 {
    fun part1() {
        val bytes = readInputFileLines(3).map { line ->
            line.map { it.digitToInt() }
        }.reduce { acc, bit ->
            acc.mapIndexed { index, value -> value + (if (bit[index] == 0) -1 else 1) }
        }.map { if (it > 0) 1 else 0 }.toIntByBytes()
        println(bytes * (bytes xor 0xFFF))
    }

    fun part2() {
        val lines = readInputFileLines(3).map { line -> line.map { it.digitToInt() } }
        println(filter(0, lines, true) * filter(0, lines, false))
    }

    private fun filter(index: Int, lines: List<List<Int>>, byMax: Boolean): Int {
        if (lines.size == 1) return lines.first().toIntByBytes()
        val partition = lines.partition { it[index] == 0 }
        println(partition.first)
        println(partition.second)
        val newLines = if (byMax) {
            maxOf(partition.second, partition.first, compareBy { it.size })
        } else {
            minOf(partition.first, partition.second, compareBy { it.size })
        }
        return filter(index + 1, newLines, byMax)
    }
}