/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#pragma once

#include "stdafx.h"
#include "game_pieces.h"
#include "game_base.h"

#ifndef checkers_h
#define checkers_h

#include <iostream>
#include <vector>
#include <string>
#include <utility>

using std::ostream;
using std::vector;

/* class of game */
class Checkers : public GameBase
{
public:
	/* friend */
	friend ostream& operator<<(ostream &, const Checkers&);
	/* constructor */
	Checkers(const string&, const string&);
	/* copy control */
	Checkers(const Checkers&) = delete;
	Checkers& operator=(const Checkers&) = delete;
	Checkers(Checkers&&) = delete;
	Checkers& operator=(Checkers&&) = delete;
	~Checkers() = default;
	/* game methods */
	/* print the board */
	virtual void print();
	/* detect if the game is over and one player wins */
	virtual bool done();
	/* detect if the game is over and neither of the players wins */
	virtual bool stalemate();
	/* handle a turn where either user chooses to make a valid move */
	virtual int turn();
	/* overload play() method */
	int play();
private:
	/* member variables */
	string black_player;
	string white_player;
	const game_piece black_pawn = game_piece(black, "black", "X");
	const game_piece white_pawn = game_piece(white, "white", "O");
	const game_piece black_king = game_piece(black, "black", "B");
	const game_piece white_king = game_piece(white, "white", "W");
	const game_piece space = game_piece();
	piece_color current_color;
	/* helper methods */
	/* defalut game */
	void default_game(const string&, const string&);
	/* choose game piece according to color */
	const game_piece& pick_pawn(piece_color);
	const game_piece& pick_reverse_pawn(piece_color);
	const game_piece& pick_king(piece_color);
	const game_piece& pick_reverse_king(piece_color);
	/* choose game player according to color */
	const string& pick_player(piece_color);
	/* return the reverse color */
	piece_color get_reverse_color(piece_color);
	/* detect if there is any valid move for the given player */
	bool has_valid_move(piece_color);
	/* count how many pieces on the board */
	unsigned int num_game_pieces();
	unsigned int num_game_pieces(piece_color);
	/* check if the piece is from certain color */
	bool is_current_piece(const unsigned int&, piece_color);
	bool is_reverse_piece(const unsigned int&, piece_color);
	/* check if the place is space */
	bool is_space(const unsigned int&, const unsigned int&);
	/* check if two places are neighbors */
	bool is_neighbor(const unsigned int&, const unsigned int&);
	/* check if a place has neighbor of a certain color */
	bool has_neighbor(const unsigned int&, const unsigned int&);
	bool has_neighbor(const unsigned int&);
	bool is_jumpable(const unsigned int&, const unsigned int&);
	bool is_movable_with_jump(const unsigned int&, const unsigned int&);
	bool is_movable_with_jump(const unsigned int&);
	bool is_movable_without_jump(const unsigned int&, const unsigned int&);
	bool is_movable_without_jump(const unsigned int&);
	void save_game();
};

/* insertion operator */
ostream& operator<<(ostream &, const Checkers&);

#endif
