import util.readInputFileLines
import java.util.concurrent.atomic.AtomicInteger

class Day6 {

    fun execute() {
        val initialState = readInputFileLines(6)[0].split(",").map { it.toInt() }.toMutableList().groupBy { it }
            .mapValues { it.value.size.toLong() }
        val day = AtomicInteger(0)

        fun step(state: Map<Int, Long>) {
            if (day.getAndIncrement() == 256) {
                println(state.values.sum())
                return
            }
            val newState = state.toMutableMap().apply {
                (1..8).forEach { index ->
                    this[index - 1] = (state[index] ?: 0)
                }
                val numberToAdd = state[0] ?: 0
                this[8] = numberToAdd
                this[6] = (this[6] ?: 0) + numberToAdd
            }
            step(newState)
        }
        step(initialState)
    }
}