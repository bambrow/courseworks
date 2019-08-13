child = [[3,1,2,3],[2,4,5],[1,6],[1,7],
         [2,8,9],[1,10],[0],[3,11,12,13],
         [0],[0],[0],[0],[2,14,15],[0],
         [0],[0]]


def DFS(vertex):
    if vertex is None:
        return
    for c in child[vertex][1:(child[vertex][0]+1)]:
        DFS(c)
    print vertex

DFS(0)
