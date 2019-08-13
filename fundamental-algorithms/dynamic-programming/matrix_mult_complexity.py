from math import *
from random import *

v = []
z = 0


def mtx_loop_driver(d):
    look = []
    for i in range(len(d)-1):
        lr = []
        for j in range(len(d)-1):
            lr.append(0)
        look.append(lr)
    return mtx_loop(0,len(d)-2,d,look)


def mtx_loop(x,y,d,look):
    global z
    l = len(d)-1
    for p in range(0,l):
        for i in range(0,l-p):
            j = p+i
            temp = float('inf')
            for k in range(i,j):
                z += 1
                temp = min(temp, look[i][k]+d[i]*d[k+1]*d[j+1]+look[k+1][j])
                look[i][j] = temp
    return look[x][y]


v.append(randint(0,100))
v.append(randint(0,100))

for i in range(79):
    z = 0
    k = randint(0,100)
    v.append(k)
    mtx_loop_driver(v)
    print i+2,z
