package aoc2018

import kotlin.test.*

class Exercise11Test {
    @Test fun testCalculatePowerValue() {
        assertEquals(4, Exercise11.power(3 to 5, 8))
        assertEquals(-5, Exercise11.power(122 to 79, 57))
        assertEquals(0, Exercise11.power(217 to 196, 39))
        assertEquals(4, Exercise11.power(101 to 153, 71))
    }

    @Test fun testLargestCell() {
        val cell = 3
        Exercise11.clearCache()
        assertEquals(29 to Pair(33, 45), Exercise11.largestCell(cell, 18))

        Exercise11.clearCache()
        assertEquals(30 to Pair(21, 61), Exercise11.largestCell(cell, 42))
    }
}
