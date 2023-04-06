from typing import List

class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        out = []
        p = 1
        for i in range(len(nums)):
            out.append(p)
            p *= nums[i]
        
        p = 1
        for i in range(len(nums) - 1, -1 , -1):
            out[i] *= p
            p *= nums[i]
        return out


nums = [1,2,3,4]
print(Solution().productExceptSelf(nums))
nums = [-1,1,0,-3,3]
print(Solution().productExceptSelf(nums))