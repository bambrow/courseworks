/***********************************************************

Lab1: Game Boards and Pieces
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "game_pieces.h"
#include "game_board.h"
#include "common.h"
#include <vector>
#include <string>
#include <iostream>
#include <sstream>

using namespace std;

int main(int argc, char * argv[])
{

	/* if the number of arguments is not correct, print usage message and return error code */
	if (argc != array_index::ARGUMENT_NUM) {
		return usage_message(argv[array_index::PROGRAM_NAME]);
	}

	/* if the file cannot be opened, print error message and return error code */
	ifstream input(argv[array_index::INPUT_FILE_NAME]);
	if (!input.is_open()) {
		cout << "Cannot open file " << argv[array_index::INPUT_FILE_NAME] << endl;
		return return_code::FILE_OPEN_FAILURE_ERR;
	}

	/* horizontal and vertical dimensions */
	unsigned int dim_h, dim_v;

	/* continue trying to read the dimensions */
	while (int i = read_board_dimensions(input, dim_h, dim_v)) {

		/* if cannot read any line from the file, print error message and return error code */
		if (i == return_code::READ_LINE_FAILURE_ERR) {
			cout << "Failure to read a line in file " << argv[array_index::INPUT_FILE_NAME] << endl;
			return return_code::READ_LINE_FAILURE_ERR;
		}

		/* if cannot extract dimensions from this line, skip this line */
		if (i == return_code::EXTRACT_BOARD_DIMENSIONS_FAILURE_ERR) {
			continue;
		}

		/* if extracted the dimensions successfully, stop reading */
		if (i == return_code::SUCCESS) {
			break;
		}

	}

	vector<game_piece> game_pieces;

	/* initialize all the game pieces as empty squares */
	for (unsigned int i = 0; i != dim_h * dim_v; ++i) {
		game_pieces.push_back(game_piece(piece_color::no_color, "", " "));
	}

	/* read game pieces; if error code occurs, print error message and return that error code */
	if (read_game_pieces(input, game_pieces, dim_h, dim_v) == return_code::NO_WELL_FORMED_GAME_PIECE_ERR) {
		cout << "Failure to read a valid game piece in file " << argv[array_index::INPUT_FILE_NAME] << endl;
		return return_code::NO_WELL_FORMED_GAME_PIECE_ERR;
	}

	/* print game pieces; if error code occurs, print error message and return that error code */
	if (print_game_board(game_pieces, dim_h, dim_v) == return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR) {
		cout << "Failure to match the vector size to the board dimensions" << endl;
		return return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR;
	}

	cout << endl << endl;

	/* read neighbors of all pieces; return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR is guaranteed
	not happen here (already checked when printing the game pieces), so skip the check of this error code */
	print_neighbors(game_pieces, dim_h, dim_v);

	return return_code::SUCCESS;

}
