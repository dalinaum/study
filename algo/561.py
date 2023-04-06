from typing import List

# class Solution:
#     def arrayPairSum(self, nums: List[int]) -> int:
#         nums.sort()
#         pair = []
#         sum = 0

#         for i in nums:
#             pair.append(i)
#             if len(pair) == 2:
#                 sum += min(pair)
#                 pair = []
#         return sum

# class Solution:
#     def arrayPairSum(self, nums: List[int]) -> int:
#         nums.sort()
#         sum = 0

#         for i, num in enumerate(nums):
#             if i % 2 == 0:
#                 sum += num
#         return sum

class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        return sum(sorted(nums)[::2])

nums = [1,4,3,2]
print(Solution().arrayPairSum(nums))