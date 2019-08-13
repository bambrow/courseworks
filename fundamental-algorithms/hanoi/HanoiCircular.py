def hanoiCircularOne(n, A, B, C):
    if (n == 1):
        move(1, A, B)
    else:
        hanoiCircularTwo(n-1, A, C, B)
        move(n, A, B)
        hanoiCircularTwo(n-1, C, B, A)


def hanoiCircularTwo(n, A, C, B):
    if (n == 1):
        move(1, A, B)
        move(1, B, C)
    else:
        hanoiCircularTwo(n-1, A, C, B)
        move(n, A, B)
        hanoiCircularOne(n-1, C, A, B)
        move(n, B, C)
        hanoiCircularTwo(n-1, A, C, B)
        

def move(k, P, Q):
    print 'move', k, 'from', P, 'to', Q


hanoiCircularOne(3, 'A', 'B', 'C')