from typing import List

# class Solution:
#     def twoSum(self, nums: List[int], target: int) -> List[int]:
#         len_nums = len(nums)
#         for i in range(len_nums):
#             for j in range(i + 1, len_nums):
#                 if nums[i] + nums[j] == target:
#                     return [i, j]

# class Solution:
#     def twoSum(self, nums: List[int], target: int) -> List[int]:
#         for i, num in enumerate(nums):
#             complement = target - num
#             if complement in nums[i + 1:]:
#                 return [i, nums[i + 1:].index(complement) + (i + 1)]

# class Solution:
#     def twoSum(self, nums: List[int], target: int) -> List[int]:
#         nums_map = {}
#         for i, num in enumerate(nums):
#             nums_map[num] = i
        
#         for i, num in enumerate(nums):
#             complement = target - num
#             if complement in nums_map and i != nums_map[complement]:
#                 return [i, nums_map[complement]]

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        nums_map = {}        
        for i, num in enumerate(nums):
            complement = target - num
            if complement in nums_map and i != nums_map[complement]:
                return [nums_map[complement], i]
            nums_map[num] = i

nums = [2,7,11,15]
target = 9
a = Solution()
print(a.twoSum(nums, target))