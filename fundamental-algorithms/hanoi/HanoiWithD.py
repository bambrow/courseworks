def hanoiWithD(n, A, B, C, D):
    if (n == 1):
        move(1, A, B)
    elif (n == 2):
        move(1, A, C)
        move(2, A, B)
        move(1, C, B)
    else:
        hanoiWithD(n-2, A, C, B, D)
        move(n-1, A, D)
        move(n, A, B)
        move(n-1, D, B)
        hanoiWithD(n-2, C, B, A, D)

        
def move(k, P, Q):
    print 'move', k, 'from', P, 'to', Q


hanoiWithD(6, 'A', 'B', 'C', 'D')