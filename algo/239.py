from typing import List
import collections

# class Solution:
#     def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
#         window = collections.deque()
#         results = []
#         current_max = float('-inf')

#         for i, v in enumerate(nums):
#             window.append(v)
#             if i < k - 1:
#                 continue

#             if current_max == float('-inf'):
#                 current_max = max(window)
#             elif v > current_max:
#                 current_max = v

#             results.append(current_max)

#             if current_max == window.popleft():
#                 current_max = float('-inf')
#         return results

# 책의 풀이인데 타임오버가 발생함. 이렇게 풀 수 없음.

# class Solution:
#     def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
#         dq = collections.deque()
#         results = []

#         for i, v in enumerate(nums):
#             while dq and nums[dq[0]] < v:
#                 dq.popleft()

#             dq.appendleft(i)

#             while dq[-1] <= i - k:
#                 dq.pop()
            
#             if i >= k - 1:
#                 results.append(nums[dq[-1]])

#         return results

# 1712ms 통관

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        dq = collections.deque()
        results = []

        for i, v in enumerate(nums):
            while dq and nums[dq[-1]] < v:
                dq.pop()

            dq.append(i)

            while dq[0] <= i - k:
                dq.popleft()
            
            if i >= k - 1:
                results.append(nums[dq[0]])

        return results
    
# 1607ms 디크리싱 데크가 더 나음. (솔루션 참고)

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        dq = collections.deque()
        results = []

        for i, v in enumerate(nums):
            while dq and nums[dq[-1]] < v:
                dq.pop()

            dq.append(i)

            if i < k - 1:
                continue

            while dq[0] <= i - k:
                dq.popleft()
            
            results.append(nums[dq[0]])

        return results

# 비슷함. 코드를 약간 수정

nums = [1,3,-1,-3,5,3,6,7]
k = 3
# nums = [1]
# k = 1
print(Solution().maxSlidingWindow(nums, k))

