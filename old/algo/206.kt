class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        var node = head
        var rev: ListNode? = null

        while (node != null) {
            val next = node.next
            val prev = rev
            rev = node
            rev.next = prev
            node = next
        }
        return rev
    }
}

var head = ListNode(1)
head.next = ListNode(2)
head.next?.next = ListNode(3)
head.next?.next?.next = ListNode(4)

var rev = Solution().reverseList(head)

while (rev != null) {
    println(rev?.`val`)
    rev = rev?.next
}
