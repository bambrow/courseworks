========================================================================

Lab1: Game Boards and Pieces
Author: Weiqiang Li

========================================================================

common.h
	This is the header file for common enumerations and functions.

common.cpp
	This is the source file for common functions.

game_pieces.h
	This is the header file for enumerations, structs and functions of 
	game pieces.

game_pieces.cpp
	This is the source file for functions of game pieces.

game_board.h
	This is the header file for game board functions.

game_board.cpp
	This is the source file for game board functions.

Lab1.cpp
	This is the main application source file.

/////////////////////////////////////////////////////////////////////////////

This program parses the strings in a certain input file to read the information
of game board and game pieces. First it will read the dimensions of game board;
then it will continuously read all the game pieces on that board. Finally, this
program will print out the game board with all the pieces on it, and then print
the details of every game piece and its neighbors (if any, see extra credit 
section for this part).

/////////////////////////////////////////////////////////////////////////////

Errors or warnings when developing the solution: none.

/////////////////////////////////////////////////////////////////////////////

Cases that will return error code:
#ERROR#

1) no argument given or more than one arguments given

> Lab1.exe
Usage: Lab1.exe <input_file_name>
> echo %errorlevel%
1
> Lab1.exe 1.txt 2.txt
Usage: Lab1.exe <input_file_name>
> echo %errorlevel%
1


2) file does not exist
> Lab1.exe 1.txt
Cannot open file 1.txt
> echo %errorlevel%
2


3) file does not contain a line or contains no dimension
sample file 
fail.txt (empty file)

> Lab1.exe fail.txt
Failure to read a line in file fail.txt
> echo %errorlevel%
3

sample file
fail2.txt

there is no dimension in this file

> Lab1.exe fail2.txt
Failure to read a line in file fail2.txt
> echo %errorlevel%
3

The second example is because in the main function, if the
program cannot read dimensions in the file, it will continue 
reading the dimensions until there is no line.


4) file does not contain any game piece
sample file 
fail3.txt: 

8 8
no piece
no piece
still no piece

> Lab1.exe fail3.txt
Failure to read a valid game piece in file fail3.txt
> echo %errorlevel%
5

/////////////////////////////////////////////////////////////////////////////

Cases that will successfully read all files and print the board:
#SUCCESS#

1) sample file checkers.txt

8 8
black checker X 0 0
black checker X 0 2
black checker X 1 1
black checker X 2 0
black checker X 2 2
black checker X 3 1
black checker X 4 0
black checker X 4 2
black checker X 5 1
black checker X 6 0
black checker X 6 2
black checker X 7 1
red checker O 0 6
red checker O 1 5
red checker O 1 7
red checker O 2 6
red checker O 3 5
red checker O 3 7
red checker O 4 6
red checker O 5 5
red checker O 5 7
red checker O 6 6
red checker O 7 5
red checker O 7 7

> Lab1.exe checkers.txt
  O   O   O   O
O   O   O   O
  O   O   O   O


X   X   X   X
  X   X   X   X
X   X   X   X  
> echo %errorlevel%
0


2) sample file chess.txt

8 8
white pawn P 0 1
white pawn P 1 1
white pawn P 2 1
white pawn P 3 1
white pawn P 4 1
white pawn P 5 1
white pawn P 6 1
white pawn P 7 1
white rook R 0 0
white knight S 1 0
white bishop B 2 0
white queen Q 3 0
white king K 4 0
white bishop B 5 0
white knight S 6 0
white rook R 7 0
black rook r 0 7
black knight s 1 7
black bishop b 2 7
black queen q 3 7
black king k 4 7
black bishop b 5 7
black knight s 6 7
black rook r 7 7
black pawn p 0 6
black pawn p 1 6
black pawn p 2 6
black pawn p 3 6
black pawn p 4 6
black pawn p 5 6
black pawn p 6 6 
black pawn p 7 6

> Lab1.exe chess.txt
r s b q k b s r
p p p p p p p p




P P P P P P P P
R S B Q K B S R
> echo %errorlevel%
0


3) badly formed file checkers2.txt

***bad form***
8		      8
black      checker      X      0      0
black      checker      X      0      2
***bad form***
black      checker      X      1      1


black      checker      X      2      0
black      checker      X      2      2
black      checker      X      3      1


black      checker      X      4      0
black      checker      X      4      2
black      checker      X      5      1
			***bad form***
black      checker      X      6      0
black      checker      X      6      2
black      checker      X      7      1
red      checker      O      0      6
red      checker      O      1      5


red      checker      O      1      7
red      checker      O      2      6
red      checker      O      3      5

	***bad form***
red      checker      O      3      7
red      checker      O      4      6
red      checker      O      5      5
***bad form******bad form***
red      checker      O      5      7
red      checker      O      6      6
***bad form***
red      checker      O      7      5
			
red      checker      O      7      7

> Lab1.exe checkers2.txt
  O   O   O   O
O   O   O   O
  O   O   O   O


X   X   X   X
  X   X   X   X
X   X   X   X  
> echo %errorlevel%
0

4) badly formed file chess2.txt

		
8     8
		
	***bad form***
white     pawn     P     0     1
	
white     pawn     P     1     1
	
white     pawn     P     2     1
white     pawn     P     3     1
white     pawn     P     4     1
white     pawn     P     5     1
white     pawn     P     6     1
***bad form***
		
		

white     pawn     P     7     1
white     rook     R     0     0
white     knight     S     1     0



white     bishop     B     2     0
		***bad form***

white     queen     Q     3     0
white     king     K     4     0
white     bishop     B     5     0
		
white     knight     S     6     0

white     rook     R     7     0
black     rook     r     0     7
black     knight     s     1     7

***bad form***
black     bishop     b     2     7
black     queen     q     3     7
black     king     k     4     7
black     bishop     b     5     7
black     knight     s     6     7


black     rook     r     7     7
black     pawn     p     0     6
black     pawn     p     1     6
black     pawn     p     2     6
black     pawn     p     3     6


black     pawn     p     4     6

black     pawn     p     5     6
black     pawn     p     6     6     
black     pawn     p     7     6

> Lab1.exe chess2.txt
r s b q k b s r
p p p p p p p p




P P P P P P P P
R S B Q K B S R
> echo %errorlevel%
0


In all test cases, the program runs correctly.

/////////////////////////////////////////////////////////////////////////////

Extra Credit:

In this section, a new function is designed to print out all the game pieces
in details and all their neighbors.

The function will loop through all the game pieces. For each game piece, if
it contains nothing, skip; otherwise it will print the piece ifself, loop 
through all its neighbors, and print the neighbors which actually have pieces.


Test examples:

1) checkers.txt

8 8
black checker X 0 0
black checker X 0 2
black checker X 1 1
black checker X 2 0
black checker X 2 2
black checker X 3 1
black checker X 4 0
black checker X 4 2
black checker X 5 1
black checker X 6 0
black checker X 6 2
black checker X 7 1
red checker O 0 6
red checker O 1 5
red checker O 1 7
red checker O 2 6
red checker O 3 5
red checker O 3 7
red checker O 4 6
red checker O 5 5
red checker O 5 7
red checker O 6 6
red checker O 7 5
red checker O 7 7


output:

0,0 black checker: 1,1 black checker
0,2 black checker: 1,1 black checker; 1,3 black checker
0,4 black checker: 1,3 black checker; 1,5 black checker
0,6 black checker: 1,5 black checker; 1,7 black checker
1,1 black checker: 0,0 black checker; 0,2 black checker; 2,0 black checker; 2,2
black checker
1,3 black checker: 0,2 black checker; 0,4 black checker; 2,2 black checker; 2,4
black checker
1,5 black checker: 0,4 black checker; 0,6 black checker; 2,4 black checker; 2,6
black checker
1,7 black checker: 0,6 black checker; 2,6 black checker
2,0 black checker: 1,1 black checker
2,2 black checker: 1,1 black checker; 1,3 black checker
2,4 black checker: 1,3 black checker; 1,5 black checker
2,6 black checker: 1,5 black checker; 1,7 black checker
5,1 red checker: 6,0 red checker; 6,2 red checker
5,3 red checker: 6,2 red checker; 6,4 red checker
5,5 red checker: 6,4 red checker; 6,6 red checker
5,7 red checker: 6,6 red checker
6,0 red checker: 5,1 red checker; 7,1 red checker
6,2 red checker: 5,1 red checker; 5,3 red checker; 7,1 red checker; 7,3 red chec
ker
6,4 red checker: 5,3 red checker; 5,5 red checker; 7,3 red checker; 7,5 red chec
ker
6,6 red checker: 5,5 red checker; 5,7 red checker; 7,5 red checker; 7,7 red chec
ker
7,1 red checker: 6,0 red checker; 6,2 red checker
7,3 red checker: 6,2 red checker; 6,4 red checker
7,5 red checker: 6,4 red checker; 6,6 red checker
7,7 red checker: 6,6 red checker


2) chess.txt

8 8
white pawn P 0 1
white pawn P 1 1
white pawn P 2 1
white pawn P 3 1
white pawn P 4 1
white pawn P 5 1
white pawn P 6 1
white pawn P 7 1
white rook R 0 0
white knight S 1 0
white bishop B 2 0
white queen Q 3 0
white king K 4 0
white bishop B 5 0
white knight S 6 0
white rook R 7 0
black rook r 0 7
black knight s 1 7
black bishop b 2 7
black queen q 3 7
black king k 4 7
black bishop b 5 7
black knight s 6 7
black rook r 7 7
black pawn p 0 6
black pawn p 1 6
black pawn p 2 6
black pawn p 3 6
black pawn p 4 6
black pawn p 5 6
black pawn p 6 6 
black pawn p 7 6


output:

0,0 white rook: 0,1 white knight; 1,0 white pawn; 1,1 white pawn
0,1 white knight: 0,0 white rook; 0,2 white bishop; 1,0 white pawn; 1,1 white pa
wn; 1,2 white pawn
0,2 white bishop: 0,1 white knight; 0,3 white queen; 1,1 white pawn; 1,2 white p
awn; 1,3 white pawn
0,3 white queen: 0,2 white bishop; 0,4 white king; 1,2 white pawn; 1,3 white paw
n; 1,4 white pawn
0,4 white king: 0,3 white queen; 0,5 white bishop; 1,3 white pawn; 1,4 white paw
n; 1,5 white pawn
0,5 white bishop: 0,4 white king; 0,6 white knight; 1,4 white pawn; 1,5 white pa
wn; 1,6 white pawn
0,6 white knight: 0,5 white bishop; 0,7 white rook; 1,5 white pawn; 1,6 white pa
wn; 1,7 white pawn
0,7 white rook: 0,6 white knight; 1,6 white pawn; 1,7 white pawn
1,0 white pawn: 0,0 white rook; 0,1 white knight; 1,1 white pawn
1,1 white pawn: 0,0 white rook; 0,1 white knight; 0,2 white bishop; 1,0 white pa
wn; 1,2 white pawn
1,2 white pawn: 0,1 white knight; 0,2 white bishop; 0,3 white queen; 1,1 white p
awn; 1,3 white pawn
1,3 white pawn: 0,2 white bishop; 0,3 white queen; 0,4 white king; 1,2 white paw
n; 1,4 white pawn
1,4 white pawn: 0,3 white queen; 0,4 white king; 0,5 white bishop; 1,3 white paw
n; 1,5 white pawn
1,5 white pawn: 0,4 white king; 0,5 white bishop; 0,6 white knight; 1,4 white pa
wn; 1,6 white pawn
1,6 white pawn: 0,5 white bishop; 0,6 white knight; 0,7 white rook; 1,5 white pa
wn; 1,7 white pawn
1,7 white pawn: 0,6 white knight; 0,7 white rook; 1,6 white pawn
6,0 black pawn: 6,1 black pawn; 7,0 black rook; 7,1 black knight
6,1 black pawn: 6,0 black pawn; 6,2 black pawn; 7,0 black rook; 7,1 black knight
; 7,2 black bishop
6,2 black pawn: 6,1 black pawn; 6,3 black pawn; 7,1 black knight; 7,2 black bish
op; 7,3 black queen
6,3 black pawn: 6,2 black pawn; 6,4 black pawn; 7,2 black bishop; 7,3 black quee
n; 7,4 black king
6,4 black pawn: 6,3 black pawn; 6,5 black pawn; 7,3 black queen; 7,4 black king;
 7,5 black bishop
6,5 black pawn: 6,4 black pawn; 6,6 black pawn; 7,4 black king; 7,5 black bishop
; 7,6 black knight
6,6 black pawn: 6,5 black pawn; 6,7 black pawn; 7,5 black bishop; 7,6 black knig
ht; 7,7 black rook
6,7 black pawn: 6,6 black pawn; 7,6 black knight; 7,7 black rook
7,0 black rook: 6,0 black pawn; 6,1 black pawn; 7,1 black knight
7,1 black knight: 6,0 black pawn; 6,1 black pawn; 6,2 black pawn; 7,0 black rook
; 7,2 black bishop
7,2 black bishop: 6,1 black pawn; 6,2 black pawn; 6,3 black pawn; 7,1 black knig
ht; 7,3 black queen
7,3 black queen: 6,2 black pawn; 6,3 black pawn; 6,4 black pawn; 7,2 black bisho
p; 7,4 black king
7,4 black king: 6,3 black pawn; 6,4 black pawn; 6,5 black pawn; 7,3 black queen;
 7,5 black bishop
7,5 black bishop: 6,4 black pawn; 6,5 black pawn; 6,6 black pawn; 7,4 black king
; 7,6 black knight
7,6 black knight: 6,5 black pawn; 6,6 black pawn; 6,7 black pawn; 7,5 black bish
op; 7,7 black rook
7,7 black rook: 6,6 black pawn; 6,7 black pawn; 7,6 black knight


#EOF#