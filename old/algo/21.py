# Definition for singly-linked list.
from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        if not list1 or list2 and list1.val > list2.val:
            list1, list2 = list2, list1
        if list1:
            list1.next = self.mergeTwoLists(list1.next, list2)
        return list1


# list1 = [1,2,4], list2 = [1,3,4]

a = ListNode(1)
a.next = ListNode(2)
a.next.next = ListNode(4)

b = ListNode(1)
b.next = ListNode(3)
b.next.next = ListNode(4)

c = Solution().mergeTwoLists(a, b)
while c:
    print(c.val)
    c = c.next