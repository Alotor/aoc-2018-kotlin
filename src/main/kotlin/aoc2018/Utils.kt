package aoc2018

import java.io.File
import java.net.URL

object Utils {
    fun resolveResource(resource: String): URL {
        return this.javaClass::class.java.getResource(resource)
    }

    fun readLines(filename: URL): List<String> =
        File(filename.toURI()).readLines()

}

operator fun Pair<Int, Int>.plus(element: Pair<Int, Int>): Pair<Int, Int> =
    Pair(first + element.first, second + element.second)



