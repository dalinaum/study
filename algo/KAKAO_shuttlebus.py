def solution(n, t, m, timetable):
    timetable = [int(t[:2]) * 60 + int(t[3:]) for t in timetable]
    timetable.sort()
    current = 60 * 9
    for _ in range(n):
        for _ in range(m):
            if timetable and timetable[0] <= current:
                candidate = timetable.pop(0) - 1
            else:
                candidate = current
        current += t
    
    h, m = divmod(candidate, 60)
    answer = str(h).zfill(2) + ':' + str(m).zfill(2)
    return answer

n = 1
t = 1
m = 5
timetable = ["08:00", "08:01", "08:02", "08:03"]

print(solution(n, t, m, timetable))