package aoc2018

typealias Grid = Array<Array<Int?>>

object Exercise06 {
    var safeDistance = 10000

    data class Coordinate(val x: Int, val y: Int) {
        fun distance(other: Coordinate): Int =
            Math.abs(this.x - other.x) + Math.abs(this.y - other.y)
    }

    fun parse(line: String): Coordinate {
        val regex = Regex("(\\d+), (\\d+)")
        val (x, y) = regex.matchEntire(line)!!.destructured
        return Coordinate(x.toInt(), y.toInt())
    }

    fun gridEach(grid: Grid, fn: (row: Int, col: Int, value: Int?) -> Unit) {
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                fn(row, col, grid[row][col])
            }
        }
    }

    fun buildGrid(coords: List<Coordinate>, fn: (c1: Coordinate) -> Int?): Grid {
        val maxX = with(coords.maxBy { it.x }!!) { x }
        val maxY = with(coords.maxBy { it.y }!!) { y }

        val grid = Array(maxY + 1, { Array<Int?>(maxX + 1, { null })})

        for (row in grid.indices) {
            for (col in grid[row].indices) {
                val curCoord = Coordinate(col, row)
                grid[row][col] = fn(curCoord)
            }
        }
        return grid
    }

    fun isBorder(grid: Grid, row: Int, col: Int): Boolean =
        row == 0 || col == 0 || row == grid.size - 1 || col == grid[0].size -1

    fun sizeOfLargestArea(lines: List<String>): Int {
        val coords = lines.map(::parse)
        val infinites = mutableSetOf<Int>()

        val grid = buildGrid(coords) { curCoord ->
            var min: Int? = null
            var minIdx: Int? = null

            coords.forEachIndexed { idx, coord ->
                val curDist = curCoord.distance(coord)

                if (min == null || curDist < min!!) {
                    min = curDist
                    minIdx = idx
                } else if (curDist == min) {
                    minIdx = null
                }
            }
            minIdx
        }

        gridEach(grid) { row, col, value ->
            if (value != null && isBorder(grid, row, col)) {
                infinites.add(value)
            }
        }

        val counts = mutableMapOf<Int, Int>()
        gridEach(grid) { _, _, value ->
            if (value != null && value !in infinites) {
                counts[value] = (counts[value] ?: 0) + 1
            }
        }
        return counts.maxBy { it.value }!!.value
    }

    fun sizeOfSafestArea(lines: List<String>): Int {
        val coords = lines.map(::parse)
        val grid = buildGrid(coords) { curCoord ->
            if (coords.map { curCoord.distance(it) }.sum() >= safeDistance) 0 else 1
        }

        var count = 0
        gridEach(grid) { _, col, value ->
            count += (value ?: 0)
        }
        return count
    }
}
