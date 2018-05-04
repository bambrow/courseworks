/***********************************************************

Lab1: Game Boards and Pieces
Author: Weiqiang Li

***********************************************************/

#pragma once

#ifndef game_pieces_h
#define game_pieces_h

#include <string>

using std::string;

/* colors of game pieces */
enum piece_color 
{
	no_color,
	invalid,
	red,
	black,
	white
};

/* struct of game piece */
struct game_piece 
{
public:
	/* constructors */
	game_piece() = default;
	game_piece(const piece_color c, const string& n, const string& d) : color(c), name(n), display(d) {}
	/* getters */
	piece_color get_color() const { return color; }
	string get_name() const { return name; }
	string get_display() const { return display; }
	/* setters */
	game_piece& set_color(const piece_color c) { color = c; return *this; }
	game_piece& set_name(const string& n) { name = n; return *this; }
	game_piece& set_display(const string& d) { display = d; return *this; }
private:
	piece_color color;		/* color of the game piece */
	string name;			/* the name of the piece */
	string display;			/* how the piece should be displayed */
};

/* read the value of piece_color and return a all lowercase string corresponting to that color */
string value_to_color(piece_color);

/* read the string return the enum value in piece_color corresonding to that string */
piece_color color_to_value(string);

/* check if a given string only contains whitespace */
bool all_whitespace(const string &);

#endif
