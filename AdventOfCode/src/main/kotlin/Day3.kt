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
        var speicher = 0
        var hasNeighbours = false
        var sum = 0

        for (row in matrix.indices) {
            for (column in matrix[row].indices) {
                if (matrix[row][column].isDigit()) {
                    hasNeighbours = checkNeighbours(row, column) || hasNeighbours
                    speicher = speicher * 10 + matrix[row][column].digitToInt()
                } else {
                    if (speicher != 0 && hasNeighbours) {
                        sum += speicher
                        speicher = 0
                        hasNeighbours = false
                    } else {
                        speicher = 0
                        hasNeighbours = false
                    }
                }
            }
        }
        println(sum)
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
}