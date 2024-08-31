from typing import List
import itertools

# 536ms 왜 이렇게 오래걸리지?
# class Solution:
#     def combine(self, n: int, k: int) -> List[List[int]]:
#         result = []

#         def dfs(elements: List[int], start: int, k: int):
#             if k == 0:
#                 result.append(elements[:])
            
#             for i in range(start, n + 1):
#                 elements.append(i)
#                 dfs(elements, i + 1, k - 1)
#                 elements.remove(i)
        
#         dfs([], 1, k)
#         return result

# 69ms - 와우
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        return list(itertools.combinations(range(1, n + 1), k))

a = Solution()
n = 4
k = 2
print(a.combine(n, k))

n = 1
k = 1
print(a.combine(n, k))