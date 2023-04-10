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

# 34ms 이게 훨씬 빠르네?!
# class Solution:
#     def permute(self, nums: List[int]) -> List[List[int]]:
#         def dfs(permutaion: List[int] = []):
#             if len(permutaion) == len(nums):
#                 result.append(permutaion)
#                 return

#             for i in nums:
#                 if i not in permutaion:
#                     new_permutation = permutaion[:]
#                     new_permutation.append(i)
#                     dfs(new_permutation)
      
#         if not nums:
#             return []
        
#         result = []
#         dfs()
#         return result

# 40ms 책의 풀이. 완전히 생각도 못한 스타일. 내 풀이가 나은 듯.
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        prev_elements: List[int] = []
        result: List[List[int]] = []

        def dfs(elements: List[int] = []):
            if len(elements) == 0:
                result.append(prev_elements[:])
                return
            
            for i in elements:
                next_elements = elements[:]
                next_elements.remove(i)
                prev_elements.append(i)
                dfs(next_elements)
                prev_elements.pop()
        
        dfs(nums)
        return result


nums = [1,2,3]

a = Solution()
print(a.permute(nums))

nums = [0,1]
print(a.permute(nums))

nums = [1]
print(a.permute(nums))