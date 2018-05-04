========================================================================

Lab4: Single-Player and Multi-Player Board Games
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

reversi_game.h
	This is the header file for some method in ReversiGame class and function
	declarations.

reversi_game.cpp
	This is the source file for some methods in ReversiGame and common functions.

checkers.h
	This is the header file for some method in Checkers class and function
	declarations
	
checkers.cpp
	This is the source file for some methods in Checkers and common functions.

lab4.cpp
	This is the main application source file.

/////////////////////////////////////////////////////////////////////////////

In this lab, we refactored our game class from the previous lab, added a new
game in the same program, and add save & restore function for these 3 games. 

Therefore, this program runs 3 games which the first 2 games are from our 
previous lab, Nine Almonds Game and Magic Square and the new game added is 
a multi-player game, Reversi. 

EXTRA CREDIT part CHECKERS is at the bottom of this file.

/////////////////////////////////////////////////////////////////////////////

REVERSI GAME:

Since this game is a multi-player game, when the player correctly enters 
“lab4.exe Reversi name1 name2” in command line, the program will run this 
multi-player Reversi game. If wrong command is entered, there will be a usage 
message offering the correct example of command: lab4.exe Reversi Alice Bob

Reversi is a game played by 2 players, starting with a 8*8 checkerboard that 
has 2 black pieces and 2 white pieces in the center(the example is shown below). 
One player should play with black pieces(denoted by “X”) and another play with 
white pieces(denoted by “O”), and this is specified with player’s name when 
entering the running command the game. The one with black pieces will move 
first. The checkerboard has squares numbered from 0,0(lower left corner) to 
7,7(upper right corner), and every game piece is represented in the (x,y) manner. 
Each piece is reversible; it has 2 sides: one is black and the other is white. 
The piece will turn into another color only when the piece is bounded by 
opponents’ piece.

The goal of this game is to have the majority of pieces turned to display one 
color(either black or white) when the last playable empty square is filled.

To form a valid move, the player should first place a new piece into a valid 
empty square. And an empty square is valid if contiguously from that empty 
square in any vertical, horizontal, or diagonal direction (with no other 
intervening empty squares) there is (are) one or more pieces of the other 
player's color followed immediately (with no intervening empty squares) by a 
piece of the color played by the player whose turn it is. Any opponent’s pieces 
that are in a straight line and bounded by the pieces just placed will be turned 
over to the current player's color.

During a play, the game will repeatedly ask the player to place the game piece 
on the board if there is still valid space for the player’s color on the board. 
If all squares are filled, only one color appears on the board, or no more valid 
move, the game will then consider the player fails or successes and end the game.

The game will ending with following cases: 1) When all the pieces on the board 
are of the same color, then the player playing with that color wins. 2) All 
squares are filled and there are more pieces of one color than another color 
on the board; then the player with more pieces on the board wins. 3) All squares 
are filled and each color has same amount of pieces on the board. 4) When there 
are no remaining legal moves for either player, then the player with more pieces 
on the board wins. 5)When there are no remaining legal moves for either player, 
and each color has same amount of pieces on the board.

Each case will return a unique return code.

*****************************************************************************

MAGIC SQUARE:

When the player correctly enters "lab4.exe MagicSquare" in command line, the
program will run Magic Square. If wrong command is entered, there will be a 
usage message offering the correct example of command: lab4.exe MagicSquare

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

The game will repeatedly ask the player to put an integer(game piece) on the board
if there is still valid space on the board.If every square contains a piece and the 
sum of the integer values of the pieces along any diagonal, row, or column is different 
than the sum of the pieces along any other diagonal, row, or column, the game will 
consider the player fails to run successfully and end the game.

The game will end in following cases: 1) the play wins, the game runs successfully;
2) the play loses(stalemate), the game runs unsuccessfully; and 3) the play quits.
Each case will return a unique return code.


*****************************************************************************

NINE ALMONDS GAME:

When the player correctly enters "lab4.exe Nine Almonds Game" in command line,
the program will run Nine Almonds game. If wrong command is entered, there 
will be a usage message offering the correct example of command: lab4.exe NineAlmonds
.

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



/////////////////////////////////////////////////////////////////////////////

NOTE:
For extra credit, see extra credit section(at the end of this file). 
The former sections are for normal implementations without extra credit.

/////////////////////////////////////////////////////////////////////////////

Copy Control Design Decisions:

GameBase:

The reason for why we did not use 1 - 4 constructors are the same. We only have 
one game to run at a time. If we want to change the current game condition, such 
as changing the display of several game_pieces, we just use set_display() to change
the display. So we think there is no need to copy one gameb class to a new game class,
and then change the conditions then. In other words, all we need to do is to change 
the member variables. 

1. copy constructor: We didn't use any copy constructor. We set it as deleted.
 
2. copy assignment: We didn't use any copy assignment. We set it as deleted.
 
3. move constructor: We didn't use any move constructor. We set it as deleted. 

4. move assignment: We didn't use any move assignment. We set it as deleted.
 
5. destructor: We set the destructor as default. Because we didn't have any 
member variables that do not have a default destructor such as a dynamic pointer.
So That's why we set the destrucotr as default in this class. And because this 
class is the base class, so we set it as virtual. The reason why set it as virtual
is to let the objects destructed in a order (first derived class and then the base class.)

MagicSquare:

The reason for why we did not use 1 - 4 constructors are the same. We only have 
one game to run at a time. If we want to change the current game condition, such 
as changing the display of several game_pieces, we just use set_display() to change
the display. So we think there is no need to copy one gameb class to a new game class,
and then change the conditions then. In other words, all we need to do is to change 
the member variables. 

1. copy constructor: We didn't use any copy constructor. We set it as deleted.
 
2. copy assignment: We didn't use any copy assignment. We set it as deleted.
 
3. move constructor: We didn't use any move constructor. We set it as deleted. 

4. move assignment: We didn't use any move assignment. We set it as deleted.
 
5. destructor: We set the destructor as default. Because we didn't have any 
member variables that do not have a default destructor such as a dynamic pointer.

NineAlmondsGame:

The reason for why we did not use 1 - 4 constructors are the same. We only have 
one game to run at a time. If we want to change the current game condition, such 
as changing the display of several game_pieces, we just use set_display() to change
the display. So we think there is no need to copy one gameb class to a new game class,
and then change the conditions then. In other words, all we need to do is to change 
the member variables. 

1. copy constructor: We didn't use any copy constructor. We set it as deleted.
 
2. copy assignment: We didn't use any copy assignment. We set it as deleted.
 
3. move constructor: We didn't use any move constructor. We set it as deleted. 

4. move assignment: We didn't use any move assignment. We set it as deleted.
 
5. destructor: We set the destructor as default. Because we didn't have any 
member variables that do not have a default destructor such as a dynamic pointer.

ReversiGame:

The reason for why we did not use 1 - 4 constructors are the same. We only have 
one game to run at a time. If we want to change the current game condition, such 
as changing the display of several game_pieces, we just use set_display() to change
the display. So we think there is no need to copy one gameb class to a new game class,
and then change the conditions then. In other words, all we need to do is to change 
the member variables. 

1. copy constructor: We didn't use any copy constructor. We set it as deleted.
 
2. copy assignment: We didn't use any copy assignment. We set it as deleted.
 
3. move constructor: We didn't use any move constructor. We set it as deleted. 

4. move assignment: We didn't use any move assignment. We set it as deleted.
 
5. destructor: We set the destructor as default. Because we didn't have any 
member variables that do not have a default destructor such as a dynamic pointer.

Checkers:

The reason for why we did not use 1 - 4 constructors are the same. We only have 
one game to run at a time. If we want to change the current game condition, such 
as changing the display of several game_pieces, we just use set_display() to change
the display. So we think there is no need to copy one gameb class to a new game class,
and then change the conditions then. In other words, all we need to do is to change 
the member variables. 

1. copy constructor: We didn't use any copy constructor. We set it as deleted.
 
2. copy assignment: We didn't use any copy assignment. We set it as deleted.
 
3. move constructor: We didn't use any move constructor. We set it as deleted. 

4. move assignment: We didn't use any move assignment. We set it as deleted.
 
5. destructor: We set the destructor as default. Because we didn't have any 
member variables that do not have a default destructor such as a dynamic pointer.

/////////////////////////////////////////////////////////////////////////////

Game piece format:

Reversi:

X black piece
O white piece


*****************************************************************************

MagicSquare:

The piece is represented as the number it holds.


*****************************************************************************

Nine Almonds:

X almond

/////////////////////////////////////////////////////////////////////////////

NOTE: the coordinates of game pieces are represented as (x,y).

Below is an example in Nine Almonds game, the following game piece will be 
represented as (2,3), because it is located x=2, y=3.

4
3     A  
2        
1        
0
X 0 1 2 3 4


*****************************************************************************

Below is the initial condition of Reversi Game:

7
6
5
4       O X
3       X O
2
1
0
X 0 1 2 3 4 5 6 7


*****************************************************************************

Below is an example in Magic Square with 7 is placed in (0,0).

2
1
0 7
X 0 1 2

/////////////////////////////////////////////////////////////////////////////

Errors or warnings when developing the solution: none.

/////////////////////////////////////////////////////////////////////////////

How to run the game(s) correctly:

Lab4.exe NineAlmonds
Lab4.exe MagicSquare
Lab4.exe Reversi [black_player] [white_player]

/////////////////////////////////////////////////////////////////////////////

The TEST_CASE folder includes multiple test cases avaliable for each game.

To use the test cases, change the file name to the name of the game, and
put them in the folder where lab4.exe is.

/////////////////////////////////////////////////////////////////////////////

Game saving format: Reversi

Example:

ReversiGame
Alice Bob
black
24 X
25 O
26 O
27 O
28 O
29 O
30 O

Explanation:
The first line is the name of the game. 

The second lines saves the name of black player and white player. 

The third line tells the color of the current turn. 

The rest of lines saves all the pieces on the board with the vector index and 
the denotation (black is X and white is O). Space is not saved here.

/////////////////////////////////////////////////////////////////////////////

Game saving format: Magic Square

Example:

MagicSquare
3
0 2
1 9
2 4
3 7
4 space
5 3
6 6
7 1
8 8
5

Explanation:
The first line is the name of the game. 

The second line is the dimension of the game board. 

And the next couple of lines show everything (no matter if it is a piece of a 
space) currently on the board. 

So here through line 0 to line 8, following a number represented the game piece, 
or following by a space. 

And the remaining lines show the avaliable pieces that is not placed on the board.

/////////////////////////////////////////////////////////////////////////////

Game saving format: Nine Almonds

Example:

NineAlmondsGame
10 11 
4

Explanation:
The first line is the name of the game. 

The second line shows the vector index of all almonds. 

So here there are 2 almonds and one is at [10] (which is 2,0 on the board) 
and another one is at [11] (which is 2,1).

The last line tells the current turns. So here it is the fourth turn.

/////////////////////////////////////////////////////////////////////////////

If the player choose not to save and restore, then outputs:
NO DATA

/////////////////////////////////////////////////////////////////////////////

TEST CASE FOR NINE ALMONDS:

> Lab4.exe NineAlmonds
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

Continue this turn (YyNn)?n

4
3   A
2   A   A A
1   A A A
0
X 0 1 2 3 4

enter the piece you want to move: quit
Do you want to save this game? (YyNn)y
user quit!
total turns: 1

/////////////////////////////////////////////////////////////////////////////

TEST CASE FOR MAGIC SQUARE:

> Lab4.exe MagicSquare
2
1
0
X 0 1 2

Available pieces: 1 2 3 4 5 6 7 8 9

enter the number you want to add: 4
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 2

2   4
1
0
X 0 1 2

Available pieces: 1 2 3 5 6 7 8 9

enter the number you want to add: 5
enter the x coordinate you want to move to: 1
enter the y coordinate you want to move to: 1

2   4
1   5
0
X 0 1 2

Available pieces: 1 2 3 6 7 8 9

enter the number you want to add: quit
Do you want to save this game? (YyNn)y

/////////////////////////////////////////////////////////////////////////////

TEST CASE FOR REVERSI:

> Lab4.exe Reversi Alice Bob
7
6
5
4       O X
3       X O
2
1
0
X 0 1 2 3 4 5 6 7


now is Alice's turn.
enter the coordinate (x,y) you want to put your black piece: 4,2


black piece: 4; white piece: 1

7
6
5
4       O X
3       X X
2         X
1
0
X 0 1 2 3 4 5 6 7


now is Bob's turn.
enter the coordinate (x,y) you want to put your white piece: 5,2


black piece: 3; white piece: 3

7
6
5
4       O X
3       X O
2         X O
1
0
X 0 1 2 3 4 5 6 7


now is Alice's turn.
enter the coordinate (x,y) you want to put your black piece: 5,3


black piece: 5; white piece: 2

7
6
5
4       O X
3       X X X
2         X O
1
0
X 0 1 2 3 4 5 6 7


now is Bob's turn.
enter the coordinate (x,y) you want to put your white piece: 5,4


black piece: 3; white piece: 5

7
6
5
4       O O O
3       X X O
2         X O
1
0
X 0 1 2 3 4 5 6 7


now is Alice's turn.
enter the coordinate (x,y) you want to put your black piece: quit
Do you want to save this game? (YyNn)y

user quit!

/////////////////////////////////////////////////////////////////////////////

[Extra Credit] Checkers

Introduction to Checkers game:

Since this game is a multi-player game, when the player correctly enters 
“lab4.exe Checkers name1 name2” in command line, the program will run this 
multi-player checker game. If wrong command is entered, there will be a usage 
message offering the correct example of command: lab4.exe Checkers Alice Bob.

Checkers is a game played by 2 players, and these 2 players will be assigned 
to either the top side or the bottom side of a 8*8 checkerboard. The game will 
start with 12 pawn pieces(either all black pawns or all white pawns) on each 
player’s side. The pieces are placed on the dark squares of the 3 rows closet 
to player’s side. One player should play with black pieces and another play 
with white pieces(denoted by “O”, and this is specified with player’s name 
when entering the running command the game. The one with black pieces will 
move first. The checkerboard has squares numbered from 0,0 (lower left corner) 
to 7,7 (upper right corner), and every game piece is represented in the (x,y) 
manner. In this game every piece has two sides; one side is initially set to 
be Pawn, which players will start the game using this side, and the other side 
is King. Pawn will turn and become King only when the Pawn reaches the row that 
is closet to opponent. A piece is removed for the board when it is jumped over 
by opponent’s piece.

The goal of this game is to have one player capturing all of the opponent’s 
piece or leaving the opponent with no legal move.

The player could have two actions. One is jump, and another is simple move. 
To make a simple move, the player just simply place a piece one square diagonally 
to an adjacent empty dark square. Pawn pieces can move only diagonally forward;
kings can move in any diagonal direction.

To form a valid move in jump, the player should validly jump over a piece of 
opponent and place the piece into an empty dark square.A valid jump consists 
of moving a piece from a square diagonally adjacent to an opponent's piece, to 
an empty square immediately beyond it in the same direction. (Thus jumping over 
the opponent's piece.) Pawn can jump diagonally forward only; kings can jump in 
any diagonal direction. 

Any opponent’s pieces that are jumped over by the pieces just placed will be removed.
Any piece can jump a king.  

During a play, the game will repeatedly ask the player to place the game piece 
on the board if there is still valid space and pieces for the player’s color 
on the board.If all pieces left are one same color, or no more valid move, the 
game will then consider the player fails or successes and end the game.

The game will ending with following cases: 1) When all the pieces on the board 
are of the same color, then the player playing with that color wins. 2) When there 
are no remaining legal moves for either player, then the player who still could 
make next move wins.
Each case will return a unique return code.


How to run Checkers game correctly: Lab4.exe Checkers [black_player] [white_player]


Game piece format in Checkers:

X black pawn
O white pawn
B black king
W white king


Game saving format: Checkers

Example:

Checkers
Alice Bob
white
27 W
36 B

Explanation:
The first line is the name of the game. 

The second lines saves the name of black player and white player. 

The third line tells the color of the current turn. 

The rest of lines saves all the pieces on the board with the 
vector index and the denotation. Space is not saved here.

*****************************************************************************

TEST CASE:

> Lab4.exe Checkers Alice Bob

7   X   X   X   X
6 X   X   X   X
5   X   X   X   X
4
3
2 O   O   O   O
1   O   O   O   O
0 O   O   O   O
X 0 1 2 3 4 5 6 7


now is Bob's turn. (white)
enter the piece you want to move: 2,2
enter the destination: 3,3

7   X   X   X   X
6 X   X   X   X
5   X   X   X   X
4
3       O
2 O       O   O
1   O   O   O   O
0 O   O   O   O
X 0 1 2 3 4 5 6 7


now is Alice's turn. (black)
enter the piece you want to move: 1,5
enter the destination: 2,4

7   X   X   X   X
6 X   X   X   X
5       X   X   X
4     X
3       O
2 O       O   O
1   O   O   O   O
0 O   O   O   O
X 0 1 2 3 4 5 6 7


now is Bob's turn. (white)
enter the piece you want to move: 3,3
enter the destination: 1,5


7   X   X   X   X
6 X   X   X   X
5   O   X   X   X
4
3
2 O       O   O
1   O   O   O   O
0 O   O   O   O
X 0 1 2 3 4 5 6 7

3,3 to 1,5

this piece cannot be jumped from its new position. this turn is over.

7   X   X   X   X
6 X   X   X   X
5   O   X   X   X
4
3
2 O       O   O
1   O   O   O   O
0 O   O   O   O
X 0 1 2 3 4 5 6 7


now is Alice's turn. (black)
enter the piece you want to move: quit
Do you want to save this game? (YyNn)y
user quit!
