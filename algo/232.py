import collections

class MyQueue:

    def __init__(self):
        self.q1 = collections.deque()
        self.q2 = collections.deque()

    def push(self, x: int) -> None:
        while self.q2:
            self.q1.append(self.q2.pop())
        self.q1.append(x)
        while self.q1:
            self.q2.append(self.q1.pop())

    def pop(self) -> int:
        return self.q2.pop()

    def peek(self) -> int:
        return self.q2[-1]

    def empty(self) -> bool:
        return not self.q2