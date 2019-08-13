from math import *


def mtx_driver(d):
    look = []
    for i in range(len(d)-1):
        lr = []
        for j in range(len(d)-1):
            lr.append(float('inf'))
        look.append(lr)
    return mtx(0,len(d)-2,d,look)


def mtx(i,j,d,look):
    if j == i:
        return 0
    if look[i][j] != float('inf'):
        return look[i][j]
    temp = float('inf')
    for k in range(i,j):
        temp = min(temp, mtx(i,k,d,look)+d[i]*d[k+1]*d[j+1]+mtx(k+1,j,d,look))
    look[i][j] = temp
    return temp


v = [20,100,10,20,100,5]

print mtx_driver(v)


def mtx_loop_driver(d):
    look = []
    for i in range(len(d)-1):
        lr = []
        for j in range(len(d)-1):
            lr.append(0)
        look.append(lr)
    return mtx_loop(0,len(d)-2,d,look)


def mtx_loop(x,y,d,look):
    l = len(d)-1
    for p in range(0,l):
        for i in range(0,l-p):
            j = p+i
            temp = float('inf')
            for k in range(i,j):
                temp = min(temp, look[i][k]+d[i]*d[k+1]*d[j+1]+look[k+1][j])
                look[i][j] = temp
    return look[x][y]


print mtx_loop_driver(v)
