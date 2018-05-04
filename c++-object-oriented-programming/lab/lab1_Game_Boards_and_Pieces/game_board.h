/***********************************************************

Lab1: Game Boards and Pieces
Author: Weiqiang Li

***********************************************************/

#pragma once

#ifndef game_board_h
#define game_board_h

#include <string>
#include <fstream>
#include <vector>
#include "game_pieces.h"

using std::string;
using std::ifstream;
using std::vector;

/* read board dimensions, two integers seperated by spaces, from the given input file stream */
int read_board_dimensions(ifstream &, unsigned int &, unsigned int &);

/* read reasonable game pieces from the given input file stream and store them in the vector */
int read_game_pieces(ifstream &, vector<game_piece> &, unsigned int, unsigned int);

/* print the game board with every game piece in its right position */
int print_game_board(vector<game_piece> &, unsigned int, unsigned int);

/* print the neighbors of all game pieces */
int print_neighbors(vector<game_piece> &, unsigned int, unsigned int);

#endif
