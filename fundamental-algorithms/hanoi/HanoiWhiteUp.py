def hanoiWhiteUp(n, A, B, C):
    if (n == 1):
        move(1, A, B)
    else:
        hanoiWhiteUp(n-1, A, C, B)
        move(n, A, B)
        hanoiWhiteUp(n-1, C, A, B)
        hanoiWhiteUp(n-1, A, B, C)

        
def move(k, P, Q):
    print 'move', k, 'from', P, 'to', Q


hanoiWhiteUp(3, 'A', 'B', 'C')