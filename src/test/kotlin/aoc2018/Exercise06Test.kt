package aoc2018

import kotlin.test.*

class Exercise06Test {
    @Test fun testParseCoordinate() {
        assertEquals(Exercise06.parse("1, 1"), Exercise06.Coordinate(1, 1))
        assertEquals(Exercise06.parse("111, 111"), Exercise06.Coordinate(111, 111))
    }

    @Test fun testCoordDistance() {
        assertEquals(0, Exercise06.Coordinate(1, 1).distance(Exercise06.Coordinate(1, 1)))
        assertEquals(10, Exercise06.Coordinate(0, 0).distance(Exercise06.Coordinate(5, 5)))
    }

    @Test fun testSizeOfLargestArea() {
        val input = listOf(
                "1, 1",
                "1, 6",
                "8, 3",
                "3, 4",
                "5, 5",
                "8, 9"
        )

        assertEquals(Exercise06.sizeOfLargestArea(input), 17)
    }

    @Test fun testSizeOfSafestArea() {
        val input = listOf(
                "1, 1",
                "1, 6",
                "8, 3",
                "3, 4",
                "5, 5",
                "8, 9"
        )
        Exercise06.safeDistance = 32
        assertEquals(16, Exercise06.sizeOfSafestArea(input))
    }
}
