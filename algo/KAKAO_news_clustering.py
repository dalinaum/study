# from collections import Counter

# Counter를 카운터로 사용.
# def solution(str1, str2):
#     str1, str2 = str1.lower(), str2.lower()
#     list1, list2 = [], []
#     for i in range(1, len(str1)):
#         pair = str1[i - 1 : i + 1]
#         if pair.isalpha():
#             list1.append(pair)
    
#     for i in range(1, len(str2)):
#         pair = str2[i - 1 : i + 1]
#         if pair.isalpha():
#             list2.append(pair)
    
#     counter1 = Counter(list1)
#     counter2 = Counter(list2)
#     seen = set(list1)
#     seen.update(list2)

#     denominator = 0
#     numerator = 0

#     for i in seen:
#         denominator += max(counter1[i], counter2[i])
#         numerator += min(counter1[i], counter2[i])
        
#     if denominator == 0:
#         return 65536
#     answer = int(numerator / denominator * 65536)
#     return answer


# 카운터의 and와 or을 사용.
from collections import Counter

def solution(str1, str2):
    str1, str2 = str1.lower(), str2.lower()
    list1 = [str1[i - 1 : i + 1] for i in range(1, len(str1)) if str1[i - 1 : i + 1].isalpha()]
    list2 = [str2[i - 1 : i + 1] for i in range(1, len(str2)) if str2[i - 1 : i + 1].isalpha()]
    
    counter1 = Counter(list1)
    counter2 = Counter(list2)

    numerator = sum((counter1 & counter2).values())
    denominator = sum((counter1 | counter2).values())
    
    return 65536 if denominator == 0 else int(numerator / denominator * 65536)

# str1 = "FRANCE"
# str2 = "french"
# str1 = "handshake"
# str2 = "shake hands"
str1 = "aa1+aa2"
str2 = "AAAA12"
# str1 = "E=M*C^2"
# str2 = "e=m*c^2"
print(solution(str1, str2))
