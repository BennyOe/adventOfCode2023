import java.io.File
import kotlin.math.pow

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        var sum = 0.0
        File("src/main/resources/day4/input").useLines { lines ->
            lines.forEach { line ->
                val winningNumbers = line.substringAfter(":").substringBefore("|").trim().split("\\s+".toRegex())
                    .filter { it.matches("\\d+".toRegex()) }.toSet()
                val pickedNumbers =
                    line.substringAfter("|").trim().split("\\s+".toRegex()).filter { it.matches("\\d+".toRegex()) }
                        .toSet()
                val score = winningNumbers.intersect(pickedNumbers).size
                if (score > 0) sum += 2.0.pow(score - 1)
            }
        }
        println(sum)
    }
}