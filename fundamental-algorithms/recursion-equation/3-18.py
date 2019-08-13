from math import *

def rec(n):
    if n == 1:
        return 4
    return min(1024*n+2*rec(n/2), 4*n*n)


def recA(n):
    if n == 1:
        return 4
    return 1024*n+2*rec(n/2)


def recB(n):
    return 4*n*n;


def printAll(n):
    print '[', int(rec(n)), int(recA(n)), int(recB(n)), ']'


for i in range (20):
    print i, int(pow(2,i)),
    printAll(pow(2, i))

# equation
from math import *

def rec(n):
    if n == 1:
        return 4
    return min(1024*n+2*rec(n/2), 4*n*n)


def form(n):
    if n <= 2**9:
        return 4*n*n
    return 1024*n*(log(n,2)-7)


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (30):
    print i, int(pow(2,i)),
    printAll(pow(2, i))
