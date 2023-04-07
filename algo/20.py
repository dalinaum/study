# 최초 풀이, 24ms

# class Solution:
#     def isValid(self, s: str) -> bool:
#         stack = []
#         for i in s:
#             if i in "({[":
#                 stack.append(i)
#             elif i in "]})":
#                 if not stack:
#                     return False
#                 open = stack.pop()
#                 if open == '(' and i != ')' or open == '{' and i != '}' or open =='[' and i!= ']':
#                     return False
#         return not stack


# 30~35ms 교재 풀이. 깔끔하지만 느림
class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        table = {
            ')': '(',
            '}': '{',
            ']': '['
        }
        for i in s:
            if i in "({[":
                stack.append(i)
            elif not stack or table[i] != stack.pop():
                return False
        return not stack

a = Solution()
s = "()"
print(a.isValid(s))

s = "()[]{}"
print(a.isValid(s))

s = "(]"
print(a.isValid(s))