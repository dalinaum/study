import collections

# 46ms 재귀 방식

# class Solution:
#     def removeDuplicateLetters(self, s: str) -> str:
#         set_s = set(s)

#         for i in sorted(set_s):
#             suffix = s[s.index(i):]
#             if set_s == set(suffix):
#                 return i + self.removeDuplicateLetters(suffix.replace(i, ''))
#         return ''

# 34ms set와 Counter를 이용한 방식
class Solution:
    def removeDuplicateLetters(self, s: str) -> str:
        counter, stack, seen = collections.Counter(s), [], set()
        for char in s:
            counter[char] -= 1
            if char in seen:
                continue
            while stack and char < stack[-1] and counter[stack[-1]]:
                seen.remove(stack.pop())                
            stack.append(char)
            seen.add(char)
        return ''.join(stack)

a = Solution()

s = "bcabc"
print(a.removeDuplicateLetters(s))

s = "cbacdcbc"
print(a.removeDuplicateLetters(s))
