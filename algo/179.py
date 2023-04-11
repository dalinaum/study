from typing import List

# 73ms 내 풀이
# class Solution:
#     def largestNumber(self, nums: List[int]) -> str:
#         sorted: List[int] = []
#         for num in nums:
#             done = False
#             for i, n in enumerate(sorted):
#                 if self.has_to_swap(n, num):
#                     sorted.insert(i, num)
#                     done = True
#                     break
#             if not done:
#                 sorted.append(num)
#         print(sorted)
#         return str(int(''.join(map(str, sorted))))
    
#     def has_to_swap(self, num1: int, num2: int) -> bool:
#         return str(num1) + str(num2) < str(num2) + str(num1)

# 75ms 책의 풀이. 성능은 유사한 듯.
class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        for k in range(1, len(nums)):
            for i in range(k - 1, -1, -1):
                j = i + 1
                num_i, num_j = nums[i], nums[j]
                if self.has_to_swap(num_i, num_j):
                    nums[j], nums[i] = num_i, num_j
                else:
                    # 반드시 탈출하게 하자.
                    break
         
        return str(int(''.join(map(str, nums))))
    
    def has_to_swap(self, num1: int, num2: int) -> bool:
        return str(num1) + str(num2) < str(num2) + str(num1)

a = Solution()
nums = [10,2]
print(a.largestNumber(nums))

nums = [3,30,34,5,9]
print(a.largestNumber(nums))
