import java.io.File
import kotlin.math.pow

object Day4 {
    val file = File("src/main/resources/day4/input")

    @JvmStatic
    fun main(args: Array<String>) {
        println("Part one ${partOne()}")
        println("Part two ${partTwo()}")
    }

    private fun partOne(): Int {
        var sum = 0.0
        file.useLines { lines ->
            lines.forEach { line ->
                val score = getWinningNumbers(line).intersect(getPickedNumbers(line)).size
                if (score > 0) sum += 2.0.pow(score - 1)
            }
        }
        return sum.toInt()
    }

    private fun partTwo(): Int {
        val cards = mutableMapOf<Int, Int>()
        file.useLines { lines ->
            lines.forEachIndexed { i, line ->
                val score = getWinningNumbers(line).intersect(getPickedNumbers(line)).size
                if (!cards.containsKey(i)) cards[i] = 1
                else cards[i] = cards.getValue(i) + 1

                repeat (cards.getValue(i)) {
                    for (n in 1 .. score) {
                        if (!cards.containsKey(i + n)) cards[i + n] = 1
                        else cards[i + n] = cards.getValue(i + n) + 1
                    }
                }
            }
        }
        return cards.values.sumOf { it }
    }

    private fun getPickedNumbers(line: String): Set<String>{
        return line.substringAfter("|").trim().split("\\s+".toRegex()).filter { it.matches("\\d+".toRegex()) }
            .toSet()
    }

    private fun getWinningNumbers(line: String): Set<String> {
        return line.substringAfter(":").substringBefore("|").trim().split("\\s+".toRegex())
            .filter { it.matches("\\d+".toRegex()) }.toSet()
    }
}