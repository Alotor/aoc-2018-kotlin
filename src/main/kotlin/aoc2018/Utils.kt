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


fun <A,B> reversePair(pair: Pair<A,B>): Pair<B, A> {
    return Pair(pair.second, pair.first)
}

class CircularLinkedList<T> {
    class LinkedCell<T>(val value: T) {
        var previous: LinkedCell<T> = this
        var next: LinkedCell<T> = this
    }

    var current: LinkedCell<T>
    val head: LinkedCell<T>

    constructor(initialHead: T) {
        head = LinkedCell(initialHead)
        current = head
    }

    fun add(value: T) {
        val first = this.current.next
        val second = first.next
        this.current = LinkedCell(value)
        this.current.previous = first
        this.current.next = second
        first.next = this.current
        second.previous = this.current
    }

    fun back(times: Int) {
        (1 .. times).forEach {
            this.current = this.current.previous
        }
    }

    fun remove(): T {
        val value = this.current.value
        val previous = this.current.previous
        val next = this.current.next
        previous.next = next
        next.previous = previous
        this.current = next
        return value
    }
}

