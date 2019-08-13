
def josephus2(k):
    if k <= 1:
        return 1
    if k%2 == 0:
        return 2*josephus2(k/2)-1
    return 2*josephus2((k-1)/2)+1


for i in range(1,21):
    print josephus2(i)
