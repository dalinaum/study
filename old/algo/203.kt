/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        val dummyHead = ListNode(0, head)
        var node = dummyHead.next
        var prev: ListNode? = dummyHead

        while (node != null) {
            if (node.`val` == `val`) {
                prev?.next = node.next
            } else {
                prev = prev?.next
            }
            node = node.next
        }
        return dummyHead.next
    }
}
