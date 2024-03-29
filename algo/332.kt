// class Solution {
//     fun findItinerary(tickets: List<List<String>>): List<String> {
//         val sortedTickets = tickets.sortedWith(compareBy({ it[0] }, { it[1] }))
//         val graph = mutableMapOf<String, MutableList<String>>()
//         for ((from, to) in sortedTickets) {
//             val dests = graph.getOrDefault(from, mutableListOf())
//             dests.add(to)
//             graph[from] = dests
//         }

//         val path = mutableListOf<String>()
//         fun visit(from: String) {
//             while (graph.getOrDefault(from, mutableListOf()).isNotEmpty()) {
//                 visit(graph.getValue(from).removeAt(0))
//             }
//             path.add(from)
//         }
//         visit("JFK")

//         return path.reversed()
//     }
// }

// //val tickets = listOf(
// //    listOf("MUC", "LHR"),
// //    listOf("JFK", "MUC"),
// //    listOf("SFO", "SJC"),
// //    listOf("LHR", "SFO")
// //)

// //val tickets = listOf(
// //    listOf("JFK", "SFO"),
// //    listOf("JFK", "ATL"),
// //    listOf("SFO", "ATL"),
// //    listOf("ATL", "JFK"),
// //    listOf("ATL", "SFO")
// //)

// val tickets = listOf(
//     listOf("JFK","KUL"),
//     listOf("JFK","NRT"),
//     listOf("NRT","JFK"),
// )

// //[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]

// println(Solution().findItinerary(tickets))

class Solution {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        val sortedTickets = tickets.sortedWith(compareBy({ it[0] }, { it[1] }))
        val graph = mutableMapOf<String, MutableList<String>>()
        for ((from, to) in sortedTickets) {
            val dests = graph.getOrDefault(from, mutableListOf())
            dests.add(to)
            graph[from] = dests
            graph.putIfAbsent(to, mutableListOf())
        }
        val path = mutableListOf<String>()
        val stack = mutableListOf<String>("JFK")

        while (stack.isNotEmpty()) {
            while (graph.getValue(stack[stack.lastIndex]).isNotEmpty()) {
                stack.add(graph.getValue(stack[stack.lastIndex]).removeAt(0))
            }
            path.add(stack.removeAt(stack.lastIndex))
        }
        return path.reversed()
    }
}

//val tickets = listOf(
//    listOf("MUC", "LHR"),
//    listOf("JFK", "MUC"),
//    listOf("SFO", "SJC"),
//    listOf("LHR", "SFO")
//)

val tickets = listOf(
    listOf("JFK", "SFO"),
    listOf("JFK", "ATL"),
    listOf("SFO", "ATL"),
    listOf("ATL", "JFK"),
    listOf("ATL", "SFO")
)

//val tickets = listOf(
//    listOf("JFK", "KUL"),
//    listOf("JFK", "NRT"),
//    listOf("NRT", "JFK"),
//)

println(Solution().findItinerary(tickets))
