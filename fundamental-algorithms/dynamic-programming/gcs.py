from math import *

# the following code is written with 
# lookup table and path recovery

# greatest common sequence

def gcs_driver(A,B):
    look = []
    path = []
    for i in range(len(A)):
        lr = []
        pr = []
        for j in range(len(B)):
            lr.append(-1)
            pr.append(-1)
        look.append(lr)
        path.append(pr)
    gcs(A,B,len(A)-1,len(B)-1,look,path)
    return gcs_str(A,B,len(A)-1,len(B)-1,path)


def gcs(A,B,i,j,look,path):
    if i == -1 or j == -1:
        return 0
    temp = look[i][j]
    if temp != -1:
        return temp
    r = s = -1
    if A[i] == B[j]:
        temp = 1 + gcs(A,B,i-1,j-1,look,path)
        r = i-1
        s = j-1
    elif gcs(A,B,i-1,j,look,path) >= gcs(A,B,i,j-1,look,path):
        temp = gcs(A,B,i-1,j,look,path)
        r = i-1
        s = j
    else:
        temp = gcs(A,B,i,j-1,look,path)
        r = i
        s = j-1
    look[i][j] = temp
    path[i][j] = (r,s)
    return temp


def gcs_str(A,B,i,j,path):
    str = ""
    if i > -1 and j > -1:
        r,s = path[i][j]
        str += gcs_str(A,B,r,s,path)
        if r == i-1 and s == j-1:
            str += A[i]
    return str

a = "APPPDAX"
b = "APNBDTX"
c = "AQQPDJASICAXXASDWCCSAWD"
d = "AQXMAJMLQ"

print gcs_driver(a,b)

# greatest common sequence: look both ways

def gbs_driver(A):
    look = []
    path = []
    for i in range(len(A)):
        lr = []
        pr = []
        for j in range(len(A)):
            lr.append(-1)
            pr.append(-1)
        look.append(lr)
        path.append(pr)
    gbs(A,len(A)-1,0,look,path)
    return gbs_str(A,len(A)-1,0,path)


def gbs(A,i,j,look,path):
    if i == -1 or j == len(A):
        return 0
    temp = look[i][j]
    if temp != -1:
        return temp
    r = s = -1
    if A[i] == A[j]:
        temp = 1 + gbs(A,i-1,j+1,look,path)
        r = i-1
        s = j+1
    elif gbs(A,i-1,j,look,path) >= gbs(A,i,j+1,look,path):
        temp = gbs(A,i-1,j,look,path)
        r = i-1
        s = j
    else:
        temp = gbs(A,i,j+1,look,path)
        r = i
        s = j+1
    look[i][j] = temp
    path[i][j] = (r,s)
    return temp


def gbs_str(A,i,j,path):
    str = ""
    if i > -1 and j < len(A):
        r,s = path[i][j]
        str += gbs_str(A,r,s,path)
        if r == i-1 and s == j+1:
            str += A[i]
    return str

print gbs_driver(c)


# longest palindrome

def gps_driver(A):
    look = []
    path = []
    for i in range(len(A)):
        lr = []
        pr = []
        for j in range(len(A)):
            lr.append(-1)
            pr.append(-1)
        look.append(lr)
        path.append(pr)
    gps(A,len(A)-1,0,look,path)
    return gps_str(A,len(A)-1,0,path)


def gps(A,i,j,look,path):
    if j > i:
        return 0
    if j == i:
        return 1
    temp = look[i][j]
    if temp != -1:
        return temp
    r = s = -1
    if A[i] == A[j]:
        temp = 2 + gps(A,i-1,j+1,look,path)
        r = i-1
        s = j+1
    elif gps(A,i-1,j,look,path) >= gps(A,i,j+1,look,path):
        temp = gps(A,i-1,j,look,path)
        r = i-1
        s = j
    else:
        temp = gps(A,i,j+1,look,path)
        r = i
        s = j+1
    look[i][j] = temp
    path[i][j] = (r,s)
    return temp


def gps_str(A,i,j,path):
    str = ""
    if j < i:
        r,s = path[i][j]
        str += gps_str(A,r,s,path)
        if r == i-1 and s == j+1:
            str = A[i] + str + A[i]
    if j == i:
        str += A[i]
    return str

print gps_driver(d)
