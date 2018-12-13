package aoc2018

import kotlin.test.*

class Exercise13Test {
    @Test fun test() {
        val input = """
            /->-\
            |   |  /----\
            | /-+--+-\  |
            | | |  | v  |
            \-+-/  \-+--/
              \------/
        """.trimIndent().lines()

        val (map, cars) = Exercise13.parseMap(input)

        assertEquals(2, cars.size)
        assertEquals(Directions.RIGHT, cars[0].direction)
        assertEquals(0, cars[0].row)
        assertEquals(2, cars[0].column)
        assertEquals(Directions.DOWN, cars[1].direction)
        assertEquals(3, cars[1].row)
        assertEquals(9, cars[1].column)

        assertEquals(13, map.width)
        assertEquals(6, map.height)

        // Row 0
        assertEquals(Road.RIGHT_DOWN, map[0][0])
        assertEquals(Road.HORIZONTAL, map[0][1])
        assertEquals(Road.HORIZONTAL, map[0][2])
        assertEquals(Road.HORIZONTAL, map[0][3])
        assertEquals(Road.LEFT_DOWN, map[0][4])
        assertEquals(null, map[0][5])
        assertEquals(null, map[0][6])
        assertEquals(null, map[0][7])
        assertEquals(null, map[0][8])
        assertEquals(null, map[0][9])
        assertEquals(null, map[0][10])
        assertEquals(null, map[0][11])
        assertEquals(null, map[0][12])

        // Row 1
        assertEquals(Road.VERTICAL, map[1][0])
        assertEquals(null, map[1][1])
        assertEquals(null, map[1][2])
        assertEquals(null, map[1][3])
        assertEquals(Road.VERTICAL, map[1][4])
        assertEquals(null, map[1][5])
        assertEquals(null, map[1][6])
        assertEquals(Road.RIGHT_DOWN, map[1][7])
        assertEquals(Road.HORIZONTAL, map[1][8])
        assertEquals(Road.HORIZONTAL, map[1][9])
        assertEquals(Road.HORIZONTAL, map[1][10])
        assertEquals(Road.HORIZONTAL, map[1][11])
        assertEquals(Road.LEFT_DOWN, map[1][12])


        // Row 2
        assertEquals(Road.VERTICAL, map[2][0])
        assertEquals(null, map[2][1])
        assertEquals(Road.RIGHT_DOWN, map[2][2])
        assertEquals(Road.HORIZONTAL, map[2][3])
        assertEquals(Road.CROSS, map[2][4])
        assertEquals(Road.HORIZONTAL, map[2][5])
        assertEquals(Road.HORIZONTAL, map[2][6])
        assertEquals(Road.CROSS, map[2][7])
        assertEquals(Road.HORIZONTAL, map[2][8])
        assertEquals(Road.LEFT_DOWN, map[2][9])
        assertEquals(null, map[2][10])
        assertEquals(null, map[2][11])
        assertEquals(Road.VERTICAL, map[2][12])

        // Row 3
        assertEquals(Road.VERTICAL, map[3][0])
        assertEquals(null, map[3][1])
        assertEquals(Road.VERTICAL, map[3][2])
        assertEquals(null, map[3][3])
        assertEquals(Road.VERTICAL, map[3][4])
        assertEquals(null, map[3][5])
        assertEquals(null, map[3][6])
        assertEquals(Road.VERTICAL, map[3][7])
        assertEquals(null, map[3][8])
        assertEquals(Road.VERTICAL, map[3][9])
        assertEquals(null, map[3][10])
        assertEquals(null, map[3][11])
        assertEquals(Road.VERTICAL, map[3][12])

        // Row 4
        assertEquals(Road.RIGHT_UP, map[4][0])
        assertEquals(Road.HORIZONTAL, map[4][1])
        assertEquals(Road.CROSS, map[4][2])
        assertEquals(Road.HORIZONTAL, map[4][3])
        assertEquals(Road.LEFT_UP, map[4][4])
        assertEquals(null, map[4][5])
        assertEquals(null, map[4][6])
        assertEquals(Road.RIGHT_UP, map[4][7])
        assertEquals(Road.HORIZONTAL, map[4][8])
        assertEquals(Road.CROSS, map[4][9])
        assertEquals(Road.HORIZONTAL, map[4][10])
        assertEquals(Road.HORIZONTAL, map[4][11])
        assertEquals(Road.LEFT_UP, map[4][12])

        // Row 5
        assertEquals(null, map[5][0])
        assertEquals(null, map[5][1])
        assertEquals(Road.RIGHT_UP, map[5][2])
        assertEquals(Road.HORIZONTAL, map[5][3])
        assertEquals(Road.HORIZONTAL, map[5][4])
        assertEquals(Road.HORIZONTAL, map[5][5])
        assertEquals(Road.HORIZONTAL, map[5][6])
        assertEquals(Road.HORIZONTAL, map[5][7])
        assertEquals(Road.HORIZONTAL, map[5][8])
        assertEquals(Road.LEFT_UP, map[5][9])
        assertEquals(null, map[5][10])
        assertEquals(null, map[5][11])
        assertEquals(null, map[5][12])
    }

    @Test fun testTick() {
        val input = """
            /->-\
            |   |  /----\
            | /-+--+-\  |
            | | |  | v  |
            \-+-/  \-+--/
              \------/
        """.trimIndent().lines()

        val map = Exercise13.parseMap(input)

        var gs = Exercise13.tick(GameState(map))
        assertEquals(3, gs.cars[0].column)
        assertEquals(4, gs.cars[1].row)
        assertEquals(Directions.RIGHT, gs.cars[1].direction)
        assertEquals(Turn.STRAIGHT, gs.cars[1].turn)

        gs = Exercise13.tick(gs)
        assertEquals(4, gs.cars[0].column)
        assertEquals(Directions.DOWN, gs.cars[0].direction)
        assertEquals(10, gs.cars[1].column)
    }

    @Test fun testCrash() {
        val input = """
            /---\
            |   |  /----\
            | /-+--v-\  |
            | | |  | |  |
            \-+-/  ^-+--/
              \------/
        """.trimIndent().lines()

        val map = Exercise13.parseMap(input)
        var gs = Exercise13.tick(GameState(map))
        assertEquals(0, gs.cars.size)
        assertEquals(2, gs.crashed.size)
        assertEquals(gs.crashed[0].row, gs.crashed[1].row)
        assertEquals(gs.crashed[0].column, gs.crashed[1].column)
        assertEquals(3, gs.crashed[1].row)
        assertEquals(7, gs.crashed[1].column)
    }

    @Test fun testFirstCrash() {
        val input = """
            /->-\
            |   |  /----\
            | /-+--+-\  |
            | | |  | v  |
            \-+-/  \-+--/
              \------/
        """.trimIndent().lines()

        assertEquals(Pair(7, 3), Exercise13.findFirstCrash(input))
    }

    @Test fun testLastStandingCrash() {
        val input = """
            />-<\
            |   |
            | /<+-\
            | | | v
            \>+</ |
              |   ^
              \<->/
        """.trimIndent().lines()

        assertEquals(Pair(6, 4), Exercise13.findLastStanding(input))
    }
}
