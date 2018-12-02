package aoc2018

import kotlin.test.*

class Exercise02Test {
    @Test fun testletterFreqNoMatches() {
        val res = Exercise02.letterFreq("abc")
        assertEquals(Pair(0, 0), res)
    }

    @Test fun testletterFreq2EntriesMatches() {
        val res = Exercise02.letterFreq("aab")
        assertEquals(Pair(1, 0), res)
    }

    @Test fun testletterFreq3EntiresMatches() {
        val res = Exercise02.letterFreq("abbb")
        assertEquals(Pair(0, 1), res)
    }

    @Test fun testletterFreqBoth3and2EntriesMatch() {
        val res = Exercise02.letterFreq("aaabb")
        assertEquals(Pair(1, 1), res)
    }

    @Test fun testChecksumBoxes() {
        val res = Exercise02.checksumBoxes(listOf(
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"))

        assertEquals(12, res)
    }

    @Test fun testFindBoxes() {
        val res = Exercise02.findBox(listOf(
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fghxx",
                "fguij",
                "axcye",
                "wvxyz"))

        assertEquals("fgij", res)
    }
}
