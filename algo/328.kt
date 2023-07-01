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
    fun oddEvenList(head: ListNode?): ListNode? {
        var node = head
        val firstHead = ListNode(0)
        val secondHead = ListNode(0)
        var firstP: ListNode? = firstHead
        var secondP: ListNode? = secondHead

        var isOdd = true
        while (node != null) {
            if (isOdd) {
                firstP?.next = node
                firstP = firstP?.next
            } else {
                secondP?.next = node
                secondP = secondP?.next
            }
            isOdd = !isOdd
            node = node.next
        }
        firstP?.next = secondHead.next
        secondP?.next = null
        return firstHead.next
    }
}
