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
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        
        while (fast?.next?.next != null) {
            fast = fast?.next?.next
            slow = slow?.next
            if (fast == slow) {
                return true
            }
        }
        return false
    }
}
