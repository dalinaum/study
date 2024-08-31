from collections import Counter

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        missing = len(t)
        needs = Counter(t)
        start, end, lp = 0, 0, 0

        for rp, c in enumerate(s, 1):
            missing -= needs[c] > 0
            needs[c] -= 1

            if missing == 0:
                while lp < rp and needs[s[lp]] < 0:
                    needs[s[lp]] += 1
                    lp += 1

                if not end or rp - lp < end - start:
                    start, end = lp, rp
        
        return s[start:end]

s = "ADOBECODEBANC"
t = "ABC"
print(Solution().minWindow(s, t))

s = "a"
t = "a"
print(Solution().minWindow(s, t))

s = "a"
t = "aa"
print(Solution().minWindow(s, t))