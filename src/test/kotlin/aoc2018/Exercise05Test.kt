package aoc2018

import kotlin.test.*

class Exercise05Test {

    @Test fun testReactPolymer() {
        val data = listOf(
            "abcdefg" to "abcdefg",
            "abcCdDefg" to "abefg",
            "aA" to "",
            "abcCBA" to "",
            "dabAcCaCBAcCcaDA" to "dabCBAcaDA"
        )

        data.forEach { (input, expect) ->
            assertEquals(expect, Exercise05.reactPolymer(input))
        }
    }

    @Test fun testFindBestPolymer() {
        val data = listOf(
            "dabAcCaCBAcCcaDA" to 4
        )

        data.forEach { (input, expect) ->
            assertEquals(expect, Exercise05.reactBestPolymer(listOf(input)))
        }
    }

}
