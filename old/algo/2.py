from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 61ms
# class Solution:
#     def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
#         head = p = None
#         carry = 0

#         if not l1:
#             return l2
        
#         if not l2:
#             return l1

#         while l1 or l2 or carry:
#             sum = carry
#             if l1:
#                 sum += l1.val
#                 l1 = l1.next
#             if l2:
#                 sum += l2.val
#                 l2 = l2.next
#             val = sum % 10
#             carry = sum // 10

#             if not p:
#                 head = p = ListNode(val)
#             else:
#                 p.next  = ListNode(val)
#                 p = p.next

#         return head


# 74ms
# 전가산기 구현
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        head = p = None
        carry = 0

        if not l1:
            return l2
        
        if not l2:
            return l1

        while l1 or l2 or carry:
            sum = carry
            if l1:
                sum += l1.val
                l1 = l1.next
            if l2:
                sum += l2.val
                l2 = l2.next
            carry, val = divmod(sum, 10)

            if not p:
                head = p = ListNode(val)
            else:
                p.next  = ListNode(val)
                p = p.next

        return head


#2 4 3
a = ListNode(2)
a.next = ListNode(4)
a.next.next = ListNode(3)

# while a:
#     print(a.val)
#     a = a.next
#5 6 4

b = ListNode(5)
b.next = ListNode(6)
b.next.next = ListNode(4)

# while b:
#     print(b.val)
#     b = b.next
#result: 7 0 8

c = Solution().addTwoNumbers(a, b)
while c:
    print(c.val)
    c = c.next