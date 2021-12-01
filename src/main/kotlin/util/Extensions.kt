package util

import java.io.File

fun List<Int>.zipWithNextBy3(): List<Pair<Int, Int>> {
    if (size < 3) throw IllegalStateException("Not normal bro")
    val pairs = mutableListOf<Pair<Int, Int>>()
    forEachIndexed { index, t ->
        if (index + 3 < size) {
            val firstSum = t + get(index + 1) + get(index + 2)
            val secondSum = get(index + 1) + get(index + 2) + get(index + 3)
            pairs.add(Pair(firstSum, secondSum))
        }
    }
    return pairs
}

fun readInputFileLines(dayNumber: Int) = File("inputs/$dayNumber.txt").readLines()
fun readInputFileIntLines(dayNumber: Int) = readInputFileLines(dayNumber).map { it.toInt() }