/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

This file is the source file for Reversi game.
In this file we define methods in ReversiGame class and
common functions.

***********************************************************/
#include "stdafx.h"
#include "game.h"
#include "game_base.h"
#include "reversi_game.h"
#include <ostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <utility>

using namespace std;

const unsigned int R_DIM = 8; // demension for ReversiGame
const unsigned int R_LEN = 1; // initial length for ReversiGame

/***********************************************************

Constructor of ReversiGame object. A 8*8 game board is
created and (3,3), (4,4) positions are filled with black,
(3,4), (4,3) positions are filled with white.

***********************************************************/
ReversiGame::ReversiGame(const string& playerA, const string& playerB) : GameBase(R_DIM, R_DIM, R_LEN) {

	ifstream in("ReversiGame");
	string line;

	if (in.is_open()) {

		if (!getline(in, line)) {
			cout << "badly formed file! create a new game." << endl;
			default_game(playerA, playerB);
			in.close();
			return;
		}

		trim(line);

		if (line == "NO DATA") {
			default_game(playerA, playerB);
			in.close();
			return;
		}
		else {

			for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
				for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
					game_pieces.push_back(space);
				}
			}

			if (!getline(in, line)) {
				cout << "badly formed file! create a new game." << endl;
				default_game(playerA, playerB);
				in.close();
				return;
			}

			try {
				istringstream strm_player(line);
				string temp_playerA, temp_playerB;
				if ((strm_player >> temp_playerA) && (strm_player >> temp_playerB)) {
					black_player = temp_playerA;
					white_player = temp_playerB;
				}
				else {
					throw return_code::BADLY_FORMED_FILE_ERR;
				}
			}
			catch (...) {
				cout << "badly formed file! create a new game." << endl;
				default_game(playerA, playerB);
				in.close();
				return;
			}

			if (!getline(in, line)) {
				cout << "badly formed file! create a new game." << endl;
				default_game(playerA, playerB);
				in.close();
				return;
			}

			trim(line);

			if (line == "black") current_color = black;
			else if (line == "white") current_color = white;
			else {
				cout << "badly formed file! create a new game." << endl;
				default_game(playerA, playerB);
				in.close();
				return;
			}

			try {
				while (getline(in, line))
				{
					istringstream strm_line(line);
					unsigned int temp_index;
					string dis;
					if ((strm_line >> temp_index) && (strm_line >> dis)) {
						if (temp_index >= dim_x * dim_y) {
							throw return_code::BADLY_FORMED_FILE_ERR;
						}
						if (dis == "X") {
							game_pieces[temp_index] = black_piece;
						}
						else if (dis == "O") {
							game_pieces[temp_index] = white_piece;
						}
						else {
							throw return_code::BADLY_FORMED_FILE_ERR;
						}
					}
					else {
						throw return_code::BADLY_FORMED_FILE_ERR;
					}
				}
			}
			catch (...) {
				cout << "badly formed file! create a new game." << endl;
				default_game(playerA, playerB);
				in.close();
				return;
			}

		}

		in.close();

	}
	else {
		default_game(playerA, playerB);
	}
}

/***********************************************************

Print the board.

***********************************************************/
void ReversiGame::print() {
	cout << *this << endl;
}

/***********************************************************

Default game.

***********************************************************/
void ReversiGame::default_game(const string& playerA, const string& playerB) {
	for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
		for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
			game_pieces.push_back(space);
		}
	}
	const unsigned int MID = R_DIM / 2;
	set_game_piece(MID, MID, black_piece);
	set_game_piece(MID - 1, MID - 1, black_piece);
	set_game_piece(MID, MID - 1, white_piece);
	set_game_piece(MID - 1, MID, white_piece);
	black_player = playerA;
	white_player = playerB;
}

/***********************************************************

<< operator.

Input:
ostream& os
the ostream
const ReversiGame &game
the game to be played

Return:
the ostream that is passed in

***********************************************************/
ostream& operator<<(ostream& os, const ReversiGame& game) {
	for (unsigned int pos_y = game.dim_y - 1; pos_y != -1; --pos_y) {
		os << pos_y << " ";
		for (unsigned int pos_x = 0; pos_x != game.dim_x; ++pos_x) {
			os << game.game_pieces[game.get_index(pos_x, pos_y)].get_display() << " ";
		}
		os << endl;
	}

	os << "X ";
	for (unsigned int pos_x = 0; pos_x != game.dim_x; ++pos_x) {
		os << pos_x << " ";
	}
	os << endl;
	return os;
}

/***********************************************************

Detect if the game over and one player wins.

Return:
true if	1. all pieces on the board are of the same color, or
		2. there are more pieces of one color, and
			a) all squares are filled, or
			b) there are no legal moves for either player

***********************************************************/
bool ReversiGame::done() {

	if (num_game_pieces(black) == num_game_pieces() ||
		num_game_pieces(white) == num_game_pieces()) 
		return true;

	if (num_game_pieces(black) != num_game_pieces(white)) {
		if (num_game_pieces() == dim_x * dim_y) return true;
		if (!has_valid_move(black) && !has_valid_move(white)) return true;
	}

	return false;

}

/***********************************************************

Detect if the game is over and neither of the players wins.

Return:
true if	there are same number of black of white pieces, and
			a) all squares are filled, or
			b) there are no legal moves for either player

***********************************************************/
bool ReversiGame::stalemate() {

	if (num_game_pieces(black) == num_game_pieces(white)) {
		if (num_game_pieces() == dim_x * dim_y) return true;
		if (!has_valid_move(black) && !has_valid_move(white)) return true;
	}

	return false;

}

/***********************************************************

Handle a turn where either user chooses to make a valid move.

Return:
return_code::USER_QUIT
when user chooses to quit
return return_code::SUCCESS
when the move is successful

***********************************************************/
int ReversiGame::turn() {

	cout << endl << "now is " << pick_player(current_color) << "'s turn." << endl;

	if (!has_valid_move(current_color)) {
		cout << "no valid move for " << pick_player(current_color) << "! "
			<< "swith to the other player." << endl << endl;
		current_color = get_reverse_color(current_color);
		return return_code::SUCCESS;
	}

	cout << "enter the coordinate (x,y) you want to put your "
		<< value_to_color(current_color) << " piece: ";

	unsigned int pos_x, pos_y;

	if (prompt(pos_x, pos_y) == return_code::USER_QUIT) {
		save_game();
		cout << endl;
		return return_code::USER_QUIT;
	}
	cout << endl;

	while (get_game_piece(pos_x, pos_y) != space || !check_all(pos_x, pos_y, current_color)) {
		cout << "the coordinate you entered cannot be played. please enter again: ";
		if (prompt(pos_x, pos_y) == return_code::USER_QUIT) {
			save_game();
			cout << endl;
			return return_code::USER_QUIT;
		}
		cout << endl;
	}

	flip_all(pos_x, pos_y, current_color);
	cout << endl;
	cout << "black piece: " << num_game_pieces(black) 
		<< "; white piece: " << num_game_pieces(white) << endl << endl;

	current_color = get_reverse_color(current_color);
	return return_code::SUCCESS;

}

/***********************************************************

Overloaded play() method.

Return:
return_code::USER_QUIT
when user chooses to quit
return_code::NO_VALID_MOVE
when there is a tie
return return_code::SUCCESS
when either player wins

***********************************************************/
int ReversiGame::play() {
	while (true) {
		print();
		if (done()) {
			cout << "game over!" << endl;
			unsigned int black_score = num_game_pieces(black);
			unsigned int white_score = num_game_pieces(white);
			cout << "black piece: " << black_score
				<< "; white piece: " << white_score << endl << endl;
			if (black_score > white_score) cout << "BLACK WINS!" << endl;
			else cout << "WHITE WINS!" << endl;
			return return_code::SUCCESS;
		}

		if (stalemate()) {
			cout << "game over!" << endl;
			unsigned int black_score = num_game_pieces(black);
			unsigned int white_score = num_game_pieces(white);
			cout << "black piece: " << black_score
				<< "; white piece: " << white_score << endl << endl;
			cout << "TIE!" << endl;
			throw return_code::NO_VALID_MOVE;
		}

		if (turn() == return_code::USER_QUIT) {
			cout << "user quit!" << endl;
			throw return_code::USER_QUIT;
		}
		else {
			turns++;
		}
	}
}

/***********************************************************

Method to save Reversi Game.

***********************************************************/
void ReversiGame::save_game() {

	cout << "Do you want to save this game? (YyNn)";
	string line;

	// get the user's answer
	while (getline(cin, line)) {

		trim(line);
		lower_case(line);

		// if user input N or n, end this turn
		if (line == "n") {
			cout << endl;
			ofstream out("ReversiGame");
			out.clear();
			out << "NO DATA" << endl;
			out.close();
			return;
		}

		// if user input Y or y, continue this turn //
		else if (line == "y") {

			// save game
			ofstream out("ReversiGame");
			out.clear();
			out << "ReversiGame" << endl;
			out << black_player << " " << white_player << endl;

			if (current_color == black) {
				out << "black" << endl;
			}
			else {
				out << "white" << endl;
			}

			for (unsigned int i = 0; i != game_pieces.size(); ++i) {
				if (game_pieces[i] != space) {
					out << i << " " << game_pieces[i].get_display() << endl;
				}
			}

			out.close();
			return;
		}
		else {
			continue;
		}
	}
}

/***********************************************************

Choose game piece according to color.

Input:
piece_color c
color

Return:
const game_piece&
the corresponding game piece

***********************************************************/
const game_piece& ReversiGame::pick_piece(piece_color c) {
	if (c == black) return black_piece;
	else if (c == white) return white_piece;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}
const game_piece& ReversiGame::pick_reverse_piece(piece_color c) {
	if (c == black) return white_piece;
	else if (c == white) return black_piece;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

/***********************************************************

Choose game player according to color.

Input:
piece_color c
color

Return:
const string&
the corresponding player

***********************************************************/
const string& ReversiGame::pick_player(piece_color c) {
	if (c == black) return black_player;
	else if (c == white) return white_player;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

/***********************************************************

Return the reverse color.

Input:
piece_color c
color

Return:
piece_color
the reverse color

***********************************************************/
piece_color ReversiGame::get_reverse_color(piece_color c) {
	if (c == black) return white;
	else if (c == white) return black;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

/***********************************************************

Detect if there is any valid move for the given player.

Input:
piece_color c
color

Return:
true if there is any valid move for this color

***********************************************************/
bool ReversiGame::has_valid_move(piece_color color) {
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (game_pieces[i] == space) {
			if (!has_neighbor(i, get_reverse_color(color))) continue;
			unsigned int pos_y = i / dim_x, pos_x = i % dim_x;
			if (check_all(pos_x, pos_y, color)) return true;
		}
	}
	return false;
}

/***********************************************************

Count how many pieces on the board.

Input:
none
OR
piece_color c
the color needs to be counted

Return:
the number of pieces on the board
(without or with the certain color)

***********************************************************/
unsigned int ReversiGame::num_game_pieces() {
	unsigned int count = 0;
	for (game_piece p : game_pieces) {
		if (p != space) count++;
	}
	return count;
}
unsigned int ReversiGame::num_game_pieces(piece_color c) {
	unsigned int count = 0;
	for (game_piece p : game_pieces) {
		if (p == pick_piece(c)) count++;
	}
	return count;
}

/***********************************************************

Check if the piece is an black_piece.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
true if the piece is an black_piece

***********************************************************/
bool ReversiGame::is_black_piece(const unsigned int& pos_x, const unsigned int& pos_y) {
	return get_game_piece(pos_x, pos_y) == black_piece;
}

/***********************************************************

Check if the piece is an white_piece.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
true if the piece is an white_piece

***********************************************************/
bool ReversiGame::is_white_piece(const unsigned int& pos_x, const unsigned int& pos_y) {
	return get_game_piece(pos_x, pos_y) == white_piece;
}

/***********************************************************

Check if the place is space.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
true if the piece is space

***********************************************************/
bool ReversiGame::is_space(const unsigned int& pos_x, const unsigned int& pos_y) {
	return get_game_piece(pos_x, pos_y) == space;
}

/***********************************************************

Check if two places are neighbors.

Input:
const unsigned int& x, const unsigned int& y
indices

Return:
true if the two places are neighbors

***********************************************************/
bool ReversiGame::is_neighbor(const unsigned int& x, const unsigned int& y) {
	if (x >= dim_x * dim_y || y >= dim_x * dim_y) return false;
	if (x == y) return false;
	int dim = dim_x;
	int pos_y = x / dim, pos_x = x % dim;
	int dest_y = y / dim, dest_x = y % dim;
	if (((dest_y - pos_y) == 1 || (dest_y - pos_y) == 0 || (dest_y - pos_y) == -1)
		&& ((dest_x - pos_x) == 1 || (dest_x - pos_x) == 0 || (dest_x - pos_x) == -1)) {
		return true;
	}
	else {
		return false;
	}
}

/***********************************************************

Check if a place has neighbor of a certain color.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate
piece_color c
color
OR:
const unsigned int& index
index
piece_color c
color

Return:
true if the place has neighbor of this color

***********************************************************/
bool ReversiGame::has_neighbor(const unsigned int& pos_x, const unsigned int& pos_y, piece_color c) {
	return has_neighbor(get_index(pos_x, pos_y), c);
}
bool ReversiGame::has_neighbor(const unsigned int& index, piece_color c) {
	if (index >= dim_x * dim_y) return false;
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_neighbor(index, i)) {
			if (game_pieces[i] == pick_piece(c)) return true;
		}
	}
	return false;
}

/***********************************************************

Check one direction of a place (space) and return if there
is any valid play.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinates
piece_color color_to_find
the color that needed to be checked
const int& i, const int& j
the direction

Return:
true if there is any valid play in this direction

***********************************************************/
bool ReversiGame::check(const unsigned int& pos_x, const unsigned int& pos_y, 
	piece_color color_to_find, const int& i, const int& j) {

	if ((i != 0 && i != 1 && i != -1) || (j != 0 && j != 1 && j != -1))
		throw return_code::BAD_GAME_CONDITION_ERR;

	if (i == 1 && pos_x == dim_x - 1) return false;
	if (i == -1 && pos_x == 0) return false;
	if (j == 1 && pos_y == dim_y - 1) return false;
	if (j == -1 && pos_y == 0) return false;

	game_piece cur = get_game_piece(pos_x, pos_y);
	game_piece next = get_game_piece(i == 0 ? pos_x : (i == 1 ? pos_x + 1 : pos_x - 1),
		j == 0 ? pos_y : (j == 1 ? pos_y + 1 : pos_y - 1));

	if (next == space) return false;
	else if (cur == space || cur == pick_piece(color_to_find)) {
		if (next == pick_piece(color_to_find)) return false;
		if (next == pick_reverse_piece(color_to_find)) 
			return check(i == 0 ? pos_x : (i == 1 ? pos_x + 1 : pos_x - 1),
				j == 0 ? pos_y : (j == 1 ? pos_y + 1 : pos_y - 1),
				color_to_find, i, j);
	}
	else if (cur == pick_reverse_piece(color_to_find)) {
		if (next == pick_piece(color_to_find)) return true;
		if (next == pick_reverse_piece(color_to_find))
			return check(i == 0 ? pos_x : (i == 1 ? pos_x + 1 : pos_x - 1),
				j == 0 ? pos_y : (j == 1 ? pos_y + 1 : pos_y - 1),
				color_to_find, i, j);
	}
	
	return false;

}

/***********************************************************

Check all directions for a certain player.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinates
piece_color color_to_find
the color that needed to be checked

Return:
true if there is any valid play in all directions

***********************************************************/
bool ReversiGame::check_all(const unsigned int& pos_x, const unsigned int& pos_y, 
	piece_color color_to_find) {

	for (int i = -1; i != 2; ++i) {
		for (int j = -1; j != 2; ++j) {
			if (i == 0 && j == 0) continue;
			if (check(pos_x, pos_y, color_to_find, i, j)) return true;
		}
	}
	return false;

}

/***********************************************************

Flip the pieces of one direction.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinates
piece_color color_to_find
the color that needed to be turned into
const int& i, const int& j
the direction

***********************************************************/
void ReversiGame::flip(const unsigned int& pos_x, const unsigned int& pos_y,
	piece_color color_to_find, const int& i, const int& j) {

	if ((i != 0 && i != 1 && i != -1) || (j != 0 && j != 1 && j != -1))
		throw return_code::BAD_GAME_CONDITION_ERR;

	game_piece next = get_game_piece(i == 0 ? pos_x : (i == 1 ? pos_x + 1 : pos_x - 1),
		j == 0 ? pos_y : (j == 1 ? pos_y + 1 : pos_y - 1));

	set_game_piece(pos_x, pos_y, pick_piece(color_to_find));
	if (next == pick_piece(color_to_find)) return;
	
	flip(i == 0 ? pos_x : (i == 1 ? pos_x + 1 : pos_x - 1),
		j == 0 ? pos_y : (j == 1 ? pos_y + 1 : pos_y - 1),
		color_to_find, i, j);

}

/***********************************************************

Flip all directions for a certain player.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinates
piece_color color_to_find
the color that needed to be turned into

***********************************************************/
void ReversiGame::flip_all(const unsigned int& pos_x, const unsigned int& pos_y,
	piece_color color_to_find) {

	for (int i = -1; i != 2; ++i) {
		for (int j = -1; j != 2; ++j) {
			if (i == 0 && j == 0) continue;
			if (check(pos_x, pos_y, color_to_find, i, j)) {
				flip(pos_x, pos_y, color_to_find, i, j);
			}
		}
	}

}
