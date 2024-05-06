import java.io.File

object Day7 {
    private val cards = mutableMapOf<String, Int>()

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day7/inputTest")
        file.readLines().map { line -> line.split(" ") }.map { (k,v) -> cards[k] = v.toInt() }

        println(" $cards")
    }
}