import java.io.File

object Day3 {
    private val matrix = mutableListOf<MutableList<Char>>()

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day3/input")
        for (line in file.readLines()) {
            val row = mutableListOf<Char>()
            for (char in line) {
                row.add(char)
            }
            matrix.add(row)
        }

        partOne(matrix)
        partTwo(matrix)
    }

    private fun partOne(matrix: MutableList<MutableList<Char>>) {
        var numberStorage = 0
        var hasSymbolNeighbours = false
        var sum = 0

        for (row in matrix.indices) {
            for (column in matrix[row].indices) {
                if (matrix[row][column].isDigit()) {
                    hasSymbolNeighbours = hasSymbolNeighbours || checkNeighbours(row, column)
                    numberStorage = numberStorage * 10 + matrix[row][column].digitToInt()
                } else {
                    if (numberStorage != 0 && hasSymbolNeighbours) {
                        sum += numberStorage
                    }
                    numberStorage = 0
                    hasSymbolNeighbours = false
                }
            }
        }
        println("Part one $sum")
    }

    private fun checkNeighbours(row: Int, column: Int): Boolean {
        var hasNeighbours = false
        for (subrow in row - 1..row + 1) {
            if (subrow >= 0 && subrow < matrix.size) {
                for (field in column - 1..column + 1) {
                    if (field >= 0 && field < matrix[0].size) {
                        if (Regex("[^0-9.]").matches(matrix[subrow][field].toString())) hasNeighbours = true
                    }
                }
            }
        }
        return hasNeighbours
    }

    private fun partTwo(matrix: MutableList<MutableList<Char>>) {
        // TODO test Input is working. Input not
        var sum = 0

        for (row in matrix.indices) {
            for (column in matrix[row].indices) {
                if (matrix[row][column] == '*') {
                    val numbers: MutableSet<Int> = checkNumbersAtStar(row, column)
                    if (numbers.size == 2)
                        sum += numbers.reduce { acc, i -> acc * i }
                }
            }
        }
        println("Part Two: $sum")
    }

    private fun checkNumbersAtStar(row: Int, column: Int): MutableSet<Int> {
        val numbers: MutableSet<Int> = mutableSetOf()
        for (subrow in row - 1..row + 1) {
            if (subrow >= 0 && subrow < matrix.size) {
                for (field in column - 1..column + 1) {
                    if (field >= 0 && field < matrix[0].size) {
                        if (matrix[subrow][field].isDigit()) {
                            numbers.add(getNumber(subrow, field))
                        }
                    }
                }
            }
        }
        return numbers
    }

    private fun getNumber(subrow: Int, field: Int): Int {
        var column = field
        var number = 0
        while (column > 0 && matrix[subrow][column-1].isDigit()) column--
        while ( matrix[subrow][column].isDigit()) {
            number = number * 10 + matrix[subrow][column].digitToInt()
            column++
        }
        return number
    }
}