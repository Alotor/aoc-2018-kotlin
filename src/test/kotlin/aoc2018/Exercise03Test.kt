package aoc2018

import kotlin.test.*

class Exercise03Test {
    @Test fun testParseCoordinatesEntry() {
        val data = listOf(
            "#1 @ 1,3: 4x4" to Exercise03.Entry(1, 1, 3, 4, 4),
            "#111 @ 100,300: 40x411" to Exercise03.Entry(111, 100, 300, 40, 411))

         data.forEach {
             assertEquals(it.second, Exercise03.parseEntry(it.first))
         }
    }

    @Test fun testIfCountsIntersections() {
        val values = listOf(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2")

        assertEquals(4, Exercise03.countIntersections(values))
    }

    @Test fun testCleanIntersection() {
        val values = listOf(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2")

        assertEquals(3, Exercise03.checkClean(values))
    }
}
