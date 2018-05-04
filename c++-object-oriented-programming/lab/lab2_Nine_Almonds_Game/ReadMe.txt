========================================================================

Lab2: Nine Almonds Game
Author: Weiqiang Li

========================================================================

game.h
	This is the header file for NineAlmondsGame class, common enumerations
	and function declarations.

game.cpp
	This is the source file for some methods in NineAlmondsGame class and
	common functions.

game_pieces.h
	This is the header file for enumerations, structs and functions of 
	game pieces.

game_pieces.cpp
	This is the source file for functions of game pieces.

lab2.cpp
	This is the main application source file.

/////////////////////////////////////////////////////////////////////////////

This program will run Nine Almonds game. In this game, the player will have
a 5*5 game board and initially will have the middle 3*3 coordinates filled 
with game pieces. Every game piece is represented in the (x,y) manner.
All game pieces are identical, and they are called "almonds". 

The goal of this game is to move the almonds on the board in order to reach 
a state that 1) only one almond left on the board, and 2) the only almond must
located on the center (2,2) position. 

To form a valid move, the player must start with an almond, use it to jump over 
another almond next to it (horizontally, vertically or diagonally), and jump to
the coordinate two positions away (horizontally, vertically or diagonally). If
the player does so, the almond that is jumped over will be removed from the 
game board.

The game will repeatedly ask the player to pick an almond on the board if there
is still valid move possible, and will continue to ask the player if the player
wishes to use the current almond to make further moves if there is still valid
move starting from the current almond. If there is no valid move for the current
almond the player is dealing with, the program will automatically end the 
current run and ask the player to pick another almond.

The game will end in the following cases: 1) the player wins, the game runs
successfully; 2) the player loses, the game runs unsuccessfully; and 3) the
player quits. Each case will return a unique return code.

NOTE:
For extra credit, see extra credit section. 
The former sections are for normal implementations without extra credit.

/////////////////////////////////////////////////////////////////////////////

NOTE: the coordinates of game pieces are represented as (x,y).

For example, the following game piece will be represented as (2,3), 
because it is located x=2, y=3.

4
3     A  
2        
1        
0
X 0 1 2 3 4

/////////////////////////////////////////////////////////////////////////////

Errors or warnings when developing the solution: none.

/////////////////////////////////////////////////////////////////////////////

Cases that will return error code before starting the game:
(for auto_play and corresponsing arguments, see extra credit)
#ERROR#

1) no argument given or more than two arguments given

> Lab2.exe
Usage: Lab2.exe NineAlmonds [auto_play]
> echo %errorlevel%
1
> Lab2.exe game1 option1 option2
Usage: Lab2.exe NineAlmonds [auto_play]
> echo %errorlevel%
1


2) wrong argument(s) given
> Lab2.exe game
Usage: Lab2.exe NineAlmonds [auto_play]
> echo %errorlevel%
2


/////////////////////////////////////////////////////////////////////////////

Cases that will successfully run the game until the end:
(below is one example)
#SUCCESS#

> Lab2.exe NineAlmonds
4
3   A A A
2   A A A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: 2,2
enter the destination: 2,4

4     A
3   A   A
2   A   A
1   A A A
0
X 0 1 2 3 4

2,2 to 2,4

Continue this turn (YyNn)?y
enter the destination: 4,2

4
3   A
2   A   A A
1   A A A
0
X 0 1 2 3 4

2,2 to 2,4 to 4,2

Continue this turn (YyNn)?y
enter the destination: 2,0

4
3   A
2   A   A
1   A A
0     A
X 0 1 2 3 4

2,2 to 2,4 to 4,2 to 2,0

Continue this turn (YyNn)?y
enter the destination: 0,2

4
3   A
2 A A   A
1     A
0
X 0 1 2 3 4

2,2 to 2,4 to 4,2 to 2,0 to 0,2

Continue this turn (YyNn)?n

4
3   A
2 A A   A
1     A
0
X 0 1 2 3 4

enter the piece you want to move: 1,3
enter the destination: 1,1

4
3
2 A     A
1   A A
0
X 0 1 2 3 4

1,3 to 1,1

Continue this turn (YyNn)?n

4
3
2 A     A
1   A A
0
X 0 1 2 3 4

enter the piece you want to move: 3,2
enter the destination: 1,0

4
3
2 A
1   A
0   A
X 0 1 2 3 4

3,2 to 1,0

Continue this turn (YyNn)?y
enter the destination: 1,2

4
3
2 A A
1
0
X 0 1 2 3 4

3,2 to 1,0 to 1,2

this piece cannot be moved from its new position. this turn is over.

4
3
2 A A
1
0
X 0 1 2 3 4

enter the piece you want to move: 0,2
enter the destination: 2,2

4
3
2     A
1
0
X 0 1 2 3 4

0,2 to 2,2

this piece cannot be moved from its new position. this turn is over.

4
3
2     A
1
0
X 0 1 2 3 4

success!
total turns: 4

> echo %errorlevel%
0


/////////////////////////////////////////////////////////////////////////////

Cases that will unsuccessfully run the game until no valid move avaliable:
(below is one example)
#ERROR#

> Lab2.exe NineAlmonds
4
3   A A A
2   A A A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: 1,2
enter the destination: 1,4

4   A
3     A A
2     A A
1   A A A
0
X 0 1 2 3 4

1,2 to 1,4

this piece cannot be moved from its new position. this turn is over.

4   A
3     A A
2     A A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: 2,2
enter the destination: 2,4

4   A A
3       A
2       A
1   A A A
0
X 0 1 2 3 4

2,2 to 2,4

Continue this turn (YyNn)?n

4   A A
3       A
2       A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: 3,2
enter the destination: 3,4

4   A A A
3
2
1   A A A
0
X 0 1 2 3 4

3,2 to 3,4

this piece cannot be moved from its new position. this turn is over.

4   A A A
3
2
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: 2,4
enter the destination: 0,4

4 A     A
3
2
1   A A A
0
X 0 1 2 3 4

2,4 to 0,4

this piece cannot be moved from its new position. this turn is over.

4 A     A
3
2
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: 2,1
enter the destination: 0,1

4 A     A
3
2
1 A     A
0
X 0 1 2 3 4

2,1 to 0,1

this piece cannot be moved from its new position. this turn is over.

4 A     A
3
2
1 A     A
0
X 0 1 2 3 4

no valid move! game over.
total turns: 5

> echo %errorlevel%
4


The game will also behave well in badly formed inputs on the command line
and from the user.

/////////////////////////////////////////////////////////////////////////////

Cases that user quits:
(below is one example)
#ERROR#

> Lab2.exe NineAlmonds
4
3   A A A
2   A A A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: quit
user quit!
total turns: 0

> echo %errorlevel%
3


/////////////////////////////////////////////////////////////////////////////

Extra Credit: Auto Play

#NOTE#
Please wait for several seconds (or even minutes) between automatic solutions.
Please press "enter" to get the next solution until the end.

1. command line input

1) Cases that will not start the game with extra option:

> Lab2.exe NineAlmonds auto
Usage: Lab2.exe NineAlmonds [auto_play]
> echo %errorlevel%
2


> Lab2.exe game auto_play
Usage: Lab2.exe NineAlmonds [auto_play]
> echo %errorlevel%
2


2) Cases that will start the game with extra option:

> Lab2.exe NineAlmonds auto_play


2. test run output (partial)
below is the partial output for the auto_play.

> Lab2.exe NineAlmonds auto_play
auto play begins; please wait for several seconds (or minutes) between solutions
.

4
3
2     A
1
0
X 0 1 2 3 4

2,1 to 0,3
2,2 to 4,2
4,2 to 2,0
2,0 to 0,2
0,2 to 2,4
3,3 to 1,3
0,3 to 2,3
2,4 to 2,2


press enter to see next solution (if any).

4
3
2     A
1
0
X 0 1 2 3 4

2,1 to 0,3
2,2 to 2,4
0,3 to 2,3
3,3 to 1,3
2,4 to 0,2
0,2 to 2,0
2,0 to 4,2
4,2 to 2,2


press enter to see next solution (if any).

4
3
2     A
1
0
X 0 1 2 3 4

2,1 to 4,3
2,2 to 0,2
0,2 to 2,0
2,0 to 4,2
4,2 to 2,4
1,3 to 3,3
4,3 to 2,3
2,4 to 2,2


press enter to see next solution (if any).

4
3
2     A
1
0
X 0 1 2 3 4

2,1 to 4,3
2,2 to 2,4
4,3 to 2,3
1,3 to 3,3
2,4 to 4,2
4,2 to 2,0
2,0 to 0,2
0,2 to 2,2


press enter to see next solution (if any).

4
3
2     A
1
0
X 0 1 2 3 4

1,2 to 3,0
2,2 to 4,2
3,0 to 3,2
3,3 to 3,1
4,2 to 2,0
2,0 to 0,2
0,2 to 2,4
2,4 to 2,2


press enter to see next solution (if any).

4
3
2     A
1
0
X 0 1 2 3 4

1,2 to 3,0
2,2 to 2,4
2,4 to 0,2
0,2 to 2,0
2,0 to 4,2
3,3 to 3,1
3,0 to 3,2
4,2 to 2,2


press enter to see next solution (if any).

(...)
(not finished, just partial outputs.)

#EOF#