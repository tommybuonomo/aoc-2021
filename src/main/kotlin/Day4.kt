import util.readInputFileLines
import util.swapped

class Day4 {
    data class BingoNum(val num: Int, var marked: Boolean = false)
    data class BingoBoard(private val rows: List<List<BingoNum>>) {
        private val flatten = rows.flatten()
        private val columns get() = rows.swapped()
        fun won() = rows.any { row -> row.all { it.marked } } ||
                columns.any { column -> column.all { it.marked } }

        val unmarkedNumbersSum get() = flatten.sumOf { if (!it.marked) it.num else 0 }
        fun markNumber(number: Int) {
            flatten.filter { it.num == number }.forEach { it.marked = true }
        }
    }

    fun execute() {
        val input = readInputFileLines(4)
        val randomNumbers = input[0].split(",").map { it.toInt() }

        val boards = input.subList(2, input.size).windowed(5, step = 6) { board ->
            BingoBoard(board.map { boardLine ->
                boardLine.windowed(2, 3).map {
                    BingoNum(it.trim().toInt())
                }
            })
        }.toMutableList()


        // Part 1
//        randomNumbers.forEach { playedNumber ->
//            boards.firstOrNull {
//                it.markNumber(playedNumber)
//                it.won()
//            }?.let {
//                println(it.unmarkedNumbersSum * playedNumber)
//            }
//        }

        // Part 2
        val boardSize = boards.size
        val winners = mutableListOf<BingoBoard>()
        randomNumbers.map { playedNumber ->
            boards.filter {
                it.markNumber(playedNumber)
                it.won()
            }.also {
                boards.removeAll(it)
                winners.addAll(it)
            }
            if (winners.size == boardSize) {
                println(winners.last().unmarkedNumbersSum * playedNumber)
                return
            }
        }
    }
}