// import java.util.PriorityQueue

// class ListNode(var `val`: Int) {
//     var next: ListNode? = null
// }

// class Solution {
//     fun mergeKLists(lists: Array<ListNode?>): ListNode? {
//         val priorityQueue = PriorityQueue<Int>()
//         for (list in lists) {
//             var node = list
//             while (node != null) {
//                 priorityQueue.add(node.`val`)
//                 node = node.next
//             }
//         }
//         val head = ListNode(0)
//         var p: ListNode? = head

//         while (priorityQueue.isNotEmpty()) {
//             val value = priorityQueue.poll()
//             p?.next = ListNode(value)
//             p = p?.next
//         }
//         return head.next
//     }
// }

// //lists = [[1,4,5],[1,3,4],[2,6]]

// val head1 = ListNode(1)
// head1.next = ListNode(4)
// head1.next?.next = ListNode(5)

// val head2 = ListNode(1)
// head2.next = ListNode(3)
// head2.next?.next = ListNode(4)

// val head3 = ListNode(2)
// head3.next = ListNode(6)

// val lists: Array<ListNode?> = arrayOf(head1, head2, head3)

// var result = Solution().mergeKLists(lists)

// while (result != null) {
//     println(result?.`val`)
//     result = result?.next
// }

import java.util.PriorityQueue

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

data class ValAndNode(
    val value: Int,
    val node: ListNode?
) : Comparable<ValAndNode> {
    override fun compareTo(other: ValAndNode): Int =
        compareValues(this.value, other.value)
}

class Solution {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val priorityQueue = PriorityQueue<ValAndNode>()
        val head = ListNode(0)
        var p: ListNode? = head

        for (list in lists) {
            if (list != null) {
                priorityQueue.add(ValAndNode(list.`val`, list))
            }
        }

        while (priorityQueue.isNotEmpty()) {
            val (_, node) = priorityQueue.poll()
            p?.next = node
            p = p?.next
            val next = node?.next
            if (next != null) {
                priorityQueue.add(ValAndNode(next.`val`, next))
            }
        }
        return head.next
    }
}

//lists = [[1,4,5],[1,3,4],[2,6]]

val head1 = ListNode(1)
head1.next = ListNode(4)
head1.next?.next = ListNode(5)

val head2 = ListNode(1)
head2.next = ListNode(3)
head2.next?.next = ListNode(4)

val head3 = ListNode(2)
head3.next = ListNode(6)

val lists: Array<ListNode?> = arrayOf(head1, head2, head3)

var result = Solution().mergeKLists(lists)

while (result != null) {
    println(result?.`val`)
    result = result?.next
}
