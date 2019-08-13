def hanoiSlow(n, A, B, C):
    if (n == 1):
        move(1, A, C)
        move(1, C, B)
    else:
        hanoiSlow(n-1, A, B, C)
        move(n, A, C)
        hanoiSlow(n-1, B, A, C)
        move(n, C, B)
        hanoiSlow(n-1, A, B, C)

        
def move(k, P, Q):
    print 'move', k, 'from', P, 'to', Q


hanoiSlow(3, 'A', 'B', 'C')