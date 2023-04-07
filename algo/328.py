from typing import Optional
# Definition for singly-linked list.

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 43ms
# class Solution:
#     def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
#         odds = ListNode()
#         evens = ListNode()

#         odd_p = odds
#         even_p = evens

#         while head and head.next:
#             odd_p.next = head
#             odd_p = odd_p.next
#             even_p.next = head.next
#             even_p = even_p.next
#             head = head.next.next

#         if head:
#             odd_p.next = head
#             odd_p = odd_p.next
        
#         odd_p.next = evens.next
#         even_p.next = None
#         return odds.next

# 49ms 책의 조금 더 효율적인 풀이
class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return head
        
        odd = head
        even_head = even = head.next

        while even and even.next:
            odd.next = odd.next.next
            odd = odd.next
            even.next = even.next.next
            even = even.next
        
        odd.next = even_head
        return head
     

# [1,2,3,4,5]
a = ListNode(1)
a.next = ListNode(2)
a.next.next = ListNode(3)
a.next.next.next = ListNode(4)
a.next.next.next.next = ListNode(5)

b = Solution().oddEvenList(a)

while b:
    print(b.val)
    b = b.next
print('---')

a = ListNode(2)
a.next = ListNode(1)
a.next.next = ListNode(3)
a.next.next.next = ListNode(5)
a.next.next.next.next = ListNode(6)
a.next.next.next.next.next = ListNode(4)
a.next.next.next.next.next.next = ListNode(7)

b = Solution().oddEvenList(a)

while b:
    print(b.val)
    b = b.next