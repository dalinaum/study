from __future__ import annotations
from typing import Optional

class ListNode:

    def __init__(self, value: int = 0, prev: Optional[ListNode] = None, next: Optional[ListNode] = None):
        self.value = value
        self.prev = prev
        self.next = next
        

class MyCircularDeque:

    def __init__(self, k: int):
        self.head = ListNode(None)
        self.tail = ListNode(None)
        self.k = k
        self.len = 0
        self.head.next, self.tail.prev = self.tail, self.head

    def _add(self, before: ListNode, newNode: ListNode):
        next = before.next
        before.next = newNode
        newNode.prev, newNode.next = before, next
        next.prev = newNode

    def insertFront(self, value: int) -> bool:
        if self.len == self.k:
            return False
        self.len += 1
        self._add(self.head, ListNode(value))
        return True

    def insertLast(self, value: int) -> bool:
        if self.len == self.k:
            return False
        self.len += 1
        self._add(self.tail.prev, ListNode(value))
        return True
    
    def _del(self, before: ListNode):
        node_right = before.next.next
        before.next = node_right
        node_right.prev = before

    def deleteFront(self) -> bool:
        if not self.len:
            return False
        self.len -= 1
        self._del(self.head)
        return True

    def deleteLast(self) -> bool:
        if not self.len:
            return False
        self.len -= 1
        self._del(self.tail.prev.prev)
        return True
        
    def getFront(self) -> int:
        return self.head.next.value if self.len else -1
        
    def getRear(self) -> int:
        return self.tail.prev.value if self.len else -1
        

    def isEmpty(self) -> bool:
        return not self.len

    def isFull(self) -> bool:
        return self.k == self.len

d = MyCircularDeque(3)
print(d.insertLast(1))
print(d.insertLast(2))
print(d.insertFront(3))
print(d.insertFront(4))
print(d.getRear())
# Your MyCircularDeque object will be instantiated and called as such:
# obj = MyCircularDeque(k)
# param_1 = obj.insertFront(value)
# param_2 = obj.insertLast(value)
# param_3 = obj.deleteFront()
# param_4 = obj.deleteLast()
# param_5 = obj.getFront()
# param_6 = obj.getRear()
# param_7 = obj.isEmpty()
# param_8 = obj.isFull()