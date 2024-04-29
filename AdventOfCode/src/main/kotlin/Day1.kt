import java.io.File

object Day1 {
    @JvmStatic
    fun main(args: Array<String>) {
        // Part one
        println(File("src/main/resources/day1/input").useLines { lines ->
            lines.sumOf { line ->
                line.filter { it.isDigit() }.let { "${it.first()}${it.last()}" }.toInt()
            }
        })
        // Part two
        println(File("src/main/resources/day1/input").useLines { lines ->
            lines.sumOf { line ->
                line.replaceSpelledDigits().filter { it.isDigit() }.let { "${it.first()}${it.last()}" }.toInt()
            }
        })
    }
}

private fun String.replaceSpelledDigits(): String {
    return replace("nine", "n9e")
        .replace("eight", "e8t")
        .replace("seven", "s7n")
        .replace("six", "s6x")
        .replace("five", "f5e")
        .replace("four", "f4r")
        .replace("three", "t3e")
        .replace("two", "t2o")
        .replace("one", "o1e")
}