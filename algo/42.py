from typing import List

# class Solution:
#     def trap(self, height: List[int]) -> int:
#         left, right = 0, len(height) - 1
#         left_max, right_max = 0, 0
#         volumn = 0

#         while left <= right:
#             left_max = max(left_max, height[left])
#             right_max = max(right_max, height[right])

#             if left_max <= right_max:
#                 volumn += left_max - height[left]
#                 left += 1
#             else:
#                 volumn += right_max - height[right]
#                 right -= 1
#         return volumn

class Solution:
    def trap(self, height: List[int]) -> int:
        stack = []
        volumn = 0

        for i in range(len(height)):
            while stack and height[i] > height[stack[-1]]:
                top = stack.pop()
                if not stack:
                    break

                distance = i - stack[-1] - 1
                gap = min(height[i], height[stack[-1]]) - height[top]
                volumn += distance * gap

            stack.append(i)
            
        return volumn

height = [0,1,0,2,1,0,1,3,2,1,2,1]
a = Solution()

print(a.trap(height))

height = [4,2,0,3,2,5]
print(a.trap(height))