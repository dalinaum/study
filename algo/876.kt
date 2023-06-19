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

class Solution {
    fun middleNode(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }
}

//val head = ListNode(1)
//head.next = ListNode(2)
//head.next?.next = ListNode(3)
//head.next?.next?.next = ListNode(4)
//head.next?.next?.next?.next = ListNode(5)

val head = ListNode(1)
head.next = ListNode(2)
head.next?.next = ListNode(3)
head.next?.next?.next = ListNode(4)
head.next?.next?.next?.next = ListNode(5)
head.next?.next?.next?.next?.next = ListNode(6)

println(Solution().middleNode(head)?.`val`)
