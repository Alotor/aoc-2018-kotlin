package aoc2018

enum class Directions {
    RIGHT, LEFT, UP, DOWN
}

enum class Turn {
    LEFT, STRAIGHT, RIGHT
}

enum class Road(val token: Char) {
    VERTICAL('|'),
    HORIZONTAL('-'),
    CROSS('+'),
    LEFT_DOWN('\\'),
    RIGHT_UP('\\'),
    LEFT_UP('/'),
    RIGHT_DOWN('/')
}

data class Car(
    val row: Int,
    val column: Int,
    val direction: Directions,
    val turn: Turn = Turn.LEFT
) {
    fun collide(other: Car) =
        this.row == other.row && this.column == other.column
}

typealias Tracks = Array<Array<Road?>>

data class GameState(
    val tracks: Tracks,
    val cars: List<Car>,
    val crashed: List<Car>
) {
    constructor(mapData: Pair<Tracks, List<Car>>): this(
        mapData.first, mapData.second, listOf<Car>()) {
    }
}

val Tracks.width: Int
    get() = this[0].size

val Tracks.height: Int
    get() = this.size

fun Tracks.print(): String =
    this.map { it.map { (it?.token)?: " " }.joinToString("") }.joinToString("\n")

object Exercise13 {
    fun parseCar(current: Char, row: Int, column: Int): Car? {
        if (current in setOf('>', '<', '^', 'v')) {
            return Car(row, column, when(current) {
                '>' -> Directions.RIGHT
                '<' -> Directions.LEFT
                '^' -> Directions.UP
                else ->  Directions.DOWN
            })
        }
        return null
    }

    fun parseRoad(previous: Char?, current: Char): Road? = when(current) {
        Road.VERTICAL.token -> Road.VERTICAL
        Road.HORIZONTAL.token -> Road.HORIZONTAL
        Road.CROSS.token -> Road.CROSS
        Road.LEFT_DOWN.token ->
            if (previous in listOf('>', '<', '+', '-')) Road.LEFT_DOWN else Road.RIGHT_UP
        Road.LEFT_UP.token ->
            if (previous in listOf('>', '<', '+', '-')) Road.LEFT_UP else Road.RIGHT_DOWN
        '>', '<' -> Road.HORIZONTAL
        '^', 'v' -> Road.VERTICAL
        else -> null
    }

    fun parseMap(input: List<String>): Pair<Tracks, List<Car>> {
        var cars = listOf<Car>()
        val width = input.maxBy { it.length }!!.length
        val tracks = Array(input.size, { arrayOfNulls<Road?>(width)})
        var row = 0

        for (line in input) {
            var column = 0
            var previous: Char? = null

            for (cc in line) {
                tracks[row][column] = parseRoad(previous, cc)
                val car = parseCar(cc, row, column)
                if (car != null) {
                    cars += car
                }
                previous = cc
                column++
            }
            row++
        }

        return tracks to cars
    }

    fun carStep(state: GameState, car: Car): Car {
        val row = when(car.direction) {
            Directions.UP -> car.row - 1
            Directions.DOWN -> car.row + 1
            else -> car.row
        }

        val column = when(car.direction) {
            Directions.LEFT -> car.column - 1
            Directions.RIGHT -> car.column + 1
            else -> car.column
        }

        val direction = when(state.tracks[row][column]!!) {
            Road.CROSS -> when (car.turn) {
                Turn.LEFT -> when (car.direction) {
                    Directions.RIGHT -> Directions.UP
                    Directions.LEFT -> Directions.DOWN
                    Directions.UP -> Directions.LEFT
                    Directions.DOWN -> Directions.RIGHT
                }
                Turn.RIGHT -> when(car.direction) {
                    Directions.RIGHT -> Directions.DOWN
                    Directions.LEFT -> Directions.UP
                    Directions.UP -> Directions.RIGHT
                    Directions.DOWN -> Directions.LEFT
                }
                Turn.STRAIGHT -> car.direction
            }

            Road.LEFT_DOWN -> when (car.direction) {
                Directions.RIGHT -> Directions.DOWN
                else -> Directions.LEFT
            }

            Road.RIGHT_UP -> when (car.direction) {
                Directions.LEFT -> Directions.UP
                else -> Directions.RIGHT
            }

            Road.LEFT_UP -> when (car.direction) {
                Directions.RIGHT -> Directions.UP
                else -> Directions.LEFT
            }

            Road.RIGHT_DOWN -> when (car.direction) {
                Directions.LEFT -> Directions.DOWN
                else -> Directions.RIGHT
            }
            else -> car.direction
        }

        val turn = when(state.tracks[row][column]) {
            Road.CROSS -> when(car.turn) {
                Turn.LEFT -> Turn.STRAIGHT
                Turn.STRAIGHT -> Turn.RIGHT
                Turn.RIGHT -> Turn.LEFT
            }
            else -> car.turn
        }

        return Car(row, column, direction, turn)
    }

    fun tick(state: GameState): GameState {
        var next = listOf<Car>()
        var crashed = state.crashed
        var left = state.cars.sortedWith(compareBy<Car> { it.row }.thenBy { it.column })

        while (left.size > 0) {
            val car = left.first()
            left = left.drop(1)

            val toCar = carStep(state, car)

            val leftCollision = left.find { it.collide(toCar) }
            if (leftCollision != null) {
                left = left.filter { it != leftCollision }
                crashed += leftCollision
                crashed += toCar
                continue
            }

            val nextCollision = next.find { it.collide(toCar) }
            if (nextCollision != null) {
                next = next.filter { it != nextCollision }
                crashed += nextCollision
                crashed += toCar
                continue
            }

            next += toCar
        }

        return GameState(state.tracks, next, crashed)
    }


    fun findFirstCrash(input: List<String>): Pair<Int, Int> {
        val map = parseMap(input)
        var state = GameState(map)

        while (state.crashed.size == 0) {
            state = tick(state)
        }

        return state.crashed[0].column to state.crashed[0].row
    }

    fun findLastStanding(input: List<String>): Pair<Int, Int> {
        val map = parseMap(input)
        var state = GameState(map)

        while (state.cars.size > 1) {
            state = tick(state)
        }

        return state.cars[0].column to state.cars[0].row
    }

}
