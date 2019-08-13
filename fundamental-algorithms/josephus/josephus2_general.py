
def josephus2(k):
    if k <= 1:
        return 1
    return (josephus2(k-1)+1)%k+1


for i in range(1,21):
    print josephus2(i)
