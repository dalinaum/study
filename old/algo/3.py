# 62ms 책의 풀이에 기반

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        start, max_length = 0, 0
        seen = {}

        for i, char in enumerate(s):
            if char in seen and start <= seen[char]:
                start = seen[char] + 1
            max_length = max(max_length, i - start + 1)
            seen[char] = i
        return max_length


a = Solution()
s = "abcabcbb"
print(a.lengthOfLongestSubstring(s))
s = "bbbbb"
print(a.lengthOfLongestSubstring(s))
s = "pwwkew"
print(a.lengthOfLongestSubstring(s))
s = "aab"
print(a.lengthOfLongestSubstring(s))