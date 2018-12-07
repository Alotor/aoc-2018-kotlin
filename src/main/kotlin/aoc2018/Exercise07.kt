package aoc2018

object Exercise07 {
    var workers = 5
    var time = 60

    fun parse(line: String): Pair<String, String> {
        val regex = Regex("Step (\\w+) must be finished before step (\\w+) can begin.")
        val (parent, child) = regex.matchEntire(line)!!.destructured
        return parent to child
    }

    fun buildChildrenTree(pairs: List<Pair<String, String>>): Map<String, List<String>> {
        val children = mutableMapOf<String, List<String>>()

        pairs.forEach { (parent, child) ->
            if (children[parent] == null) {
                children[parent] = listOf()
            }
            children[parent] = (children[parent]!! + child).sortedWith(naturalOrder())
        }

        return children
    }

    fun assembleOrder(input: List<String>): String {
        workers = 1
        time = 0
        return assembleOrderWithWorkers(input).first
    }

    fun valueOf(s: String): Int =
        time + 1 + (s[0] - 'A')

    fun assembleOrderWithWorkers(input: List<String>): Pair<String, Int> {
        val pairs = input.map(::parse)
        val children = buildChildrenTree(pairs)
        val parents = buildChildrenTree(pairs.map(::reversePair))

        val candidates = sortedSetOf<String>()
        val processed = hashSetOf<String>()
        var result = ""

        var workersJobs = listOf<Pair<Int, String>>()
        var time = 0

        // Search root nodes
        candidates.addAll(children.keys.filter { parents[it] == null })

        while (!candidates.isEmpty() || !workersJobs.isEmpty()) {
            workersJobs = workersJobs.map { (cicles, job) -> (cicles - 1) to job }
            workersJobs.filter { (cicles, _) -> cicles == 0 }.forEach { (_, next) ->
                result += next
                processed.add(next)

                if (children[next] != null) {
                    val curChildren = children[next]!!.filter { child ->
                        parents[child] == null ||
                        parents[child]!!.all { processed.contains(it) }
                    }
                    candidates.addAll(curChildren)
                }
            }

            workersJobs = workersJobs.filter { (cicles, _) -> cicles != 0 }

            while (workersJobs.size < workers && candidates.size > 0) {
                val next = candidates.first()
                candidates.remove(next)
                workersJobs += Pair(valueOf(next), next)
            }

            if (!candidates.isEmpty() || !workersJobs.isEmpty()) {
                time++
            }
        }

        return result to time
    }
}
