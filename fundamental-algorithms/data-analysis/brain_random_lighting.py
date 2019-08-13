import random


def player1(i):
    print 'Player 1 round ' + str(i) 
    for k in sort_count:
        if light[k] != 'None':
            continue
        lighted = []
        light[k] = 'Red'
        lighted.append(k)
        row = matrix[k]
        for j in range(100):
            if row[j] == 1:
                light[j] = 'Red'
                lighted.append(j)
        print 'Player 1 decide to light ' + str(len(lighted)) + ' lights to red'
        print lighted
        break
    # print light
    print 'Red: ' + str(sum(l == 'Red' for l in light))
    print 'Blue: ' + str(sum(l == 'Blue' for l in light))
    print ''


def player2(i):
    print 'Player 2 round ' + str(i) 
    for k in sort_count:
        if light[k] != 'None':
            continue
        lighted = []
        light[k] = 'Blue'
        lighted.append(k)
        row = matrix[k]
        for j in range(100):
            if row[j] == 1:
                light[j] = 'Blue'
                lighted.append(j)
        print 'Player 2 decide to light ' + str(len(lighted)) + ' lights to blue'
        print lighted
        break
    # print light
    print 'Red: ' + str(sum(l == 'Red' for l in light))
    print 'Blue: ' + str(sum(l == 'Blue' for l in light))
    print ''


matrix = []
for i in range(100):
    matrix.append([0] * 100)
    

for i in range(100):
    for j in range(100):
        if i < j:
            matrix[i][j] = random.randint(0,17) / 17
            matrix[j][i] = matrix[i][j]

count = []
light = []
            
for i in range(100):
    count.append(sum(matrix[i]))
    light.append('None')

sort_count = sorted(range(len(count)), key=lambda k: count[k], reverse=True)

# print count
# print light
# print sort_count

# print ''

for i in range(10):
    player1(i+1)
    player2(i+1)


print light

red = sum(l == 'Red' for l in light)
blue = sum(l == 'Blue' for l in light)

print 'Red: ' + str(red)
print 'Blue: ' + str(blue)

