/***********************************************************

Lab2: Nine Almonds Game
Author: Weiqiang Li

***********************************************************/

#pragma once

#include "stdafx.h"
#include "game_pieces.h"

#ifndef game_h
#define game_h

#include <vector>
#include <string>
#include <iostream>

using std::vector;
using std::string;
using std::ostream;

/* class of game */
class NineAlmondsGame
{
public:
	/* friend */
	friend ostream& operator<<(ostream &, const NineAlmondsGame &);
	/* constructor */
	NineAlmondsGame();
	/* game methods */
	/* detect if the game is successfully over */
	bool done();
	/* detect if the game is over, but not successfully over */
	bool stalemate();
	/* prompt the coordinate if valid */
	int prompt(unsigned int &, unsigned int &);
	/* handle a turn where user chooses a valid piece and makes a valid move */
	int turn();
	/* handle the game status */
	int play();
	/* auto play */
	int auto_play();
private:
	/* member variables */
	unsigned const int dim_x = 5;
	unsigned const int dim_y = 5;
	vector<game_piece> game_pieces;
	const game_piece almond = game_piece(brown, "almond", "A");
	const game_piece space = game_piece();
	/* helper methods */
	/* get the current index in the vector using the coordinate */
	unsigned int get_index(const unsigned int&, const unsigned int&) const;
	/* get the current game piece using the coordinate */
	const game_piece& get_game_piece(const unsigned int&, const unsigned int&);
	/* set the current game piece using the coordinate */
	NineAlmondsGame& set_game_piece(const unsigned int&, const unsigned int&, const game_piece&);
	/* count how many almonds on the board */
	unsigned int num_game_pieces();
	/* check if the piece is an almond */
	bool is_almond(const unsigned int&, const unsigned int&);
	/* check if two pieces are neighbors if they are both almonds */
	bool is_neighbor(const unsigned int&, const unsigned int&);
	/* check if a piece has neighbor if it is an almond */
	bool has_neighbor(const unsigned int&, const unsigned int&);
	bool has_neighbor(const unsigned int&);
	/* check if the second index is jumpable from the first index */
	bool is_jumpable(const unsigned int&, const unsigned int&);
	/* check if a piece is movable if it is an almond */
	bool is_movable(const unsigned int&, const unsigned int&);
	bool is_movable(const unsigned int&);

	/* helper functions for auto_play */
	/* store the current state, recursively test the next move, and generate the sequence string */
	bool generate_seq(vector<game_piece>&, string = "");
	/* find and store possible destinations for a piece */
	vector<unsigned int> find_path(const unsigned int&, const unsigned int&);
	/* check if the second coordinate is the valid destination for the first coordinate for a move*/
	bool find_move(const unsigned int&, const unsigned int&, const unsigned int&, const unsigned int&);
	/* move the piece */
	void move(const unsigned int&, const unsigned int&, const unsigned int&, const unsigned int&);
};

/* array index for command line arguments */
enum array_index
{
	PROGRAM_NAME,		/* argv[0] is always the program name */
	ARGUMENT_NAME,		/* argv[1] should be the name of argument */
	EXTRA_OPTION,		/* argv[2] should be the extra option */
	ARGUMENT_NUM		/* equivalent to argc, indicates there should be 2 arguments */
};

/* return code for Lab0.cpp */
enum return_code
{
	SUCCESS,					/* success run */
	ILLEGAL_NUM_OF_ARGS_ERR,	/* the value of argc is not correct */
	WRONG_ARGS_ERR,				/* the argument is not what is expected */
	USER_QUIT,					/* the user chooses to quit */
	NO_VALID_MOVE				/* there is no valid move on the board */
};

/* helper function to print the error message if command line argument is not correct */
void usage_message(const string &, const string &);

/* change all characters in a string to lower case */
void lower_case(string &);

/* trim the string; delete the whitespaces on the left and right end */
void trim(string &);

/* replace the commas in the string to whitespaces */
bool reform(string &);

/* insertion operator of the game */
ostream& operator<<(ostream &, const NineAlmondsGame &);

#endif
