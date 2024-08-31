/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

// 리스트를 쓰는 방법

//class Solution {
//    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
//        val list = mutableListOf<ListNode>()
//        var node = head
//        while (node != null) {
//            list.add(node)
//            node = node.next
//        }
//        val next = list.getOrNull(list.lastIndex - n + 2)
//        val prev = list.getOrNull(list.lastIndex - n)
//        return if (prev != null) {
//            prev.next = next
//            head
//        } else {
//            next
//        }
//    }
//}

// 투 패스로 해결.

//class Solution {
//    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
//        var length = 0
//        var node = head
//        while (node != null) {
//            length++
//            node = node.next
//        }
//        val dummyHead = ListNode(0)
//        dummyHead.next = head
//        node = dummyHead
//
//        length -= n
//        while (length > 0) {
//            node = node?.next
//            length--
//        }
//        node?.next = node?.next?.next
//        return dummyHead.next
//    }
//}

// 투 포인터즈 원 패스

class Solution {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummyHead: ListNode? = ListNode(0, head)
        var slow = dummyHead
        var fast = dummyHead
        var gap = n + 1
        while (gap > 0) {
            fast = fast?.next
            gap--
        }
        while (fast != null) {
            fast = fast.next
            slow = slow?.next
        }
        slow?.next = slow?.next?.next
        return dummyHead?.next
    }
}
