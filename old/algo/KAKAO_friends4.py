def solution(m, n, board):
    board = [list(i) for i in board]

    # for i in board:
    #     print(i)
    # print()

    matched = True
    while matched:
        matched = []
        for i in range(m - 1):
            for j in range(n - 1):
                if board[i][j] == board[i][j + 1]\
                    == board[i + 1][j] == board[i + 1][j + 1]\
                        != '#':
                    matched.append((i, j))
        
        for i, j in matched:
            board[i][j] = '#'
            board[i][j + 1] = '#'
            board[i + 1][j] = '#'
            board[i + 1][j + 1] = '#'
        
        # for i in board:
        #     print(i)
        # print()
        
        for _ in range(m - 1):
            for i in range(m - 1):
                for j in range(n):
                    if board[i + 1][j] == '#':
                        board[i + 1][j], board[i][j] = board[i][j], '#'
            
        # for i in board:
        #     print(i)
        # print()
    
    return sum(i.count('#') for i in board)


# m = 4
# n = 5
# board = ["CCBDE", "AAADE", "AAABF", "CCBBF"]

m = 6
n = 6
board = ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]
print(solution(m, n, board))
