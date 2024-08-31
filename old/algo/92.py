from typing import Optional
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 28ms 내가 푼 방법
# class Solution:
#     def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
#         root = p = ListNode(None, head)
#         for _ in range(0, left - 1):
#             p = p.next
        
#         rev = None 
#         start = p
#         mid_last = p = p.next
                
#         for _ in range(right - left + 1):
#             p.next, rev, p = rev, p, p.next
        
#         mid_last.next = p
#         start.next = rev
#         return root.next

# 37ms ~ 28ms 책에서 참고한 방식
class Solution:
    def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
        if not head or left == right:
            return head
        
        root = start = ListNode(None, head)
        for _ in range(left - 1):
            start = start.next
        
        end = start.next
                
        for _ in range(right - left):
            tmp, start.next, end.next = start.next, end.next, end.next.next
            start.next.next = tmp
        
        return root.next
        

a = ListNode(1)
a.next = ListNode(2)
a.next.next = ListNode(3)
a.next.next.next = ListNode(4)
a.next.next.next.next = ListNode(5)

p = Solution().reverseBetween(a, 2, 4)

while p:
    print(p.val)
    p = p.next

a = ListNode(1)

p = Solution().reverseBetween(a, 1, 1)

while p:
    print(p.val)
    p = p.next

a = ListNode(3)
a.next = ListNode(5)

p = Solution().reverseBetween(a, 1, 2)

while p:
    print(p.val)
    p = p.next