import java.io.File

object Day5 {
    val seeds: List<Int> = mutableListOf()
    val seedToSoil: Map<Int,Map<Int,Int>> = mutableMapOf()
    val soilToFertilizer: Map<Int,Map<Int,Int>> = mutableMapOf()
    val fertilizerToWater: Map<Int,Map<Int,Int>> = mutableMapOf()
    val waterToLight: Map<Int,Map<Int,Int>> = mutableMapOf()
    val lightToTemp: Map<Int,Map<Int,Int>> = mutableMapOf()
    val tempToHumidity: Map<Int,Map<Int,Int>> = mutableMapOf()
    val humidityToLocation: Map<Int,Map<Int,Int>> = mutableMapOf()

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day5/input")
        val text = file.readText()
        println(text.split("\\s*\\n\\s*\\n\\s*".toRegex())[1])
    }
}