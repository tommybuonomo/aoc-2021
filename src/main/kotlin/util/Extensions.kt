package util

import java.io.File

fun readInputFileLines(dayNumber: Int) = File("inputs/$dayNumber.txt").readLines()
fun readInputFileIntLines(dayNumber: Int) = readInputFileLines(dayNumber).map { it.toInt() }

fun List<Int>.toIntByBytes() = Integer.parseInt(this.joinToString(separator = ""), 2)

fun <T> List<List<T>>.swapped(): List<List<T>> {
    val columnSize = get(0).size
    return mutableListOf<List<T>>().also { row ->
        for (i in 0 until columnSize) {
            row.add(mutableListOf<T>().also { column ->
                for (j in indices) {
                    column.add(this@swapped[j][i])
                }
            })
        }
    }
}