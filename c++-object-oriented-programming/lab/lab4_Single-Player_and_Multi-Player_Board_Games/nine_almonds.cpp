/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

This file is the source file for Nine Almonds game.
In this file we define methods in NineAlmondsGame class and
common functions.

***********************************************************/
#include "stdafx.h"
#include "game.h"
#include "game_base.h"
#include "nine_almonds.h"
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <iterator>
#include <sstream>
#include <iomanip>

using namespace std;

const unsigned int N_DIM = 5; // dimension for NineAlmondsGame
const unsigned int N_LEN = 1; // initial length for NineAlmondsGame
const unsigned int N_PCE = 9; // number of initial game pieces for NineAlmondsGame

/***********************************************************

Constructor of NineAlmondsGame object. A 5*5 game board is
created and the middle 3*3 coordinates are filled with
almonds.

***********************************************************/
NineAlmondsGame::NineAlmondsGame() : GameBase(N_DIM, N_DIM, N_LEN) {

	ifstream in("NineAlmondsGame");
	string line;

	if (in.is_open()) {
		
		if (!getline(in, line)) {
			cout << "badly formed file! create a new game." << endl;
			default_game();
			in.close();
			return;
		}

		trim(line);
		
		if (line == "NO DATA") {
			default_game();
			in.close();
			return;
		}
		else {

			for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
				for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
					game_pieces.push_back(space);
				}
			}

			// get gamepieces
			if (!getline(in, line)) {
				cout << "badly formed file! create a new game." << endl;
				default_game();
				in.close();
				return;
			}

			try {
				istringstream strm_pieces(line);
				unsigned int index = 0;
				while (strm_pieces >> index) {
					if (index >= dim_x * dim_y) {
						throw return_code::BADLY_FORMED_FILE_ERR;
					}
					game_pieces[index] = almond;
				}
			}
			catch (...) {
				cout << "badly formed file! create a new game." << endl;
				default_game();
				in.close();
				return;
			}

			// get turns
			if (!getline(in, line)) {
				cout << "badly formed file! create a new game." << endl;
				default_game();
				in.close();
				return;
			}

			try {
				istringstream strm_turns(line);
				if (!(strm_turns >> turns)) throw return_code::BADLY_FORMED_FILE_ERR;
			}
			catch (...) {
				cout << "badly formed file! create a new game." << endl;
				default_game();
				in.close();
				return;
			}

			// check if the game is valid
			if (num_game_pieces() > N_PCE) {
				cout << "bad game condition from file! create a new game." << endl;
				default_game();
				in.close();
				return;
			}
			
		}

		in.close();

	}
	else {
		default_game();
	}
}

/***********************************************************

Default game.

***********************************************************/
void NineAlmondsGame::default_game() {
	game_pieces.clear();
	for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
		for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
			if (pos_y >= 1 && pos_y <= dim_y - 2 && pos_x >= 1 && pos_x <= dim_x - 2) {
				game_pieces.push_back(almond);
			}
			else {
				game_pieces.push_back(space);
			}
		}
	}
}

/***********************************************************

Print the board.

***********************************************************/
void NineAlmondsGame::print() {
	cout << *this << endl;
}

/***********************************************************

Detect if the game is successfully over.

Return:
true if there is one and only one almond at the center

***********************************************************/
bool NineAlmondsGame::done() {
	return num_game_pieces() == 1 && is_almond(dim_x / 2, dim_y / 2);
}

/***********************************************************

Detect if the game is over, but not successfully over.

Return:
true if the game is not successfully done, and there is no
movable piece on the board

***********************************************************/
bool NineAlmondsGame::stalemate() {
	if (done()) return false;
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_movable(i)) return false;
	}

	return true;
}

/***********************************************************

Handle a turn where user chooses a valid piece and makes a
valid move.

Return:
return_code::USER_QUIT
when user chooses to quit
return return_code::SUCCESS
when the move is successful

***********************************************************/
int NineAlmondsGame::turn() {

	unsigned int pos_x, pos_y, dest_x, dest_y;

	cout << "enter the piece you want to move: ";
	if (prompt(pos_x, pos_y) == return_code::USER_QUIT) {
		save_game();
		return return_code::USER_QUIT;
	}

	/* prepare to print the moves */
	string moves;
	moves.append(to_string(pos_x) + "," + to_string(pos_y));

	/* while the current piece is movable */
	while (is_movable(pos_x, pos_y)) {

		cout << "enter the destination: ";
		if (prompt(dest_x, dest_y) == return_code::USER_QUIT) {
			save_game();
			return return_code::USER_QUIT;
		}

		unsigned int index = get_index(pos_x, pos_y);
		unsigned int dest_index = get_index(dest_x, dest_y);

		/* if the current piece can jump to the destination */
		if (is_jumpable(index, dest_index) &&
			is_almond((pos_x + dest_x) / 2, (pos_y + dest_y) / 2)) {

			/* make the move */
			set_game_piece(pos_x, pos_y, space);
			set_game_piece(dest_x, dest_y, almond);
			set_game_piece((pos_x + dest_x) / 2, (pos_y + dest_y) / 2, space);

			/* update the string storing the moves */
			moves.append(" to " + to_string(dest_x) + ',' + to_string(dest_y));

			/* print the board and moves */
			cout << endl << *this << endl;
			cout << moves << endl << endl;

			/* if the current piece is not movable, this turn is over */
			if (!is_movable(dest_x, dest_y)) {
				cout << "this piece cannot be moved from its new position. this turn is over." << endl << endl;
				return return_code::SUCCESS;
			}
			else {

				cout << "Continue this turn (YyNn)?";
				string line;

				/* get the user's answer */
				while (getline(cin, line)) {

					trim(line);
					lower_case(line);

					/* if user input N or n, end this turn */
					if (line == "n") {
						cout << endl;
						return return_code::SUCCESS;
					}

					/* if user input Y or y, continue this turn */
					else if (line == "y") {
						pos_x = dest_x;
						pos_y = dest_y;
						break;
					}
					else {
						continue;
					}
				}
			}
		}
	}
	/* if the piece is not movable, gives the player a hint and recursively call itself */
	cout << "the piece you choose cannot be moved!" << endl;
	return turn();
}


/***********************************************************

Method to save NineAlmonds Game.

***********************************************************/
void NineAlmondsGame::save_game() {

	cout << "Do you want to save this game? (YyNn)";
	string line;

	// get the user's answer
	while (getline(cin, line)) {

		trim(line);
		lower_case(line);

		// if user input N or n, end this turn
		if (line == "n") {
			cout << endl;
			ofstream out("NineAlmondsGame");
			out.clear();
			out << "NO DATA" << endl;
			out.close();
			return;
		}

		// if user input Y or y, continue this turn //
		else if (line == "y") {
			// save game
				ofstream out("NineAlmondsGame");
				out.clear();
				out << "NineAlmondsGame" << endl;
				for (unsigned int i = 0; i != game_pieces.size(); ++i) {
					if (game_pieces[i] == almond) {
						out << i << " ";
					}
				}
				out << endl;
				out << turns;
				out.close();
				return;
		}
		else {
				continue;
		}
	}
}

/***********************************************************

<< operator.

Input:
ostream& os
the ostream
const NineAlmondsGame &game
the game to be played

Return:
the ostream that is passed in

***********************************************************/
ostream& operator<<(ostream& os, const NineAlmondsGame &game) {

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

Count how many almonds on the board.

Return:
the number of almonds on the board

***********************************************************/
unsigned int NineAlmondsGame::num_game_pieces() {
	unsigned int count = 0;
	for (game_piece p : game_pieces) {
		if (p == almond) count++;
	}
	return count;
}

/***********************************************************

Check if the piece is an almond.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
true if the piece is an almond

***********************************************************/
bool NineAlmondsGame::is_almond(const unsigned int& pos_x, const unsigned int& pos_y) {
	return get_game_piece(pos_x, pos_y) == almond;
}

/***********************************************************

Check if two pieces are neighbors if they are both almonds.

Input:
const unsigned int& x, const unsigned int& y
indices

Return:
true if the two pieces are neighbors and are both almonds

***********************************************************/
bool NineAlmondsGame::is_neighbor(const unsigned int& x, const unsigned int& y) {
	if (y >= dim_x * dim_y) return false;
	if (x == y) return false;
	if (!(game_pieces[y] == almond)) return false;
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

Check if a piece has neighbor if it is an almond.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate
OR:
const unsigned int& index
index

Return:
true if the piece has neighbor and is an almond

***********************************************************/
bool NineAlmondsGame::has_neighbor(const unsigned int& pos_x, const unsigned int& pos_y) {
	return has_neighbor(get_index(pos_x, pos_y));
}
bool NineAlmondsGame::has_neighbor(const unsigned int& index) {
	if (index >= dim_x * dim_y) return false;
	if (!(game_pieces[index] == almond)) return false;
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_neighbor(index, i)) return true;
	}
	return false;
}

/***********************************************************

Check if the second index is jumpable from the first index.

Input:
const unsigned int& x, const unsigned int& y
indices

Return:
true if the second index is reachable from the first index

***********************************************************/
bool NineAlmondsGame::is_jumpable(const unsigned int& x, const unsigned int& y) {
	if (x == y) return false;
	if (y >= dim_x * dim_y) return false;
	if (game_pieces[y] == almond) return false;
	int dim = dim_x;
	int pos_y = x / dim, pos_x = x % dim;
	int dest_y = y / dim, dest_x = y % dim;
	if (((dest_y - pos_y) == 2 || (dest_y - pos_y) == 0 || (dest_y - pos_y) == -2)
		&& ((dest_x - pos_x) == 2 || (dest_x - pos_x) == 0 || (dest_x - pos_x) == -2)) {
		return true;
	}
	else {
		return false;
	}
}

/***********************************************************

Check if a piece is movable if it is an almond.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate
OR:
const unsigned int& index
index

Return:
true if the piece is movable and is an almond

***********************************************************/
bool NineAlmondsGame::is_movable(const unsigned int& pos_x, const unsigned int& pos_y) {
	return is_movable(get_index(pos_x, pos_y));
}
bool NineAlmondsGame::is_movable(const unsigned int& index) {
	if (!has_neighbor(index)) return false;
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_neighbor(index, i)) {
			if (index > i) {
				unsigned int diff = index - i;
				if (is_jumpable(index, index - 2 * diff)) {
					return true;
				}
				else {
					continue;
				}
			}
			else {
				unsigned int diff = i - index;
				if (is_jumpable(index, index + 2 * diff)) {
					return true;
				}
				else {
					continue;
				}
			}
		}
	}
	return false;
}

/***********************************************************

Auto play.

Return:
return_code::SUCCESS
run successfully

***********************************************************/
int NineAlmondsGame::auto_play() {
	cout << "auto play begins; please wait for several seconds (or minutes) between solutions." << endl << endl;
	generate_seq(game_pieces);
	cout << endl << "all solutions generated; game ends." << endl;
	return return_code::SUCCESS;
}

/***********************************************************

Store the current state, recursively test the next move, and
generate the sequence string.

Input:
vector<game_piece>& game_pieces
vector to store all game pieces
string seq
sequence string

Return:
true if it reaches possible successful solutions for current
state
false if it will not reach any possible successful solutions
for current state

***********************************************************/
bool NineAlmondsGame::generate_seq(vector<game_piece>& game_pieces, string seq) {
	vector<game_piece> current_state(game_pieces);
	string current_seq(seq);
	for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
		for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
			vector<unsigned int> path = find_path(pos_x, pos_y);
			if (path.empty()) {
				continue;
			}
			for (unsigned int index : path) {
				unsigned int dest_y = index / dim_x;
				unsigned int dest_x = index % dim_x;
				move(pos_x, pos_y, dest_x, dest_y);
				seq.append(to_string(pos_x) + ',' + to_string(pos_y) + " to "
					+ to_string(dest_x) + ',' + to_string(dest_y));
				seq.append("\n");
				if (done()) {
					cout << *this << endl << seq << endl;
					return true;
				}
				else if (stalemate()) {
					game_pieces = current_state;
					seq = current_seq;
					continue;
				}
				else {
					bool success = generate_seq(game_pieces, seq);
					if (success) {
						cout << endl << "press enter to see next solution (if any)." << endl;
						if (cin.get()) {
							continue;
						}
					}
					else {
						game_pieces = current_state;
						seq = current_seq;
						continue;
					}
				}
			}
			game_pieces = current_state;
			seq = current_seq;
		}
	}
	return false;
}

/***********************************************************

Check if the second coordinate is the valid destination for
the first coordinate for a move.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
first coordinate
const unsigned int& dest_x, const unsigned int& dest_y
second coordinate

Return:
true if it is a valid move

***********************************************************/
bool NineAlmondsGame::find_move(const unsigned int& pos_x, const unsigned int& pos_y,
	const unsigned int& dest_x, const unsigned int& dest_y) {
	unsigned int index = get_index(pos_x, pos_y);
	unsigned int dest_index = get_index(dest_x, dest_y);
	if (is_almond(pos_x, pos_y) && is_jumpable(index, dest_index) &&
		is_almond((pos_x + dest_x) / 2, (pos_y + dest_y) / 2)) {
		return true;
	}
	else {
		return false;
	}
}

/***********************************************************

Find and store possible destinations for a piece.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
a vector storing all the valid destination indices

***********************************************************/
vector<unsigned int> NineAlmondsGame::find_path(const unsigned int& pos_x, const unsigned int& pos_y) {
	vector<unsigned int> avaliable_path;
	for (unsigned int dest_y = 0; dest_y != dim_y; ++dest_y) {
		for (unsigned int dest_x = 0; dest_x != dim_x; ++dest_x) {
			if (find_move(pos_x, pos_y, dest_x, dest_y)) {
				avaliable_path.push_back(get_index(dest_x, dest_y));
			}
		}
	}
	return avaliable_path;
}

/***********************************************************

Move the piece.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
first coordinate
const unsigned int& dest_x, const unsigned int& dest_y
second coordinate

***********************************************************/
void NineAlmondsGame::move(const unsigned int& pos_x, const unsigned int& pos_y,
	const unsigned int& dest_x, const unsigned int& dest_y) {
	set_game_piece(pos_x, pos_y, space);
	set_game_piece(dest_x, dest_y, almond);
	set_game_piece((pos_x + dest_x) / 2, (pos_y + dest_y) / 2, space);
}