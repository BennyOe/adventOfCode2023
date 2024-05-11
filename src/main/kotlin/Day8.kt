import java.io.File

object Day8 {
    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day8/input")
        val map = mutableMapOf<String, Pair<String, String>>()
        var count = 0
        var destination = "AAA"

        val directions = file.useLines {
            it.first().split("").filter { it.isNotEmpty() }.toList()
        }

        file.useLines {
            it.drop(2).forEach { line ->
                map[line.substringBefore(" =")] = Pair(line.substring(7, 10), line.substring(12, 15))
            }
        }

        while (destination != "ZZZ") {
            destination = if (directions[count % directions.size] == "L") {
                map[destination]!!.first
            } else {
                map[destination]!!.second
            }
            count++
        }
        println(count)
    }

    // 1. read first line in direction list
    // 2. read rest in Map<String, Pair<String, String>>
    // 3. get AAA
    // 4. While destination != ZZZ
    // take Pair 0 or 1 from direction L or R
    // increase count
    // direction count ++ % direction.size
}