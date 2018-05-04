/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

This is the header file contains some protected member variables 
and public function declarations that are common to multiple games.

***********************************************************/
#pragma once

#include "stdafx.h"
#include "game_pieces.h"

#ifndef game_base_h
#define game_base_h

#include <memory>
#include <iostream>
#include <string>
#include <vector>

using std::ostream;
using std::vector;
using std::shared_ptr;

class GameBase {
public:
	/* friend */
	friend ostream& operator<<(ostream &, const GameBase &);
	/* constructor */
	GameBase(unsigned int x, unsigned int y, unsigned int l) 
		: dim_x(x), dim_y(y), max_len(l) { }
	/* copy control */
	GameBase(const GameBase&) = delete;
	GameBase& operator=(const GameBase&) = delete;
	GameBase(GameBase&&) = delete;
	GameBase& operator=(GameBase&&) = delete;
	virtual ~GameBase() = default;
	/* start the proper game using the given inputs */
	static void start(int argc, char * argv[]);
	/* generate smart pointer for game */
	static shared_ptr<GameBase> instance();
	/* virtual methods */
	virtual void print() = 0;
	virtual bool done() = 0;
	virtual bool stalemate() = 0;
	/* prompt the coordinate if valid */
	virtual int prompt(unsigned int &, unsigned int &);
	/* virtual methods */
	virtual int turn() = 0;
	virtual int play();
protected:
	/* member variables */
	unsigned int dim_x;
	unsigned int dim_y;
	unsigned int max_len;
	unsigned int turns = 0;
	vector<game_piece> game_pieces;
	static shared_ptr<GameBase> game_ptr;

	/* helper methods */
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