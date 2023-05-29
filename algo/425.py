from collections import Counter

class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        counts = Counter()
        left = 0
        for right, v in enumerate(s, 1):
            counts[v] += 1
            max_char_n = counts.most_common(1)[0][1]
            if right - left - max_char_n > k:
                counts[s[left]] -= 1
                left += 1
        return right - left

s = "AAABBC"
k = 2
print(Solution().characterReplacement(s, k))