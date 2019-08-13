from math import *


def game_driver(n,p):
    look = []
    for i in range(n+1):
        lr = []
        for j in range(n+1):
            lr.append(-1)
        look.append(lr)
    for i in range(1,n+1):
        look[i][0] = 0
        look[0][i] = 1
    return game(n,n,p,look)


def game(i,j,p,look):
    temp = look[i][j]
    if temp != -1:
        return temp
    temp = p*game(i-1,j,p,look)+(1-p)*game(i,j-1,p,look)
    look[i][j] = temp
    return temp


print game_driver(100,0.49)


def game_loop_driver(n,p):
    look = []
    for i in range(n+1):
        lr = []
        for j in range(n+1):
            lr.append(-1)
        look.append(lr)
    for i in range(1,n+1):
        look[i][0] = 0
        look[0][i] = 1
    return game_loop(n,n,p,look)


def game_loop(x,y,p,look):
    for i in range(1,len(look)):
        for j in range(1,len(look)):
            look[i][j] = p*look[i-1][j]+(1-p)*look[i][j-1]
    return look[x][y]


print game_loop_driver(100,0.4)
