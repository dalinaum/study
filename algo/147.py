from typing import Optional
import sys

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 1600, 1500ms대 타임 아웃 위기.
# class Solution:
#     def insertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
#         current = root = ListNode(-sys.maxsize)

#         while head:
#             # and current.next.val and 조건을 넣는다면 0 떄문에 걸림. 주의
#             while current.next and current.next.val < head.val:
#                 current = current.next
#             current.next, head.next, head = head, current.next, head.next
#             current = root

#         return root.next

# 161ms
class Solution:
    def insertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        current = root = ListNode(-sys.maxsize)

        while head:
            while current.next and current.next.val < head.val:
                current = current.next
            current.next, head.next, head = head, current.next, head.next

            if head and head.val < current.val:
                current = root

        return root.next


a = Solution()
head = ListNode(4)
head.next = ListNode(2)
head.next.next = ListNode(1)
head.next.next.next = ListNode(3)

result = a.insertionSortList(head)
while result:
    print(result.val)
    result = result.next

# head = [4,2,1,3]

head = ListNode(-1)
head.next = ListNode(5)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(0)

result = a.insertionSortList(head)
while result:
    print(result.val)
    result = result.next

# head = [-1,5,3,4,0]