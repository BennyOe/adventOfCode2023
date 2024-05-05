import java.io.File

object Day5 {
    private var seeds: List<Long> = mutableListOf()
    private var seedToSoil = mutableMapOf<Long, Map<Long, Long>>()
    private var soilToFertilizer = mutableMapOf<Long, Map<Long, Long>>()
    private var fertilizerToWater = mutableMapOf<Long, Map<Long, Long>>()
    private var waterToLight = mutableMapOf<Long, Map<Long, Long>>()
    private var lightToTemp = mutableMapOf<Long, Map<Long, Long>>()
    private var tempToHumidity = mutableMapOf<Long, Map<Long, Long>>()
    private var humidityToLocation = mutableMapOf<Long, Map<Long, Long>>()

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day5/input")
        val text = file.readText()
        val textList = text.split("\\s*\\n\\s*\\n\\s*".toRegex())

        seeds = textList[0].substringAfter(":").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        seedToSoil = extractMap(textList[1])
        soilToFertilizer = extractMap(textList[2])
        fertilizerToWater = extractMap(textList[3])
        waterToLight = extractMap(textList[4])
        lightToTemp = extractMap(textList[5])
        tempToHumidity = extractMap(textList[6])
        humidityToLocation = extractMap(textList[7])

    }

    private fun extractMap(mapString: String): MutableMap<Long, Map<Long, Long>> {
        val resultMap = mutableMapOf<Long, Map<Long, Long>>()
        val coordinatesList = mapString.substringAfter(":").lines().filter { line ->
            line.any { it.isDigit() }
        }.map { line ->
            line.split(" ").map { it.toLong() }.toList()
        }

        for (i in coordinatesList.indices) {
            resultMap[coordinatesList[i][0]] = mapOf(coordinatesList[i][1] to coordinatesList[i][2])
        }

        return resultMap
    }
}