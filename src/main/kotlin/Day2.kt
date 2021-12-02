import util.readInputFileLines

class Day2 {
    data class Submarine(val movement: SubmarineMovement, var depth: Int = 0, var position: Int = 0, var aim: Int = 0)

    interface SubmarineMovement {
        fun applyForward(submarine: Submarine, value: Int)
        fun applyUp(submarine: Submarine, value: Int)
        fun applyDown(submarine: Submarine, value: Int)
    }

    private class SubmarineMovement1 : SubmarineMovement {
        override fun applyForward(submarine: Submarine, value: Int) {
            submarine.position += value
        }

        override fun applyUp(submarine: Submarine, value: Int) {
            submarine.depth -= value
        }

        override fun applyDown(submarine: Submarine, value: Int) {
            submarine.depth += value
        }
    }

    private class SubmarineMovement2 : SubmarineMovement {
        override fun applyForward(submarine: Submarine, value: Int) {
            submarine.position += value
            submarine.depth += submarine.aim * value
        }

        override fun applyUp(submarine: Submarine, value: Int) {
            submarine.aim -= value
        }

        override fun applyDown(submarine: Submarine, value: Int) {
            submarine.aim += value
        }
    }

    fun execute() {
//        val submarine = Submarine(SubmarineMovement1())
        val submarine = Submarine(SubmarineMovement2())
        readInputFileLines(2).map { line ->
            line.split(" ").let {
                val value = it[1].toInt()
                when (it[0]) {
                    "forward" -> submarine.movement.applyForward(submarine, value)
                    "up" -> submarine.movement.applyUp(submarine, value)
                    "down" -> submarine.movement.applyDown(submarine, value)
                }
            }
        }
        println(submarine.depth * submarine.position)
    }
}