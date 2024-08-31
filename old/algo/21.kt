class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var l1 = list1
        var l2 = list2
        if (l1 == null || (l2 != null && l1.`val` > l2.`val`)) {
            val temp = l1
            l1 = l2
            l2 = temp
        }
        if (l1 != null) {
            l1.next = mergeTwoLists(l1.next, l2)
        }
        return l1
    }
}

var p1 = ListNode(1)
p1.next = ListNode(2)
p1.next?.next = ListNode(4)

var p2 = ListNode(1)
p2.next = ListNode(3)
p2.next?.next = ListNode(4)

var p3 = Solution().mergeTwoLists(p1, p2)
while (p3 != null) {
    println(p3?.`val`)
    p3 = p3?.next
}
