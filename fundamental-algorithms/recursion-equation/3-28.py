# a
from math import *

def rec(n):
    if n == 1:
        return 1
    return n+2*rec(n/2)


def form(n):
    return n*log(n,2)+n


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(2,i)),
    printAll(pow(2, i))
print ""

#b
from math import *

def rec(n):
    if n == 1:
        return 1
    return n+3*rec(n/3)


def form(n):
    return n*log(n,3)+n


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(3,i)),
    printAll(pow(3, i))
print ""

#c
from math import *

def rec(n):
    if n == 1:
        return 1
    return n*n+4*rec(n/2)


def form(n):
    return n*n*log(n,2)+n*n


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(2,i)),
    printAll(pow(2,i))
print ""

#d
from math import *

def rec(n):
    if n == 1:
        return 1
    return n*n+9*rec(n/3)


def form(n):
    return n*n*log(n,3)+n*n


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(3,i)),
    printAll(pow(3,i))
print ""

#f
from math import *

def rec(n):
    if n == 1:
        return 1
    return n*n+2*rec(n/2)


def form(n):
    return 2*n*n-n


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(2,i)),
    printAll(pow(2,i))
print ""

#k
from math import *

def rec(n):
    if n == 1:
        return -1
    if n == 3:
        return 0
    if n == 9:
        return 81
    return n*n+9*rec(n/3)


def form(n):
    return n*n*(log(n,3)-1)


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(3,i)),
    printAll(pow(3,i))
print ""

#l
from math import *

def rec(n):
    if n == 1:
        return 1
    return sqrt(n)+3*rec(n/9)


def form(n):
    return sqrt(n)*log(n,9)+sqrt(n)


def printAll(n):
    print '[', int(rec(n)), int(form(n)), ']'


for i in range (20):
    print i, int(pow(9,i)),
    printAll(pow(9,i))
print ""
