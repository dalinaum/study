// class Solution {
//     fun isPalindrome(head: ListNode?): Boolean {
//         var p = head
//         var list = mutableListOf<Int>()
//         while (p != null) {
//             list.add(p.`val`)
//             p = p.next
//         }
//         while (list.size > 1) {
//             val first = list.removeAt(0)
//             val last = list.removeAt(list.lastIndex)
//             if (first != last) return false
//         }
//         return true
//     }
// }

// var head = ListNode(1)
// head.next = ListNode(2)
// head.next?.next = ListNode(2)
// head.next?.next?.next = ListNode(1)
// println(Solution().isPalindrome(head))

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun isPalindrome(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        var rev: ListNode? = null

        while (fast != null && fast.next != null) {
            val temp = rev
            rev = slow

            fast = fast.next?.next
            slow = slow?.next

            rev?.next = temp
        }

        if (fast != null) {
            slow = slow?.next
        }

        while (slow != null) {
            if (slow.`val` != rev?.`val`) return false
            slow = slow.next
            rev = rev.next
        }
        return true
    }
}

var head = ListNode(1)
head.next = ListNode(2)
head.next?.next = ListNode(1)
//head.next?.next?.next = ListNode(1)
println(Solution().isPalindrome(head))
