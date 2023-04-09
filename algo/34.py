from typing import List

# 45ms. 처음만든 방식. 책은 반대로 빼어가면서 만드는 듯?
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        def dfs(permutaion: List[int] = []):
            if len(permutaion) == len(nums):
                result.append(permutaion)
                return

            for i in nums:
                if i not in permutaion:
                    new_permutation = permutaion.copy()
                    new_permutation.append(i)
                    dfs(new_permutation)
      
        if not nums:
            return []
        
        result = []
        dfs()
        return result

nums = [1,2,3]

a = Solution()
print(a.permute(nums))

nums = [0,1]
print(a.permute(nums))

nums = [1]
print(a.permute(nums))