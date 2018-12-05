package aoc2018

object Exercise03 {
    val OVERLAP = -1

    data class Entry(
        val id: Int,
        val left: Int,
        val bottom: Int,
        val width: Int,
        val height: Int)


    fun parseEntry(entry: String): Entry {
        val rex = Regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)")
        val match = rex.matchEntire(entry)!!
        val (id, left, bottom, width, height) = match.destructured
        return Entry(
            id.toInt(),
            left.toInt(),
            bottom.toInt(),
            width.toInt(),
            height.toInt()
        )
    }

    fun fillStore(store: Array<Array<Int?>>, entry: Entry): Int {
        var acc = 0
        for (row in entry.bottom .. entry.bottom + entry.height - 1) {
            for (col in entry.left .. entry.left + entry.width - 1) {
                when (store[col][row]) {
                    OVERLAP-> {} // noop
                    null -> store[col][row] = entry.id
                    else -> {
                        store[col][row] = OVERLAP
                        acc ++
                    }
                }
            }
        }
        return acc
    }

    fun initStore(entries: List<Entry>): Array<Array<Int?>> {
        var maxCols = with(entries.maxBy { it.left + it.width }!!, { left + width })
        var maxRows = with(entries.maxBy { it.bottom + it.height }!!, { bottom + height })
        return Array(maxCols + 1, { arrayOfNulls<Int?>(maxRows + 1) })
    }

    fun countIntersections(lines: List<String>): Int {
        val entries = lines.map(::parseEntry)
        val store = initStore(entries)

        return entries.fold(0) { acc, entry ->
            acc + fillStore(store, entry)
        }
    }

    fun isClean(store: Array<Array<Int?>>, entry: Entry): Boolean {
        for (row in entry.bottom .. entry.bottom + entry.height - 1) {
            for (col in entry.left .. entry.left + entry.width - 1) {
                if (store[col][row] == OVERLAP) {
                    return false
                }
            }
        }
        return true
    }

    fun checkClean(lines: List<String>): Int {
        val entries = lines.map(::parseEntry)
        val store = initStore(entries)
        entries.forEach { fillStore (store, it) }
        return entries.find { isClean(store, it)}!!.id
    }

}
