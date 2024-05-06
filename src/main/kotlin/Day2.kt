import java.io.File

object Day2 {
    private val cubes = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    @JvmStatic
    fun main(args: Array<String>) {
        // Part one
        var sum = 0
        File("src/main/resources/day2/input").useLines { lines ->
            lines.forEach { line ->
                var validGame = true
                val game = line.split(":")
                val id = game[0].filter { it.isDigit() }
                val rounds = game[1].split(";")
                rounds.forEach { round ->
                    val roundMap = cubes.toMutableMap()
                    val turns = round.split(",")
                    turns.forEach { turn ->
                        roundMap[turn.trim().substringAfter(" ")] =
                            roundMap[turn.trim().substringAfter(" ")]!! - turn.trim().substringBefore(" ").toInt()
                    }
                    roundMap.forEach { (_, v) -> if (v < 0) validGame = false }
                }
                if (validGame) sum += id.toInt()
            }
        }
        println(sum)

        // Part two
        var sum2 = 0
        File("src/main/resources/day2/input").useLines { lines ->
            lines.forEach { line ->
                val rounds = line.substringAfter(":").split(";")
                val turnMap = mutableMapOf(
                    "red" to 0,
                    "green" to 0,
                    "blue" to 0
                )
                rounds.forEach { round ->
                    round.split(",")
                        .forEach { turn ->
                            val (dig, name) = turn.trim().split(" ")
                            turnMap[name] = maxOf(turnMap[name]!!, dig.toInt())
                        }
                }
                sum2 += turnMap.values.reduce { acc, i -> acc * i }
            }

        }

        println(sum2)

    }
}