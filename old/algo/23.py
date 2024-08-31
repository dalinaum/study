from typing import List, Optional
import heapq
# Definition for singly-linked list.

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        heap = []
        root = result = ListNode(None)

        for i, list in enumerate(lists):
            if list:
                heapq.heappush(heap, (list.val, i, list))

        while heap:
            _, i, result.next = heapq.heappop(heap)
            result = result.next           
            if result.next:
                heapq.heappush(heap, (result.next.val, i, result.next))
        
        return root.next

a = ListNode(1)
a.next = ListNode(4)
a.next.next = ListNode(5)

b = ListNode(1)
b.next = ListNode(3)
b.next.next = ListNode(4)

c = ListNode(2)
c.next = ListNode(6)

d = [a, b, c]
e = Solution().mergeKLists(d)