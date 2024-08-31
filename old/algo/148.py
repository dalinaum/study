from typing import Optional, List

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 정석적인 풀이 789ms

# class Solution:
#     def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
#         if not (head and head.next):
#             return head
        
#         half, slow, fast = None, head, head
#         while fast and fast.next:
#             half, slow, fast = slow, slow.next, fast.next.next
#         half.next = None

#         l1 = self.sortList(head)
#         l2 = self.sortList(slow)
#         return self.mergeTwoLists(l1, l2)

#     def mergeTwoLists(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
#         if l1 and l2:
#             if l1.val > l2.val:
#                 l1, l2 = l2, l1
#             l1.next = self.mergeTwoLists(l1.next, l2)
#         return l1 or l2

# 퀵소트는 거의 불가능일 듯. (피벗 설정 어려움. 최악 성능)

# 책에서는 기존의 head리스트의 값을 변경. 그건 별로인 풀이인 듯. 369ms
class Solution:
    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        inputs: List[int] = []
        while head:
            inputs.append(head.val)
            head = head.next
        
        inputs.sort()
        
        root = p = ListNode(0)
        for i in inputs:
            p.next = ListNode(i)
            p = p.next
        return root.next


s = Solution()
head = ListNode(4)
head.next = ListNode(2)
head.next.next = ListNode(1)
head.next.next.next = ListNode(3)
result = s.sortList(head)
while result:
    print(result.val)
    result = result.next
# head = [4,2,1,3]


head = ListNode(-1)
head.next = ListNode(5)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(0)
result = s.sortList(head)
while result:
    print(result.val)
    result = result.next

# [-1,5,3,4,0]