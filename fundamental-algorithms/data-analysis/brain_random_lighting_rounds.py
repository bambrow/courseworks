import random


def player1(i):
    global matrix
    global light
    global sort_count
    # print 'Player 1 round ' + str(i) 
    for k in sort_count:
        if light[k] != 'None':
            continue
        # lighted = []
        light[k] = 'Red'
        # lighted.append(k)
        row = matrix[k]
        for j in range(100):
            if row[j] == 1:
                light[j] = 'Red'
                # lighted.append(j)
        # print 'Player 1 decide to light ' + str(len(lighted)) + ' lights to red'
        # print lighted
        break
    # print light
    # print 'Red: ' + str(sum(l == 'Red' for l in light))
    # print 'Blue: ' + str(sum(l == 'Blue' for l in light))
    # print ''


def player2(i):
    global matrix
    global light
    global sort_count
    # print 'Player 2 round ' + str(i) 
    for k in sort_count:
        if light[k] != 'None':
            continue
        # lighted = []
        light[k] = 'Blue'
        # lighted.append(k)
        row = matrix[k]
        for j in range(100):
            if row[j] == 1:
                light[j] = 'Blue'
                # lighted.append(j)
        # print 'Player 2 decide to light ' + str(len(lighted)) + ' lights to blue'
        # print lighted
        break
    # print light
    # print 'Red: ' + str(sum(l == 'Red' for l in light))
    # print 'Blue: ' + str(sum(l == 'Blue' for l in light))
    # print ''


red_win = 0;
blue_win = 0;
matrix = []
count = []
light = []
sort_count = []
    

def play():
    
    global red_win
    global blue_win
    global matrix
    global count
    global light
    global sort_count
    
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


    # print light

    red = sum(l == 'Red' for l in light)
    blue = sum(l == 'Blue' for l in light)

    print 'Red: ' + str(red)
    print 'Blue: ' + str(blue)
    
    if red > blue:
        red_win = red_win + 1
    elif red < blue:
        blue_win = blue_win + 1
    else:
        print 'Draw!'
        play()
    


for x in range(100):
    play()

print ''
print 'Red wins: ' + str(red_win)
print 'Blue wins: ' + str(blue_win)

