from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 반복
# class Solution:
#     def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
#         rev = None
#         while head:
#             rev, rev.next, head = head, rev, head.next
#         return rev

class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        def reverse(node: Optional[ListNode], prev: Optional[ListNode] = None):
            if not node:
                return prev
            prev, prev.next, node = node, prev, node.next
            return reverse(node, prev)

        return reverse(head, None)
    
list = [1,2,3,4,5]
head = ListNode(list[0])
for i in list[1:]:
    p = head
    while p.next:
        p = p.next
    p.next = ListNode(i)

rev_head = Solution().reverseList(head)
while rev_head:
    print(rev_head.val)
    rev_head = rev_head.next