/***********************************************************

Lab1: Game Boards and Pieces
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "game_board.h"
#include "game_pieces.h"
#include "common.h"
#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;

/***********************************************************

Read board dimensions, two integers seperated by spaces, 
from the given input file stream.

Input:
ifstream & input
the reference to the input file stream
unsigned int & dim_h
the horizontal dimension of the board
unsigned int & dim_v
the vertical dimension of the board

Return:
return_code::SUCCESS
the function runs successfully
return_code::READ_LINE_FAILURE_ERR
the function cannot get a line from the input file stream
return_code::EXTRACT_BOARD_DIMENSIONS_FAILURE_ERR
the function cannot extract two integers from the line

***********************************************************/
int read_board_dimensions(ifstream & input, unsigned int & dim_h, unsigned int & dim_v) {

	string dimensions;
	/* if cannot get a line, return error code*/
	if (!getline(input, dimensions)) {
		return return_code::READ_LINE_FAILURE_ERR;
	}

	istringstream strm(dimensions);
	/* if cannot extract two integers from the given line, return error code */
	if (!((strm >> dim_h) && (strm >> dim_v))) {
		return return_code::EXTRACT_BOARD_DIMENSIONS_FAILURE_ERR;
	}

	/* here, the two integers have already been extracted and stored */
	return return_code::SUCCESS;
}

/***********************************************************

Read reasonable game pieces from the given input file stream 
and store them in the vector.

Input:
ifstream & input
the reference to the input file stream
vector<game_piece> & game_pieces
the vector to store all game pieces
unsigned int dim_h
the horizontal dimension of the board
unsigned int dim_v
the vertical dimension of the board

Return:
return_code::SUCCESS
the function runs successfully
return_code::NO_WELL_FORMED_GAME_PIECE_ERR
the function cannot read a single well-formed game piece

***********************************************************/
int read_game_pieces(ifstream & input, vector<game_piece> & game_pieces, 
	unsigned int dim_h, unsigned int dim_v) {

	string line;
	/* set extraction_status to false and check it at the end of function */
	bool extraction_status = false;

	/* continue to get the next line, if any */
	while (getline(input, line)) {

		istringstream strm(line);

		/* prepare to extract color, name, display, horizontal position and vertical position*/
		string color_str, name, display;
		unsigned int pos_h, pos_v;

		/* if any of these extractions fail, ignore this line */
		if (!((strm >> color_str) && (strm >> name) && (strm >> display)
			&& (strm >> pos_h) && (strm >> pos_v))) {
			continue;
		}
		
		piece_color color = color_to_value(color_str);
		/* if the color is invalid, or the coordinate is greater than the dimension, ignore this line */
		if (color == invalid || pos_h >= dim_h || pos_v >= dim_v) {
			continue;
		}

		/* calculate the index in the vector */
		unsigned int index = dim_h * pos_v + pos_h;
		/* set the color, name and display */
		game_pieces[index].set_color(color).set_name(name).set_display(display);
		/* set extraction_status to true, indicating there is at least one successful extraction */
		extraction_status = true;
	}

	/* if there is no successful extraction, return error code */
	if (!extraction_status) {
		return return_code::NO_WELL_FORMED_GAME_PIECE_ERR;
	}

	return return_code::SUCCESS;
}

/***********************************************************

Print the game board with every game piece in its right 
position.

Input:
vector<game_piece> & game_pieces
the vector to store all game pieces
unsigned int dim_h
the horizontal dimension of the board
unsigned int dim_v
the vertical dimension of the board

Return:
return_code::SUCCESS
the function runs successfully
return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR
the vector size does not match the dimensions

***********************************************************/
int print_game_board(vector<game_piece> & game_pieces, unsigned int dim_h, unsigned int dim_v) {
	
	/* if the vector size does not match the dimensions, return error code */
	if (dim_h * dim_v != game_pieces.size()) {
		return return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR;
	}

	/* print from the top line backwards, the last line of game pieces are printed first */
	for (unsigned int i = dim_v - 1; i != -1; --i) {
		for (unsigned int j = 0; j != dim_h; ++j) {
			cout << game_pieces[dim_h * i + j].get_display() << " ";
		}
		cout << endl;
	}

	return return_code::SUCCESS;

}

/***********************************************************

Print the neighbors of all game pieces.

Input:
vector<game_piece> & game_pieces
the vector to store all game pieces
unsigned int dim_h
the horizontal dimension of the board
unsigned int dim_v
the vertical dimension of the board

Return:
return_code::SUCCESS
the function runs successfully
return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR
the vector size does not match the dimensions

***********************************************************/
int print_neighbors(vector<game_piece> & game_pieces, unsigned int dim_h, unsigned int dim_v) {

	/* if the vector size does not match the dimensions, return error code */
	if (dim_h * dim_v != game_pieces.size()) {
		return return_code::VECTOR_SIZE_DOES_NOT_MATCH_DIMENSIONS_ERR;
	}

	/* for all game pieces */
	for (unsigned int i = 0; i != dim_v; ++i) {
		for (unsigned int j = 0; j != dim_h; ++j) {

			/* calculate the index */
			const unsigned int index = dim_h * i + j;
			/* if there is nothing in this piece, jump to the next piece */
			if (game_pieces[index].get_color() == piece_color::no_color 
				|| game_pieces[index].get_name() == ""
				|| game_pieces[index].get_display() == " ") {
				continue;
			}

			/* prepare the inner loop bounds */
			unsigned int min_v, max_v, min_h, max_h;

			/* vertical lower bound */
			if (i == 0) {
				min_v = 0;
			}
			else {
				min_v = i - 1;
			}

			/* vertical higher bound */
			if (i == dim_v - 1) {
				max_v = dim_v - 1;
			}
			else {
				max_v = i + 1;
			}

			/* horizontal lower bound */
			if (j == 0) {
				min_h = 0;
			}
			else {
				min_h = j - 1;
			}

			/* horizontal higher bound */
			if (j == dim_h - 1) {
				max_h = dim_h - 1;
			}
			else {
				max_h = j + 1;
			}

			/* print the current piece */
			cout << i << "," << j << " " << value_to_color(game_pieces[index].get_color()) << " "
				<< game_pieces[index].get_name() << ": ";

			/* for better printing; if this is the first neighbor to be printed, no semicolon before */
			bool print_without_semicolon = true;

			/* loop through all neighbors using the inner bounds */
			for (unsigned int ii = min_v; ii != max_v + 1; ++ii) {
				for (unsigned int ij = min_h; ij != max_h + 1; ++ij) {

					/* skip the piece itself */
					if (ii == i && ij == j) {
						continue;
					}

					/* calculate the index */
					const unsigned int iindex = dim_h * ii + ij;
					/* if there is nothing in this neighbor, jump to the next neighbor */
					if (game_pieces[iindex].get_color() == piece_color::no_color 
						|| game_pieces[iindex].get_name() == ""
						|| game_pieces[iindex].get_display() == " ") {
						continue;
					}

					/* for better printing; determine if there should be a semicolon before */
					if (print_without_semicolon) {
						print_without_semicolon = false;
					}
					else {
						cout << "; ";
					}

					/* print the neighbor */
					cout << ii << "," << ij << " " << value_to_color(game_pieces[iindex].get_color()) << " "
						<< game_pieces[iindex].get_name();

				}
			}

			cout << endl;

		}
	}

	return return_code::SUCCESS;

}
