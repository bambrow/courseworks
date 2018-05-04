/***********************************************************

Lab3: Multiple Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#include "stdafx.h"
#include "game.h"
#include "game_base.h"
#include "nine_almonds.h"
#include "magic_square.h"
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <iterator>
#include <sstream>
#include <iomanip>
#include <map>
#include <iterator>

using namespace std;

const unsigned int M_LEN = 1; // initial length for MagicSquare

/***********************************************************

Constructor of MagicSquare object. A d*d game board is
created and there are d*d pieces available.

***********************************************************/
MagicSquare::MagicSquare(const unsigned int& d, const int& s) : GameBase(d, d, M_LEN) {
	int dim = d;
	sum = dim * (2 * s + dim * dim - 1) / 2;
	for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
		for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
			game_pieces.push_back(space);
			available.insert(make_pair(get_index(pos_x, pos_y) + s,
				game_piece(no_color, convert(get_index(pos_x, pos_y) + s), convert(get_index(pos_x, pos_y) + s))));
		}
	}
}


/***********************************************************

Print the board.

***********************************************************/
void MagicSquare::print() {
	cout << *this << endl;
}

/***********************************************************

Detect if the game is successfully over.

Return:
true if the board contains every piece and the sum of the values 
of the pieces along every diagonal, row, or column all be the 
same result.
false otherwise.

***********************************************************/
bool MagicSquare::done() {

	for (game_piece v : game_pieces) {
		if (v == space) {
			return false;
		}
	}

	for (unsigned int i = 0; i < dim_x; ++i) {
		if (sumVertival(i) != sum) {
			return false;
		}
	}

	for (unsigned int i = 0; i < dim_y; ++i)
	{
		if (sumHorizontal(i) != sum) {
			return false;
		}
	}

	if (sumDiagonal(true) != sum) {
		return false;
	}

	if (sumDiagonal(false) != sum) {
		return false;
	}

	return true;
}

/***********************************************************

Detect if the game is over, but not successfully over.

Return:
true if the game is not successfully done, and there is no
empty space on the board.
false otherwise.

***********************************************************/
bool MagicSquare::stalemate() {

	for (game_piece v : game_pieces) {
		if (v == space) {
			return false;
		}
	}

	for (unsigned int i = 0; i < dim_x; ++i) {
		if (sumVertival(i) != sum) {
			return true;
		}
	}

	for (unsigned int i = 0; i < dim_y; ++i)
	{
		if (sumHorizontal(i) != sum) {
			return true;
		}
	}

	if (sumDiagonal(true) != sum) {
		return true;
	}

	if (sumDiagonal(false) != sum) {
		return true;
	}

	return false;
}


/***********************************************************

Calculate the sum of the values of the piece on the board 
horizontally.

Return:
the sum value.

***********************************************************/
int MagicSquare::sumHorizontal(const unsigned int &line_y) {
	int sumH = 0;
	for (unsigned int j = 0; j < dim_x; j++)
	{
		sumH += convert(game_pieces[get_index(j, line_y)].get_display());
	}
	return sumH;
}

/***********************************************************

Calculate the sum of the values of the piece on the board 
vertically.

Return:
the sum value.

***********************************************************/
int MagicSquare::sumVertival(const unsigned int &line_x) {
	int sumV = 0;
	for (unsigned int j = 0; j < dim_y; j++)
	{
		sumV += convert(game_pieces[get_index(line_x, j)].get_display());
	}
	return sumV;
}

/***********************************************************

Calculate the sum of the values of the piece on the board 
diagonally.

Return:
the sum value.

***********************************************************/
int MagicSquare::sumDiagonal(const bool& whichDiagonal) {
	// from bottom left to top right
	int sumD = 0;
	if (whichDiagonal) {
		for (unsigned int i = 0; i < dim_y; ++i)
		{
			unsigned int pos_x = i;
			sumD += convert(game_pieces[get_index(pos_x, i)].get_display());
		}
		return sumD;
	}
	// from bottom right to top left
	else
	{
		for (unsigned int i = 0; i < dim_y; ++i)
		{
			unsigned int pos_x = dim_x - 1 - i;
			sumD += convert(game_pieces[get_index(pos_x, i)].get_display());
		}
		return sumD;
	}
}

/***********************************************************

Handle a turn where user chooses a valid piece and makes a 
valid move.
A valid piece must be the pieces from available pieces.
A valid move must be placed to an empty coordinate.

Return:
return return_code::SUCCESS
when the move is successful

***********************************************************/
int MagicSquare::turn() {
	
	int chosen_num; 
	unsigned int dest_x, dest_y;

	do {
		cout << "enter the number you want to add: ";
		prompt(chosen_num);

		if (available.find(chosen_num) == available.end()) {
			cout << "the piece you chose does not exist! please choose again." << endl;
			continue;
		}

	} while (available.find(chosen_num) == available.end());

	do {

		do {
			cout << "enter the x coordinate you want to move to: ";
			prompt(dest_x);

			if (dest_x >= dim_x) {
				cout << "invalid x coordinate! please enter again." << endl;
				continue;
			}

		} while (dest_x >= dim_x);

		do {
			cout << "enter the y coordinate you want to move to: ";
			prompt(dest_y);

			if (dest_y >= dim_y) {
				cout << "invalid y coordinate! please enter again." << endl;
				continue;
			}

		} while (dest_y >= dim_y);

		if (game_pieces[get_index(dest_x, dest_y)] != space) {
			cout << "the coordinate you chose already contains a piece! please enter coordinates again." 
				<< endl << endl;
			continue;
		}

	} while (game_pieces[get_index(dest_x, dest_y)] != space);
	
	addPiece(chosen_num, dest_x, dest_y);

	cout << endl;

	return return_code::SUCCESS;
}

/***********************************************************

Prompt the number from input if valid.
Check if the player wants to quit or continue.

Input:
int &numAdded
the number needs to be prompted
unsigned int &pos
the position needs to be prompted (overloaded uniquely 
for unsigned int)

***********************************************************/
void MagicSquare::prompt(int &numAdded) {
	
	string line;

	/* get the next input line */
	while (getline(cin, line)) {

		/* trim and transfer to lower case */
		trim(line);
		lower_case(line);

		/* if user inputs quit, then quit */
		if (line == "quit") {
			throw return_code::USER_QUIT;
		}

		istringstream strm(line);
		if (strm >> numAdded) {
			break;
		}

	}

}
void MagicSquare::prompt(unsigned int &pos) {
	int temp = pos;
	prompt(temp);
	pos = temp;
}

/***********************************************************

Convert the integer entered into string.

Input:
const int& i
the integer

Return:
returnString
integer entered in string

***********************************************************/
string MagicSquare::convert(const int& i) {
	stringstream tempS;
	tempS << i;
	string returnString = tempS.str();
	return returnString;
}

/***********************************************************

Convert the string entered into integer.

Input:
const string& s
the string

Return:
string entered (should be numeric value) in integer.

***********************************************************/
int MagicSquare::convert(const string& s) {
	istringstream tempS(s);
	int i;
	tempS >> i;
	return i;
}

/***********************************************************

<< operator.

Input:
ostream& os
the ostream
const MagicSquare &game
the game to be played

Return:
the ostream that is passed in

***********************************************************/
ostream& operator<<(ostream& os, const MagicSquare &game) {

	const unsigned int w = game.max_len + 1;
	/* print from the top line backwards, the last line of game pieces are printed first */
	for (unsigned int pos_y = game.dim_y - 1; pos_y != -1; --pos_y) {
		os << pos_y;
		for (unsigned int pos_x = 0; pos_x != game.dim_x; ++pos_x) {
			os << setw(w) << game.game_pieces[game.get_index(pos_x, pos_y)].get_display();
		}
		os << endl;
	}

	/* print the horizontal coordinates */
	os << "X";
	for (unsigned int pos_x = 0; pos_x != game.dim_x; ++pos_x) {
		os << setw(w) << pos_x;
	}
	os << endl << endl;

	auto map_it = game.available.cbegin();
	cout << "Available pieces: ";
	while (map_it != game.available.cend()) {
		cout << map_it->second.get_display() << " ";
		map_it++;
	}
	cout << endl;
	return os;
}

/***********************************************************

Add a new game piece using the coordinate.

Input:
cost int & numAdded, const unsigned int& pos_x, const unsigned int& pos_y
the piece and its coordinate

Return:
True if the piece is successfully added to the board.
False otherwise.

***********************************************************/

bool MagicSquare::addPiece(const int & numAdded, const unsigned int & x, const unsigned int &y) {

	if (available.find(numAdded) != available.end()) {
		game_pieces[get_index(x, y)].set_name(convert(numAdded)).set_display(convert(numAdded));
		// remove the element in the available list
		available.erase(numAdded);
		// max length update
		if (convert(numAdded).length() > max_len) {
			max_len = convert(numAdded).length();
		}
		return true;
	}
	else {
		return false;
	}

}