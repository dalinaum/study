import collections

class ListNode:
    def __init__(self, key=-1, val=0, next=None):
        self.key = key
        self.val = val
        self.next = next

class MyHashMap:

    def __init__(self):
        self.size = 1000
        self.table = collections.defaultdict(ListNode)

    def put(self, key: int, value: int) -> None:
        slot = self.table[key % self.size]
        if slot.key == -1:
            slot.key = key
            slot.val = value
            return
        while slot:
            if slot.key == key:
                slot.val = value
                return
            if slot.next is None:
                slot.next = ListNode(key, value)
                return
            slot = slot.next

    def get(self, key: int) -> int:
        slot = self.table[key % self.size]
        while slot:
            if slot.key == key:
                return slot.val
            slot = slot.next
        return -1

    def remove(self, key: int) -> None:
        slot = self.table[key % self.size]
        if slot.key == key:
            self.table[key % self.size] = slot.next if slot.next else ListNode()

        prev, slot = slot, slot.next
        while slot:
            if slot.key == key:
                prev.next = slot.next
                break
            slot, prev = slot.next, slot

m = MyHashMap()
m.put(1, 1)
m.put(2, 2)
print(m.get(1))
print(m.get(3))
m.put(2, 1)
print(m.get(2))
m.remove(2)
print(m.get(2))