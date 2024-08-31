from typing import List

# class Solution:
#     def threeSum(self, nums: List[int]) -> List[List[int]]:
#         result = []
#         nums.sort()

#         len_nums = len(nums)
#         for i in range(len_nums - 2):
#             if i > 0 and nums[i] == nums[i - 1]:
#                 continue
#             for j in range(i + 1, len_nums - 1):
#                 if j > i + 1 and nums[j] == nums[j - 1]:
#                     continue
#                 for k in range(j + 1, len_nums):
#                     if k > j + 1 and nums[k] == nums[k - 1]:
#                         continue
#                     num_i, num_j, num_k = nums[i], nums[j], nums[k]
#                     if num_i + num_j + num_k == 0:
#                         result.append([num_i, num_j, num_k])
#         return result

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        result = []
        nums.sort()

        len_nums = len(nums)
        for i in range(len_nums - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            left = i + 1
            right = len_nums - 1

            while left < right:
                num_i, num_left, num_right = nums[i], nums[left], nums[right]
                sum = num_i + num_left + num_right

                if sum == 0:
                    result.append([num_i, num_left, num_right])

                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    while left < right and nums[right - 1] == nums[right]:
                        right -= 1
                    left += 1
                    right -= 1
                elif sum < 0:
                    left += 1
                else:
                    right -= 1

        return result

nums = [-1,0,1,2,-1,-4]
a = Solution()
print(a.threeSum(nums))

nums = [0,1,1]
print(a.threeSum(nums))

nums = [0,0,0]
print(a.threeSum(nums))