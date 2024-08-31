from typing import List

class Solution:
    def reverseString(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        s[:] = s[::-1]

a = Solution()
s = ["h","e","l","l","o"]
a.reverseString(s)
print(s)
