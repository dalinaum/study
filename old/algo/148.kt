// 가끔씩 타임아웃! -_- 리트코드가 이상함.

data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Solution {
    fun mergeLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var l1 = list1
        var l2 = list2

        if (l1 != null && l2 != null) {
            if (l1.`val` > l2.`val`) {
                val temp = l1
                l1 = l2
                l2 = temp
            }
            l1.next = mergeLists(l1.next, l2)
        }
        return l1 ?: l2
    }

    fun sortList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) {
            return head
        }
        var slow = head
        var fast = head
        var half: ListNode? = null

        while (fast != null && fast.next != null) {
            half = slow
            slow = slow?.next
            fast = fast.next?.next
        }
        half?.next = null

        val l1 = sortList(head)
        val l2 = sortList(slow)
        return mergeLists(l1, l2)
    }
}

val head = ListNode(0)
var node: ListNode? = head

val list = intArrayOf(4, 2, 1, 3)
for (i in list) {
    node?.next = ListNode(i)
    node = node?.next
}

println(Solution().sortList(head.next))
