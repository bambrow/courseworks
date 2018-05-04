/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

This is the source file for some methods in all games.

***********************************************************/
#include "stdafx.h"
#include "game.h"
#include "game_base.h"
#include "nine_almonds.h"
#include "magic_square.h"
#include "reversi_game.h"
#include "checkers.h"
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <iterator>
#include <sstream>
#include <iomanip>

using namespace std;

shared_ptr<GameBase> GameBase::game_ptr = nullptr; // game pointer
static string arg_name; // argument of game name

/***********************************************************

Generate smart pointer for game.

Return:
shared_ptr<GameBase>
the proper smart pointer of game

***********************************************************/
shared_ptr<GameBase> GameBase::instance() {
	if (game_ptr != nullptr) {
		return game_ptr;
	}
	else {
		throw return_code::NO_GAME_CONSTRUCTED_ERR;
	}
}

/***********************************************************

Start the proper game using the given inputs.

Input:
int argc, char * argv[]
command line inputs

Return:
GameBase*
the proper pointer of game

***********************************************************/
void GameBase::start(int argc, char * argv[]) {

	if (game_ptr != nullptr) {
		throw return_code::BAD_GAME_POINTER_ERR;
	}

	if (argc == 1) {
		usage_message(argv[array_index::PROGRAM_NAME], "NineAlmonds");
		usage_message(argv[array_index::PROGRAM_NAME], "MagicSquare");
		usage_message(argv[array_index::PROGRAM_NAME], "Reversi [black_player] [white_player]");
		usage_message(argv[array_index::PROGRAM_NAME], "Checkers [black_player] [white_player]");
		throw return_code::WRONG_ARGUMENT_NUM_ERR;
	}

	arg_name = argv[array_index::ARGUMENT_NAME];

	if (arg_name == "NineAlmonds") {
		if (argc != array_index::ARGUMENT_NUM - 2) {
			usage_message(argv[array_index::PROGRAM_NAME], "NineAlmonds");
			throw return_code::WRONG_ARGUMENT_NUM_ERR;
		}
		else {
			game_ptr = make_shared<NineAlmondsGame>();
			return;
		}
	}

	if (arg_name == "MagicSquare") {
		if (argc != array_index::ARGUMENT_NUM - 2) {
			usage_message(argv[array_index::PROGRAM_NAME], "MagicSquare");
			throw return_code::WRONG_ARGUMENT_NUM_ERR;
		}
		else {
			game_ptr = make_shared<MagicSquare>();
			return;
		}
	}

	if (arg_name == "Reversi") {
		if (argc != array_index::ARGUMENT_NUM) {
			usage_message(argv[array_index::PROGRAM_NAME], "Reversi [black_player] [white_player]");
			throw return_code::WRONG_ARGUMENT_NUM_ERR;
		}
		else {
			string playerA = argv[array_index::EXTRA_ARG1];
			string playerB = argv[array_index::EXTRA_ARG2];
			game_ptr = make_shared<ReversiGame>(playerA, playerB);
			return;
		}
	}

	if (arg_name == "Checkers") {
		if (argc != array_index::ARGUMENT_NUM) {
			usage_message(argv[array_index::PROGRAM_NAME], "Checkers [black_player] [white_player]");
			throw return_code::WRONG_ARGUMENT_NUM_ERR;
		}
		else {
			string playerA = argv[array_index::EXTRA_ARG1];
			string playerB = argv[array_index::EXTRA_ARG2];
			game_ptr = make_shared<Checkers>(playerA, playerB);
			return;
		}
	}

	usage_message(argv[array_index::PROGRAM_NAME], "NineAlmonds");
	usage_message(argv[array_index::PROGRAM_NAME], "MagicSquare");
	usage_message(argv[array_index::PROGRAM_NAME], "Reversi [black_player] [white_player]");
	usage_message(argv[array_index::PROGRAM_NAME], "Checkers [black_player] [white_player]");
	throw return_code::NO_GAME_CONSTRUCTED_ERR;

}

/***********************************************************

Prompt the coordinate if valid.

Input:
unsigned int & pos_x, unsigned int & pos_y
coordinate on the board

Return:
return_code::USER_QUIT
when user chooses to quit
return return_code::SUCCESS
when prompt successfully

***********************************************************/
int GameBase::prompt(unsigned int & pos_x, unsigned int & pos_y) {

	string line;

	/* get the next input line */
	while (getline(cin, line)) {

		/* trim and transfer to lower case */
		trim(line);
		lower_case(line);

		/* if user inputs quit, then quit */
		if (line == "quit") {
			return return_code::USER_QUIT;
		}

		/* if no comma in the line, the input is invalid; continue to ask for input */
		if (!reform(line)) continue;

		istringstream strm(line);
		/* if cannot extract two numbers, continue to ask for input */
		if (!((strm >> pos_x) && (strm >> pos_y))) continue;

		/* if the coordinate is invalid, continue to ask for input */
		if (pos_x >= dim_x || pos_y >= dim_y) {
			continue;
		}
		else {
			return return_code::SUCCESS;
		}
	}
	/* if cannot get a line, recursively call itself */
	return prompt(pos_x, pos_y);
}

/***********************************************************

Handle the game status.

Return:
return return_code::SUCCESS
when the game ends successfully

***********************************************************/
int GameBase::play() {
	while (true) {
		print();
		if (done()) {
			cout << "success!" << endl;
			cout << "total turns: " << turns << endl;
			return return_code::SUCCESS;
		}

		if (stalemate()) {
			cout << "no valid move! game over." << endl;
			cout << "total turns: " << turns << endl;
			throw return_code::NO_VALID_MOVE;
		}

		if (turn() == return_code::USER_QUIT) {
			cout << "user quit!" << endl;
			cout << "total turns: " << turns << endl;
			throw return_code::USER_QUIT;
		}
		else {
			turns++;
		}
	}
}

/***********************************************************

Get the current index in the vector using the coordinate.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
the index corresponding to the coordinate

***********************************************************/
unsigned int GameBase::get_index(const unsigned int& pos_x, const unsigned int& pos_y) const {
	return dim_x * pos_y + pos_x;
}

/***********************************************************

Get the current game piece using the coordinate.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

Return:
the game piece corresponding to the coordinate

***********************************************************/
const game_piece& GameBase::get_game_piece(const unsigned int& pos_x, const unsigned int& pos_y) {
	return game_pieces[get_index(pos_x, pos_y)];
}

/***********************************************************

Set the current game piece using the coordinate.

Input:
const unsigned int& pos_x, const unsigned int& pos_y
coordinate

***********************************************************/
void GameBase::set_game_piece(const unsigned int& pos_x, const unsigned int& pos_y,
	const game_piece& p) {
	game_pieces[get_index(pos_x, pos_y)] = p;
}

ostream& operator<<(ostream& os, const GameBase &game) {

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
	os << endl;
	return os;
}