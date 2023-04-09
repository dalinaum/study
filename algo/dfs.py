graph = {
    1: [2, 3, 4],
    2: [5],
    3: [5],
    4: [],
    5: [6, 7],
    6: [],
    7: [3]
}

def recursive_dsf(v, discovered = []):
    discovered.append(v)

    for w in graph[v]:
        if w not in discovered:
            discovered = recursive_dsf(w, discovered)
    return discovered

print(f'recursive dfs: {recursive_dsf(1)}')

def iterative_dfs(v):
    discovered = []
    stack = [v]

    while stack:
        v = stack.pop()
        if v not in discovered:
            discovered.append(v)
            for w in graph[v]:
                stack.append(w)
    return discovered

print(f'iterative dfs: {iterative_dfs(1)}')