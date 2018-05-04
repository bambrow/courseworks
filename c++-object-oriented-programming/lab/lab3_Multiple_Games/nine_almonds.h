/***********************************************************

Lab3: Multiple Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#pragma once

#include "stdafx.h"
#include "game_pieces.h"
#include "game_base.h"

#ifndef nine_almonds_h
#define nine_almonds_h

#include <iostream>
#include <vector>
#include <string>

using std::ostream;
using std::vector;

/* class of game */
class NineAlmondsGame : public GameBase
{
public:
	/* friend */
	friend ostream& operator<<(ostream &, const NineAlmondsGame &);
	/* constructor */
	NineAlmondsGame();
	/* game methods */
	/* print the board */
	virtual void print();
	/* detect if the game is successfully over */
	virtual bool done();
	/* detect if the game is over, but not successfully over */
	virtual bool stalemate();
	/* handle a turn where user chooses a valid piece and makes a valid move */
	virtual int turn();

	/* auto play */
	int auto_play();
private:
	/* member variables */
	const game_piece almond = game_piece(brown, "almond", "A");
	const game_piece space = game_piece();
	/* helper methods */
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

/* insertion operator of the game */
ostream& operator<<(ostream &, const NineAlmondsGame &);

#endif