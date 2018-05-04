========================================================================

Lab3: Multiple Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

========================================================================

game.h
	this is the header file for common enumations and helper functions.

game.cpp
	this is the source file for common helper functions.

game_pieces.h
	This is the header file for enumerations, structs and functions of 
	game pieces.

game_pieces.cpp
	This is the source file for functions of game pieces.

game_base.h
	This is the header file contains some protected member variables 
	and public function declarations that are common to multiple games.

game_base.cpp
	This is the source file for some methods in both games.

nine_almonds.h
	This is the header file for NineAlmondsGame class, common enumerations
	and function declarations.

nine_almonds.cpp
	This is the source file for some methods in NineAlmondsGame class and
	common functions.

magic_square.h
	This is the header file for some methods in MagicSquare class and function 
	declarations.

magic_square.cpp
	This is the source file for some methods in MagicSquare class and
	common functions.

lab3.cpp
	This is the main application source file.

/////////////////////////////////////////////////////////////////////////////

In this lab, we refactored our game class from the previous lab and added a new
game in the same program. Therefore, this program runs two games which the first 
game is from our previous lab, Nine Almonds Game and another is the new one, 
Magic Square. 


MAGIC SQUARE:

When the player correctly enters "lab3.exe MagicSquare" in command line, the
program will run Magic Square. 

In this game, the player will start with a 3*3 empty game(or n*n, this will be 
mentioned in extra credit section) board and have 9 game pieces available to play.
The 9 pieces in the game has a name that is the same as its textual representation 
on the terminal screen when the board is printed and corresponds to a unique integer 
value between 1 and 9.

The goal of this game is to have the sum of the values of the pieces along every 
diagonal, row, or column all be the same result, which here is 15 for the basic 
version of the game.

To form a valid move, the player must first start with an integer number that is from
available pieces and enter the coordinate where the player wants to put it on. The 
coordinate entered must be empty(contains no piece/ integer). For every valid move,
the game will count the turn number once.

The game will repeatedly ask the player to put an interger(game piece) on the board
if there is still valid space on the board.If every square contains a piece and the 
sum of the integer values of the pieces along any diagonal, row, or column is different 
than the sum of the pieces along any other diagonal, row, or column, the game will 
consider the player fails to run successfully and end the game.

The game will end in following cases: 1) the play wins, the game runs successfully;
2) the play loses(stalemate), the game runs unsuccssfully; and 3) the play quits.
Each case will return a unique return code.



NINE ALMONDS GAME:

When the player correctly enters "lab3.exe Nine Almonds Game" in command line,
the program will run Nine Almonds game. 

In this game, the player will have a 5*5 game board and initially will have the 
middle 3*3 coordinates filled with game pieces. Every game piece is represented 
in the (x,y) manner. All game pieces are identical, and they are called "almonds". 

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
successfully; 2) the player loses(stalemate), the game runs unsuccessfully; and 
3) the player quits. Each case will return a unique return code.




NOTE:
For extra credit, see extra credit section(at the end of this file). 
The former sections are for normal implementations without extra credit.

/////////////////////////////////////////////////////////////////////////////

Errors or warnings when developing the solution: none.

/////////////////////////////////////////////////////////////////////////////

Cases that will return error code before starting the game:
(as indicated, those issues will all return a nullptr and no game was constructed.)

1) no argument given or more than two arguments given

> Lab3.exe
Usage: Lab3.exe [game_name] [extra_options]
> echo %errorlevel%
3

> Lab3.exe MagicSquare 5 5 5 5
Usage: Lab3.exe MagicSquare [extra_options]
> echo %errorlevel%
3


2) wrong argument(s) given
> Lab3.exe game
Usage: Lab3.exe [game_name] [extra_options]
> echo %errorlevel%
3

/////////////////////////////////////////////////////////////////////////////

MAGIC SQUARE:


Cases that will successfully run the game until the end:

> Lab3.exe MagicSquare
2
1
0
X 0 1 2

Available pieces: 1 2 3 4 5 6 7 8 9

enter the number you want to add: 8
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 0

2
1
0 8
X 0 1 2

Available pieces: 1 2 3 4 5 6 7 9

enter the number you want to add: 1
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 0

2
1
0 8 1
X 0 1 2

Available pieces: 2 3 4 5 6 7 9

enter the number you want to add: 6
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 0

2
1
0 8 1 6
X 0 1 2

Available pieces: 2 3 4 5 7 9

enter the number you want to add: 3
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 1

2
1 3
0 8 1 6
X 0 1 2

Available pieces: 2 4 5 7 9

enter the number you want to add: 5
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 1

2
1 3 5
0 8 1 6
X 0 1 2

Available pieces: 2 4 7 9

enter the number you want to add: 7
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 1

2
1 3 5 7
0 8 1 6
X 0 1 2

Available pieces: 2 4 9

enter the number you want to add: 4
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 2

2 4
1 3 5 7
0 8 1 6
X 0 1 2

Available pieces: 2 9

enter the number you want to add: 9
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 2

2 4 9
1 3 5 7
0 8 1 6
X 0 1 2

Available pieces: 2

enter the number you want to add: 2
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 2

2 4 9 2
1 3 5 7
0 8 1 6
X 0 1 2

Available pieces:

success!
total turns: 9

/////////////////////////////////////////////////////////////////////////////

Cases that will unsuccessfully run the game until no valid move avaliable:

> Lab3.exe MagicSquare
2
1
0
X 0 1 2

Available pieces: 1 2 3 4 5 6 7 8 9

enter the number you want to add: 1
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 0

2
1
0 1
X 0 1 2

Available pieces: 2 3 4 5 6 7 8 9

enter the number you want to add: 2
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 1

2
1 2
0 1
X 0 1 2

Available pieces: 3 4 5 6 7 8 9

enter the number you want to add: 3
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 2

2 3
1 2
0 1
X 0 1 2

Available pieces: 4 5 6 7 8 9

enter the number you want to add: 4
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 0

2 3
1 2
0 1 4
X 0 1 2

Available pieces: 5 6 7 8 9

enter the number you want to add: 5
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 1

2 3
1 2 5
0 1 4
X 0 1 2

Available pieces: 6 7 8 9

enter the number you want to add: 6
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 2

2 3 6
1 2 5
0 1 4
X 0 1 2

Available pieces: 7 8 9

enter the number you want to add: 7
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 0

2 3 6
1 2 5
0 1 4 7
X 0 1 2

Available pieces: 8 9

enter the number you want to add: 8
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 1

2 3 6
1 2 5 8
0 1 4 7
X 0 1 2

Available pieces: 9

enter the number you want to add: 9
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 2

2 3 6 9
1 2 5 8
0 1 4 7
X 0 1 2

Available pieces:

no valid move! game over.
total turns: 9


(EXTRA CREDIT SECTION PLEASE SEE THE END OF THIS FILE)

The game will also behave well in badly formed inputs on the command line
and from the user.

/////////////////////////////////////////////////////////////////////////////

Cases that user quits:

> Lab3.exe MagicSquare
2
1
0
X 0 1 2

Available pieces: 1 2 3 4 5 6 7 8 9

enter the number you want to add: 2
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: quit

(the game was exited)

/////////////////////////////////////////////////////////////////////////////

NINE ALMONDS GAME:


NOTE: the coordinates of game pieces are represented as (x,y).

For example, the following game piece will be represented as (2,3), 
because it is located x=2, y=3.

4
3     A  
2        
1        
0
X 0 1 2 3 4

Cases that will successfully run the game until the end:

> Lab3.exe NineAlmonds
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


/////////////////////////////////////////////////////////////////////////////

Cases that will unsuccessfully run the game until no valid move avaliable:

> Lab3.exe NineAlmonds
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


The game will also behave well in badly formed inputs on the command line
and from the user.

/////////////////////////////////////////////////////////////////////////////

Cases that user quits:

> Lab3.exe NineAlmonds
4
3   A A A
2   A A A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: quit
user quit!
total turns: 0

/////////////////////////////////////////////////////////////////////////////

[Extra Credit]

By adding this part, player is allowed to play with arbitrary sized board (greater or
equal to 1) and with an arbitrary value of the lowest valued piece in the Magic Square.

So here two arguments can be specified here. If one additional parameter is specified, 
it should be interpreted as the number of squares on a side of the board and the pieces 
should be numbered starting with 1.

If two additional arguments are specified, the first should be interpreted as the number 
of squares on a side of the board and the second as the lowest piece value.


### Case 1:
> Lab3.exe MagicSquare 1
0
X 0

Available pieces: 1

enter the number you want to add: 1
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 0

0 1
X 0

Available pieces:

success!
total turns: 1


### Case 2:
> Lab3.exe MagicSquare 3 -9
2
1
0
X 0 1 2

Available pieces: -9 -8 -7 -6 -5 -4 -3 -2 -1

enter the number you want to add: -8
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 0

2
1
0 -8
X  0  1  2

Available pieces: -9 -7 -6 -5 -4 -3 -2 -1

enter the number you want to add: -1
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 0

2
1
0 -8 -1
X  0  1  2

Available pieces: -9 -7 -6 -5 -4 -3 -2

enter the number you want to add: -6
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 0

2
1
0 -8 -1 -6
X  0  1  2

Available pieces: -9 -7 -5 -4 -3 -2

enter the number you want to add: -3
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 1

2
1 -3
0 -8 -1 -6
X  0  1  2

Available pieces: -9 -7 -5 -4 -2

enter the number you want to add: -5
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 1

2
1 -3 -5
0 -8 -1 -6
X  0  1  2

Available pieces: -9 -7 -4 -2

enter the number you want to add: -7
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 1

2
1 -3 -5 -7
0 -8 -1 -6
X  0  1  2

Available pieces: -9 -4 -2

enter the number you want to add: -4
enter the x coordinate you want to move to: 0
enter the y coordinate you want to move to: 2

2 -4
1 -3 -5 -7
0 -8 -1 -6
X  0  1  2

Available pieces: -9 -2

enter the number you want to add: -9
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 2

2 -4 -9
1 -3 -5 -7
0 -8 -1 -6
X  0  1  2

Available pieces: -2

enter the number you want to add: -2
enter the x coordinate you want to move to: 2
enter the y coordinate you want to move to: 2

2 -4 -9 -2
1 -3 -5 -7
0 -8 -1 -6
X  0  1  2

Available pieces:

success!
total turns: 9


