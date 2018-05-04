/***********************************************************

Lab3: Multiple Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#pragma once

#include "stdafx.h"
#include "game_pieces.h"

#ifndef game_base_h
#define game_base_h

#include <iostream>
#include <string>
#include <vector>

using std::ostream;
using std::vector;

class GameBase {
public:
	/* friend */
	friend ostream& operator<<(ostream &, const GameBase &);
	/* start the proper game using the given inputs */
	static GameBase* start(int argc, char * argv[]);
	/* virtual methods */
	virtual void print() = 0;
	virtual bool done() = 0;
	virtual bool stalemate() = 0;
	/* prompt the coordinate if valid */
	virtual int prompt(unsigned int &, unsigned int &);
	virtual int turn() = 0;
	virtual int play();
protected:
	/* constructor */
	GameBase(unsigned int x, unsigned int y, unsigned int l) : dim_x(x), dim_y(y), max_len(l) {};
	/* member variables */
	unsigned int dim_x;
	unsigned int dim_y;
	unsigned int max_len;
	vector<game_piece> game_pieces;
	/* methods */
	/* get the current index in the vector using the coordinate */
	virtual unsigned int get_index(const unsigned int&, const unsigned int&) const;
	/* get the current game piece using the coordinate */
	virtual const game_piece& get_game_piece(const unsigned int&, const unsigned int&);
	/* set the current game piece using the coordinate */
	virtual void set_game_piece(const unsigned int&, const unsigned int&, const game_piece&);
};

/* insertion operator of the game */
ostream& operator<<(ostream &, const GameBase &);

#endif