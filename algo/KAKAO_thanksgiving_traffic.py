import sys
from datetime import datetime


def solution(lines):
    logs = []
    for line in lines:
        (date, time, duration) = line.split()
        duration = float(duration[:-1])
        dt = datetime.strptime(
            f"{date} {time}", "%Y-%m-%d %H:%M:%S.%f").timestamp()
        logs.append((dt, -1))
        logs.append((dt - duration + 0.001, 1))
    logs.sort(key=lambda x: x[0])

    accumulated = 0
    max_request = 0

    for i, log1 in enumerate(logs):
        current = accumulated

        for log2 in logs[i:]:
            if log2[0] - log1[0] > 0.999:
                break
            if log2[1] > 0:
                current += 1

        max_request = max(max_request, current)
        accumulated += log1[1]

    return max_request


# lines = [
#     "2016-09-15 01:00:04.001 2.0s",
#     "2016-09-15 01:00:07.000 2s"
# ]

# lines = [
#     "2016-09-15 01:00:04.002 2.0s",
#     "2016-09-15 01:00:07.000 2s"
# ]

lines = [
    "2016-09-15 20:59:57.421 0.351s",
    "2016-09-15 20:59:58.233 1.181s",
    "2016-09-15 20:59:58.299 0.8s",
    "2016-09-15 20:59:58.688 1.041s",
    "2016-09-15 20:59:59.591 1.412s",
    "2016-09-15 21:00:00.464 1.466s",
    "2016-09-15 21:00:00.741 1.581s",
    "2016-09-15 21:00:00.748 2.31s",
    "2016-09-15 21:00:00.966 0.381s",
    "2016-09-15 21:00:02.066 2.62s"
]

print(solution(lines))
