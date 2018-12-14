package aoc2018

import kotlin.test.*

class Exercise14Test {
    @Test fun testGenerate() {
        val result = Exercise14.generate { it.size == 20 }
        assertEquals(listOf(3,7,1,0,1,0,1,2,4,5,1,5,8,9,1,6,7,7,9,2), result)
    }

    @Test fun testLast10() {
        assertEquals("0124515891", Exercise14.last10(5).joinToString(""))
        assertEquals("9251071085", Exercise14.last10(18).joinToString(""))
        assertEquals("5941429882", Exercise14.last10(2018).joinToString(""))
    }

    @Test fun testTailGen() {
        assertEquals(9, Exercise14.tailGen("51589"))
        assertEquals(5, Exercise14.tailGen("01245"))
        assertEquals(18, Exercise14.tailGen("92510"))
        assertEquals(2018, Exercise14.tailGen("59414"))
    }
}
