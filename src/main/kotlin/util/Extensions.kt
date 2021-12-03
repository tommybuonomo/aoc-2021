package util

import java.io.File

fun readInputFileLines(dayNumber: Int) = File("inputs/$dayNumber.txt").readLines()
fun readInputFileIntLines(dayNumber: Int) = readInputFileLines(dayNumber).map { it.toInt() }

fun List<Int>.toIntByBytes() = Integer.parseInt(this.joinToString(separator = ""), 2)