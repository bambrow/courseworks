/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

This is the header file for ReversiGame class, common 
enumerations and function declarations.

***********************************************************/
#pragma once

#include "stdafx.h"
#include "game_pieces.h"
#include "game_base.h"

#ifndef reversi_game_h
#define reversi_game_h

#include <iostream>
#include <vector>
#include <string>
#include <utility>

using std::ostream;
using std::vector;

/* class of game */
class ReversiGame : public GameBase
{
public:
	/* friend */
	friend ostream& operator<<(ostream &, const ReversiGame&);
	/* constructor */
	ReversiGame(const string&, const string&);
	/* copy control */
	ReversiGame(const ReversiGame&) = delete;
	ReversiGame& operator=(const ReversiGame&) = delete;
	ReversiGame(ReversiGame&&) = delete;
	ReversiGame& operator=(ReversiGame&&) = delete;
	~ReversiGame() = default;
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
	const game_piece black_piece = game_piece(black, "black", "X");
	const game_piece white_piece = game_piece(white, "white", "O");
	const game_piece space = game_piece();
	piece_color current_color = black;
	/* helper methods */
	/* defalut game */
	void default_game(const string&, const string&);
	/* choose game piece according to color */
	const game_piece& pick_piece(piece_color);
	const game_piece& pick_reverse_piece(piece_color);
	/* choose game player according to color */
	const string& pick_player(piece_color);
	/* return the reverse color */
	piece_color get_reverse_color(piece_color);
	/* detect if there is any valid move for the given player */
	bool has_valid_move(piece_color color);
	/* count how many pieces on the board */
	unsigned int num_game_pieces();
	unsigned int num_game_pieces(piece_color);
	/* check if the piece is black_piece */
	bool is_black_piece(const unsigned int&, const unsigned int&);
	/* check if the piece is white_piece */
	bool is_white_piece(const unsigned int&, const unsigned int&);
	/* check if the place is space */
	bool is_space(const unsigned int&, const unsigned int&);
	/* check if two places are neighbors */
	bool is_neighbor(const unsigned int&, const unsigned int&);
	/* check if a place has neighbor of a certain color */
	bool has_neighbor(const unsigned int&, const unsigned int&, piece_color);
	bool has_neighbor(const unsigned int&, piece_color);
	/* check one direction of a place (space) and return if there is any valid play */
	bool check(const unsigned int&, const unsigned int&, piece_color, const int&, const int&);
	/* check all directions for a certain player */
	bool check_all(const unsigned int&, const unsigned int&, piece_color);
	/* flip the pieces of one direction */
	void flip(const unsigned int&, const unsigned int&, piece_color, const int&, const int&);
	/* flip all directions for a certain player */
	void flip_all(const unsigned int&, const unsigned int&, piece_color);
	/* method to save Reversi Game */
	void save_game();
};

/* insertion operator */
ostream& operator<<(ostream &, const ReversiGame&);

#endif
