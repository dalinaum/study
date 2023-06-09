data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

//class Solution {
//    fun insertionSortList(head: ListNode?): ListNode? {
//        val resultHead = ListNode(Int.MIN_VALUE)
//        var node = head
//
//        outer@ while (node != null) {
//            var resultNode: ListNode? = resultHead
//            var prevResultNode: ListNode? = null
//            while (resultNode != null) {
//                if (resultNode.`val` > node!!.`val`) {
//                    prevResultNode?.next = node
//                    val temp = node.next
//                    node.next = resultNode
//                    node = temp
//                    continue@outer
//                }
//                prevResultNode = resultNode
//                resultNode = resultNode.next
//            }
//            prevResultNode?.next = node
//            val temp = node?.next
//            node?.next = null
//            node = temp
//        }
//        return resultHead.next
//    }
//}

// 항상 처음부터 찾던 코드를 개선.

class Solution {
    fun insertionSortList(head: ListNode?): ListNode? {
        val resultHead = ListNode(Int.MIN_VALUE)
        var node = head
        var resultNode: ListNode? = null
        var prevResultNode: ListNode? = null

        outer@ while (node != null) {
            if (resultNode == null || resultNode.`val` > node.`val`) {
                resultNode = resultHead
                prevResultNode = null
            }
            while (resultNode != null) {
                if (resultNode.`val` > node!!.`val`) {
                    prevResultNode?.next = node
                    val temp = node.next
                    node.next = resultNode
                    node = temp
                    continue@outer
                }
                prevResultNode = resultNode
                resultNode = resultNode.next
            }
            prevResultNode?.next = node
            val temp = node?.next
            node?.next = null
            node = temp
        }
        return resultHead.next
    }
}

val head = ListNode(0)
var node: ListNode? = head

//val list = intArrayOf(4, 2, 1, 3)
//val list = intArrayOf(-1,5,3,4,0)
val list = intArrayOf(4, 19, 14, 5, -3, 1, 8, 5, 11, 15)
for (i in list) {
    node?.next = ListNode(i)
    node = node?.next
}

var newNode: ListNode? = Solution().insertionSortList(head.next)
while (newNode != null) {
    println(newNode?.`val`)
    newNode = newNode?.next
}
