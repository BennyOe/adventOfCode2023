import java.io.File

object Day1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val textList: List<String> = File("src/main/resources/day1/input").useLines { it.toList() }
        var res = 0
        println("Hallo Jan")

        val digitList = textList.forEach { line ->
            val result = line.filter { it.isDigit() }
                val pair = "${result.first()}${result.last()}"
                res += pair.reduce { acc, c -> acc + c.code }
                .code
        }
        println(res)
    }
}