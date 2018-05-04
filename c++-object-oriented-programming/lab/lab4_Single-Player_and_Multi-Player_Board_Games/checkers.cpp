/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#include "stdafx.h"
#include "game.h"
#include "game_base.h"
#include "checkers.h"
#include <ostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <utility>
#include <cstdlib>
#include <ctime>

using namespace std;

const unsigned int C_DIM = 8; // demension for Checkers
const unsigned int C_LEN = 1; // initial length for Checkers

Checkers::Checkers(const string& playerA, const string& playerB) : GameBase(C_DIM, C_DIM, C_LEN) {

	ifstream in("Checkers");
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
							game_pieces[temp_index] = black_pawn;
						}
						else if (dis == "O") {
							game_pieces[temp_index] = white_pawn;
						}
						else if (dis == "B") {
							game_pieces[temp_index] = black_king;
						}
						else if (dis == "W") {
							game_pieces[temp_index] = white_king;
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

void Checkers::print() {
	cout << *this << endl;
}

void Checkers::default_game(const string& playerA, const string& playerB) {
	for (unsigned int pos_y = 0; pos_y != dim_y; ++pos_y) {
		for (unsigned int pos_x = 0; pos_x != dim_x; ++pos_x) {
			game_pieces.push_back(space);
		}
	}
	for (unsigned int i = 0; i != C_DIM; i += 2) {
		game_pieces[i] = white_pawn;
	}
	for (unsigned int i = C_DIM + 1; i != C_DIM * 2 + 1; i += 2) {
		game_pieces[i] = white_pawn;
	}
	for (unsigned int i = C_DIM * 2 ; i != C_DIM * 3; i += 2) {
		game_pieces[i] = white_pawn;
	}
	for (unsigned int i = 5 * C_DIM + 1; i != C_DIM * 6 + 1; i += 2) {
		game_pieces[i] = black_pawn;
	}
	for (unsigned int i = C_DIM * 6; i != C_DIM * 7; i += 2) {
		game_pieces[i] = black_pawn;
	}
	for (unsigned int i = 7 * C_DIM + 1; i != C_DIM * 8 + 1; i += 2) {
		game_pieces[i] = black_pawn;
	}
	black_player = playerA;
	white_player = playerB;
	srand((unsigned int)time(NULL));
	current_color = rand() % 2 == 0 ? black : white;
}

ostream& operator<<(ostream& os, const Checkers& game) {
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

bool Checkers::done() {
	if (!has_valid_move(black)) return true;
	if (!has_valid_move(white)) return true;
	if (num_game_pieces(black) == 0) return true;
	if (num_game_pieces(white) == 0) return true;
	return false;
}

bool Checkers::stalemate() {
	return false;
}

int Checkers::turn() {

	cout << endl << "now is " << pick_player(current_color) <<
		"'s turn. (" << value_to_color(current_color) << ")" << endl;

	unsigned int pos_x, pos_y, dest_x, dest_y;

	cout << "enter the piece you want to move: ";
	if (prompt(pos_x, pos_y) == return_code::USER_QUIT) {
		save_game();
		return return_code::USER_QUIT;
	}

	if (!is_current_piece(get_index(pos_x, pos_y), current_color)) {
		cout << "this is not your piece! please choose again." << endl;
		return turn();
	}

	/* prepare to print the moves */
	string moves;
	moves.append(to_string(pos_x) + "," + to_string(pos_y));

	bool jumped = false;
			
	/* while the current piece is movable */
	while (is_movable_with_jump(pos_x, pos_y) || is_movable_without_jump(pos_x, pos_y)) {

		cout << "enter the destination: ";
		if (prompt(dest_x, dest_y) == return_code::USER_QUIT) {
			save_game();
			return return_code::USER_QUIT;
		}
		cout << endl;

		unsigned int index = get_index(pos_x, pos_y);
		unsigned int dest_index = get_index(dest_x, dest_y);
		unsigned int jump_index = get_index((pos_x + dest_x) / 2, (pos_y + dest_y) / 2);

		if (is_neighbor(index, dest_index) &&
			is_space(dest_x, dest_y) &&
			!jumped) {

			bool mandatory_option = false;

			for (unsigned int i = 0; i != game_pieces.size(); ++i) {
				if (is_current_piece(i, current_color) && is_movable_with_jump(i)) {
					mandatory_option = true;
					break;
				}
			}

			if (mandatory_option) {
				cout << "you have an option to jump. " <<
					"you must take it! jumping is always mandatory!" << endl << endl;
				break;
			}

			set_game_piece(dest_x, dest_y, game_pieces[index]);
			set_game_piece(pos_x, pos_y, space);

			if (current_color == white) {
				if (dest_y == dim_y - 1) {
					cout << "crowned a king!" << endl << endl;
					set_game_piece(dest_x, dest_y, white_king);
				}
			}
			if (current_color == black) {
				if (dest_y == 0) {
					cout << "crowned a king!" << endl << endl;
					set_game_piece(dest_x, dest_y, black_king);
				}
			}

			current_color = get_reverse_color(current_color);

			return return_code::SUCCESS;

		}

		if (is_jumpable(index, dest_index) &&
			is_reverse_piece(jump_index, current_color)) {

			set_game_piece(dest_x, dest_y, game_pieces[index]);
			set_game_piece(pos_x, pos_y, space);
			set_game_piece((pos_x + dest_x) / 2, (pos_y + dest_y) / 2, space);

			jumped = true;

			moves.append(" to " + to_string(dest_x) + ',' + to_string(dest_y));

			cout << endl << *this << endl;
			cout << moves << endl << endl;

			if (!is_movable_with_jump(dest_x, dest_y)) {
				cout << "this piece cannot be jumped from its new position. this turn is over." << endl << endl;
				if (current_color == white) {
					if (dest_y == dim_y - 1) {
						cout << "crowned a king!" << endl << endl;
						set_game_piece(dest_x, dest_y, white_king);
					}
				}
				if (current_color == black) {
					if (dest_y == 0) {
						cout << "crowned a king!" << endl << endl;
						set_game_piece(dest_x, dest_y, black_king);
					}
				}
				current_color = get_reverse_color(current_color);
				return return_code::SUCCESS;
			}
			else {
				cout << "the piece can be jumped from its new position. please continue." << endl << endl;
				pos_x = dest_x;
				pos_y = dest_y;
				continue;
			}
		}
	}
	/* if the piece is not movable, gives the player a hint and recursively call itself */
	cout << "invalid move! please choose again." << endl;
	return turn();

}

int Checkers::play() {
	while (true) {
		print();
		if (done()) {
			cout << "game over!" << endl;
			if (!has_valid_move(white)) cout << "BLACK WINS!" << endl;
			else if (!has_valid_move(black)) cout << "WHITE WINS!" << endl;
			else if (num_game_pieces(white) == 0) cout << "BLACK WINS!" << endl;
			else if (num_game_pieces(black) == 0) cout << "WHITE WINS!" << endl;
			return return_code::SUCCESS;
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

bool Checkers::has_valid_move(piece_color c) {
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_current_piece(i, c) 
			&& (is_movable_with_jump(i) || is_movable_without_jump(i))) 
			return true;
	}
	return false;
}

const game_piece& Checkers::pick_pawn(piece_color c) {
	if (c == black) return black_pawn;
	else if (c == white) return white_pawn;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

const game_piece& Checkers::pick_reverse_pawn(piece_color c) {
	if (c == black) return pick_pawn(white);
	else if (c == white) return pick_pawn(black);
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

const game_piece& Checkers::pick_king(piece_color c) {
	if (c == black) return black_king;
	else if (c == white) return white_king;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

const game_piece& Checkers::pick_reverse_king(piece_color c) {
	if (c == black) return pick_king(white);
	else if (c == white) return pick_king(black);
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

const string& Checkers::pick_player(piece_color c) {
	if (c == black) return black_player;
	else if (c == white) return white_player;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

piece_color Checkers::get_reverse_color(piece_color c) {
	if (c == black) return white;
	else if (c == white) return black;
	else throw return_code::BAD_GAME_CONDITION_ERR;
}

unsigned int Checkers::num_game_pieces() {
	unsigned int count = 0;
	for (game_piece p : game_pieces) {
		if (p != space) count++;
	}
	return count;
}
unsigned int Checkers::num_game_pieces(piece_color c) {
	unsigned int count = 0;
	for (game_piece p : game_pieces) {
		if (p == pick_pawn(c) || p == pick_king(c)) count++;
	}
	return count;
}

bool Checkers::is_current_piece(const unsigned int& index, piece_color c) {
	return game_pieces[index] == pick_pawn(c) || game_pieces[index] == pick_king(c);
}

bool Checkers::is_reverse_piece(const unsigned int& index, piece_color c) {
	return game_pieces[index] == pick_reverse_pawn(c) || game_pieces[index] == pick_reverse_king(c);
}

bool Checkers::is_space(const unsigned int& pos_x, const unsigned int& pos_y) {
	return get_game_piece(pos_x, pos_y) == space;
}

bool Checkers::is_neighbor(const unsigned int& x, const unsigned int& y) {
	if (x >= dim_x * dim_y || y >= dim_x * dim_y) return false;
	if (x == y) return false;
	int dim = dim_x;
	int pos_y = x / dim, pos_x = x % dim;
	int dest_y = y / dim, dest_x = y % dim;
	game_piece p = game_pieces[x];
	if (p == black_king || p == white_king) {
		if (((dest_y - pos_y) == 1 || (dest_y - pos_y) == -1)
			&& ((dest_x - pos_x) == 1 || (dest_x - pos_x) == -1)) {
			if (game_pieces[y] == space 
				|| game_pieces[y] == pick_reverse_pawn(p.get_color()) 
				|| game_pieces[y] == pick_reverse_king(p.get_color())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	if (p == white_pawn) {
		if (((dest_y - pos_y) == 1)
			&& ((dest_x - pos_x) == 1 || (dest_x - pos_x) == -1)) {
			if (game_pieces[y] == space
				|| game_pieces[y] == pick_reverse_pawn(p.get_color())
				|| game_pieces[y] == pick_reverse_king(p.get_color())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	if (p == black_pawn) {
		if (((dest_y - pos_y) == -1)
			&& ((dest_x - pos_x) == 1 || (dest_x - pos_x) == -1)) {
			if (game_pieces[y] == space
				|| game_pieces[y] == pick_reverse_pawn(p.get_color())
				|| game_pieces[y] == pick_reverse_king(p.get_color())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	return false;
}

bool Checkers::has_neighbor(const unsigned int& pos_x, const unsigned int& pos_y) {
	return has_neighbor(get_index(pos_x, pos_y));
}
bool Checkers::has_neighbor(const unsigned int& index) {
	if (index >= dim_x * dim_y) return false;
	if (game_pieces[index] == space) return false;
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_neighbor(index, i)) 
			return true;
	}
	return false;
}

bool Checkers::is_jumpable(const unsigned int& x, const unsigned int& y) {
	if (x == y) return false;
	if (y >= dim_x * dim_y) return false;
	if (game_pieces[x] == space) return false;
	int dim = dim_x;
	int pos_y = x / dim, pos_x = x % dim;
	int dest_y = y / dim, dest_x = y % dim;
	if (((dest_y - pos_y) == 2 || (dest_y - pos_y) == -2)
		&& ((dest_x - pos_x) == 2 || (dest_x - pos_x) == -2)) {
		piece_color pos_c = game_pieces[x].get_color();
		piece_color mid_c = get_game_piece((pos_x + dest_x) / 2, (pos_y + dest_y) / 2).get_color();
		if (game_pieces[y] == space && mid_c == get_reverse_color(pos_c)) return true;
	}
	return false;
}

bool Checkers::is_movable_with_jump(const unsigned int& pos_x, const unsigned int& pos_y) {
	return is_movable_with_jump(get_index(pos_x, pos_y));
}
bool Checkers::is_movable_with_jump(const unsigned int& index) {
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

bool Checkers::is_movable_without_jump(const unsigned int& pos_x, const unsigned int& pos_y) {
	return is_movable_without_jump(get_index(pos_x, pos_y));
}
bool Checkers::is_movable_without_jump(const unsigned int& index) {
	if (!has_neighbor(index)) return false;
	for (unsigned int i = 0; i != game_pieces.size(); ++i) {
		if (is_neighbor(index, i)) {
			if (game_pieces[i] == space) return true;
		}
	}
	return false;
}

void Checkers::save_game() {

	cout << "Do you want to save this game? (YyNn)";
	string line;

	// get the user's answer
	while (getline(cin, line)) {

		trim(line);
		lower_case(line);

		// if user input N or n, end this turn
		if (line == "n") {
			cout << endl;
			ofstream out("Checkers");
			out.clear();
			out << "NO DATA" << endl;
			out.close();
			return;
		}

		// if user input Y or y, continue this turn //
		else if (line == "y") {

			// save game
			ofstream out("Checkers");
			out.clear();
			out << "Checkers" << endl;
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
