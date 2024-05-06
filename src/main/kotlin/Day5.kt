import java.io.File
import kotlin.math.min

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

        // maps are {start, {range, destination}}
        seedToSoil = extractMap(textList[1])
        soilToFertilizer = extractMap(textList[2])
        fertilizerToWater = extractMap(textList[3])
        waterToLight = extractMap(textList[4])
        lightToTemp = extractMap(textList[5])
        tempToHumidity = extractMap(textList[6])
        humidityToLocation = extractMap(textList[7])

//        calcPartOne(textList)
        calcPartTwo(textList)

    }

    private fun calcPartOne(textList: List<String>): Unit {
        seeds = textList[0].substringAfter(":").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        var result = calcStartToDest(seedToSoil, seeds)
//        println("seedToSoil $result")
        result = calcStartToDest(soilToFertilizer, result)
//        println("soilToFertilizer $result")
        result = calcStartToDest(fertilizerToWater, result)
//        println("fertilizerToWater $result")
        result = calcStartToDest(waterToLight, result)
//        println("waterToLight $result")
        result = calcStartToDest(lightToTemp, result)
//        println("lightToTemp $result")
        result = calcStartToDest(tempToHumidity, result)
//        println("tempToHumidity $result")
        result = calcStartToDest(humidityToLocation, result)
        println("result ${result.min()}")

    }

    private fun calcPartTwo(textList: List<String>): Unit {
        var lowestNumber = Long.MAX_VALUE
        seeds = textList[0].substringAfter(":").split(" ").filter { it.isNotBlank() }.map { it.toLong() }

        for (seedPair in seeds.indices step 2) {
            val seedRanges = getSeedRanges(
                Pair(seeds[seedPair], seeds[seedPair + 1])
            )

            var result = calcStartToDest(seedToSoil, seedRanges)
            result = calcStartToDest(soilToFertilizer, result)
            result = calcStartToDest(fertilizerToWater, result)
            result = calcStartToDest(waterToLight, result)
            result = calcStartToDest(lightToTemp, result)
            result = calcStartToDest(tempToHumidity, result)
            result = calcStartToDest(humidityToLocation, result)
            lowestNumber = min(lowestNumber, result.min())
        }
        println("result $lowestNumber")
    }

    private fun getSeedRanges(seedPair: Pair<Long, Long>): List<Long> {
        val result = mutableListOf<Long>()
        for (seed in seedPair.first until seedPair.first + seedPair.second) {
            result.add(seed)
        }
        return result
    }

    private fun calcStartToDest(map: Map<Long, Map<Long, Long>>, seedList: List<Long>): List<Long> {
        val result = mutableListOf<Long>()
        for (seed in seedList) {
            var changed = false
            // check if seed is in range
            for ((k, v) in map) {
                if (seed >= k && seed <= k + v.keys.first()) {
                    // yes -> get seed - source start -> return destination + delta
                    result.add(v.values.first() + (seed - k))
                    changed = true
                }
            }
            // no -> return seed
            if (!changed) result.add(seed)
        }
        return result
    }

    private fun extractMap(mapString: String): MutableMap<Long, Map<Long, Long>> {
        val resultMap = mutableMapOf<Long, Map<Long, Long>>()
        val coordinatesList = mapString.substringAfter(":").lines().filter { line ->
            line.any { it.isDigit() }
        }.map { line ->
            line.split(" ").map { it.toLong() }.toList()
        }

        for (i in coordinatesList.indices) {
            resultMap[coordinatesList[i][1]] = mapOf(coordinatesList[i][2] to coordinatesList[i][0])
        }

        return resultMap
    }
}