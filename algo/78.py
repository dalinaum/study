from typing import List

# 35ms. 책의 풀이를 일방적으로 따라 함.

class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        result: List[List[int]] = []

        def dfs(index: int, path: List[int]):
            result.append(path)
            
            for i in range(index, len(nums)):
                dfs(i + 1, path + [nums[i]])
        
        dfs(0, [])
        return result

a = Solution()
nums = [1,2,3]
print(a.subsets(nums))

# Input: nums = [1,2,3]
# Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

# Input: nums = [0]
# Output: [[],[0]]