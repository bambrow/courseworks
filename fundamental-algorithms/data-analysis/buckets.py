from math import *


def buckets(a, k):
    interval = (0.0+a[-1]-a[0])/k
    d = {}
    for i in range(k):
        d[i+1] = []
    for c in a:
        if (abs(c-a[0]) < 1e-6):
            d[1].append(c)
            continue
        bucket = ceil((0.0+c-a[0])/interval)
        d[bucket].append(c)
    print d
    return


a = [1,1,1,2,2,2,3,3,3]
print a
buckets(a,3)
print ""

s = reduce(lambda x,y: x+y, a)
print s/len(a)
a = map(lambda x: x-s/len(a), a)
print a
buckets(a,3)
print ""

sq = reduce(lambda x,y: x+y, map(lambda x: x*x, a))
print sqrt(sq/len(a))
a = map(lambda x: 1.0*x*len(a)/sq, a)
print a
buckets(a,3)
print ""

a = map(exp, a)
print a
buckets(a,3)
print ""

