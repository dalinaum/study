/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

// Set을 쓴 풀이

// class Solution {
//     fun detectCycle(head: ListNode?): ListNode? {
//         var node = head
//         val seen = mutableSetOf<ListNode>()
//         while (node != null) {
//             if (node in seen) {
//                 return node
//             }
//             seen.add(node)
//             node = node?.next
//         }
//         return null
//     }
// }

// Hare and Tortoise로 풀이

data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    fun detectCycle(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                break
            }
        }
        if (fast == null || fast.next == null) {
            return null
        }
        fast = head
        while (slow != fast) {
            fast = fast?.next
            slow = slow?.next
        }
        return fast
    }
}
