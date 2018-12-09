package aoc2018

import kotlin.test.*

class Exercise09Test {

    @Test fun testHighscore() {
        assertEquals(32, Exercise09.highscore(9, 25).toInt())
        assertEquals(8317, Exercise09.highscore(10, 1618).toInt())
        assertEquals(146373, Exercise09.highscore(13, 7999).toInt())
        assertEquals(2764, Exercise09.highscore(17, 1104).toInt())
        assertEquals(54718, Exercise09.highscore(21, 6111).toInt())
        assertEquals(37305, Exercise09.highscore(30, 5807).toInt())
    }

}
