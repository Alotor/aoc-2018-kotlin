package aoc2018

typealias Point = Pair<Int, Int>

object Exercise11 {
    val gridSize = 300
    val cellPowerCache = Array(gridSize, { Array(gridSize, { Array<Int?>(gridSize, { null })})})

    fun clearCache() {
        for (i in cellPowerCache.indices) {
            cellPowerCache[i] = Array(gridSize, { Array<Int?>(gridSize, { null })})
        }
    }

    fun hundredDigit(number: Int): Int = number / 100 - (number / 1000) * 10

    fun power(point: Point, serial: Int): Int {
        val (x, y) = point
        val rackId = x + 10
        val power = rackId * (rackId * y + serial)
        return hundredDigit(power) - 5
    }

    fun cellPower(cellSize: Int, point: Point, serial: Int): Int {
        val (x, y) = point

        if (cellSize == 1) {
            return power(Point(x, y), serial)
        }

        if (cellPowerCache[cellSize - 1][x - 1][y - 1] == null) {
            var sum = cellPower(cellSize - 1, point, serial)

            for (offsetX in (0 .. cellSize - 1)) {
                sum += power(Point(x + offsetX, y + cellSize - 1), serial)
            }

            for (offsetY in (0 .. cellSize - 2)) {
                sum += power(Point(x + cellSize - 1, y + offsetY), serial)
            }

            cellPowerCache[cellSize - 1][x - 1][y - 1] = sum
        }

        return cellPowerCache[cellSize - 1][x - 1][y - 1]!!
    }

    fun largestCell(cellSize: Int, serial: Int): Pair<Int, Point> {
        var max = Int.MIN_VALUE to Point(0, 0)

        for (y in 1 .. (gridSize - cellSize + 1)) {
            for (x in 1 .. (gridSize - cellSize + 1)) {
                val point = Point(x, y)
                val current = cellPower(cellSize, point, serial)

                if (current > max.first) {
                    max = current to point
                }
            }
        }
        return max
    }

    fun calculateMaxPowerCell(input: List<String>): Point {
        return largestCell(3, input[0].toInt()).second
    }

    fun calculateMaxPowerCellAnySize(input: List<String>): Pair<Point, Int> {
        return (1 .. gridSize).map { side ->
            if (side % 20 == 0) {
                println("$side / $gridSize")
            }
            val (power, point) = largestCell(side, input[0].toInt())
            power to Pair(point, side)
        }.maxBy { (power, _) -> power }!!.second
    }
}
