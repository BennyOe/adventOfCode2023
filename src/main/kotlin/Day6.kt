import java.io.File

object Day6 {
    @JvmStatic
    fun main(args: Array<String>) {
        val inputFile = File("src/main/resources/day6/input")

        partOne(inputFile)
        partTwo(inputFile)
    }

    private fun partTwo(inputFile: File) {
        var waysToWin = 0
        val (time, distance) = inputFile.readLines()
            .map {
                it.substringAfter(":")
                    .trim()
                    .replace(" ", "")
                    .toLong()
            }
        for (charge in 0..time) {
            val timeLeft = time - charge
            val myDistance = timeLeft * charge
            if (myDistance > distance)
                waysToWin++
        }
        println("Part Two: $waysToWin")
    }

}

private fun partOne(inputFile: File) {

    var waysToWin = 1
    val (timeList, distanceList) = inputFile.readLines()
        .map {
            it.substringAfter(":")
                .trim()
                .split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
        }

    for ((index, race) in timeList.withIndex()) {
        var raceWaysToWin = 0
        for (charge in 0..race) {
            val timeLeft = race - charge
            val distance = timeLeft * charge
            if (distance > distanceList[index])
                raceWaysToWin++
        }
        waysToWin *= raceWaysToWin
    }
    println("Part One: $waysToWin")
}