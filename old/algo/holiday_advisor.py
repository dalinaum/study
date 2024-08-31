from typing import List

def solution(A: List[int]) -> int:
    needs = {key: 1 for key in A}
    missing = len(needs)
    start, end, lp = 0, 0, 0

    for rp, c in enumerate(A, 1):
        missing -= needs[c] > 0
        needs[c] -= 1
        
        if missing == 0:
            while lp < rp and needs[A[lp]] < 0:
                needs[A[lp]] += 1
                lp += 1
            
            if not end or rp - lp < end - start:
                start, end = lp, rp
                print(A[start:end])
    
    return end - start

A = [7,4,7,3,4,1,7]
# A = [2,1,1,3,2,1,1,3]
# A = [7,3,2,3,1,2,1,7,7,1]
print(solution(A))