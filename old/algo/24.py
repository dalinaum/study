from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 반복 32ms
# class Solution:
#     def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
#         root = prev = ListNode(next = head)
#         while head and head.next:
#             first = head.next
#             head.next, first.next, prev.next = first.next, head, first           
#             prev, head = prev.next.next, head.next
#         return root.next

# 내가 했던 풀이 필요 없이 head.next를 두번 할당
# class Solution:
#     def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
#         if head and head.next:
#             first = head.next
#             head.next = first.next
#             first.next = head
#             head.next = self.swapPairs(head.next)
#             return first
        
# 재귀 37ms
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head and head.next:
            first = head.next
            head.next = self.swapPairs(first.next)
            first.next = head
            return first
        return head

a = ListNode(1)
a.next = ListNode(2)
a.next.next = ListNode(3)
a.next.next.next = ListNode(4)


result = Solution().swapPairs(a)

while result:
    print(result.val)
    result = result.next