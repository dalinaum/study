from typing import Optional
import collections

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

## 825ms

# class Solution:
#     def isPalindrome(self, head: Optional[ListNode]) -> bool:
#         list = []
#         p = head
#         while True:
#             list.append(p.val)
#             if not p.next:
#                 break
#             p = p.next
#         print(list)
#         return list == list[::-1]

## 940ms
# class Solution:
#     def isPalindrome(self, head: Optional[ListNode]) -> bool:
#         list = []
#         p = head
#         while p:
#             list.append(p.val)
#             p = p.next
#         print(list)
#         return list == list[::-1]


## 875 ms
# class Solution:
#     def isPalindrome(self, head: Optional[ListNode]) -> bool:
#         list = []
#         p = head
#         while p:
#             list.append(p.val)
#             p = p.next
#         print(list)
#         return list[:] == list[::-1]

# 772ms
# class Solution:
#     def isPalindrome(self, head: Optional[ListNode]) -> bool:
#         deque = collections.deque()
#         if not head:
#             return True
#         p = head
#         while p:
#             deque.append(p.val)
#             p = p.next

#         while len(deque) > 1:
#             if deque.popleft() != deque.pop():
#                 return False
#         return True

## 653ms
class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        fast = slow = head
        rev = None

        while fast and fast.next:
            fast = fast.next.next
            rev, rev.next, slow = slow, rev, slow.next
        
        if fast:
            slow = slow.next
        
        while slow and rev.val == slow.val:
            rev, slow = rev.next, slow.next
        
        return not slow


head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(2)
head.next.next.next = ListNode(1)

print(Solution().isPalindrome(head))