/***********************************************************

Lab2: Nine Almonds Game
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "game_pieces.h"
#include "game.h"
#include <string>

using namespace std;

/***********************************************************

== operator.

Input:
const game_piece & l, const game_piece & r
two game_pieces to be compared

Return:
true if they have same color, display and name

***********************************************************/
bool operator==(const game_piece & l, const game_piece & r) {
	return l.get_color() == r.get_color() &&
		l.get_display() == r.get_display() &&
		l.get_name() == r.get_name();
}

/***********************************************************

!= operator.

Input:
const game_piece & l, const game_piece & r
two game_pieces to be compared

Return:
true if == returns false

***********************************************************/
bool operator!=(const game_piece & l, const game_piece & r) {
	return !(l == r);
}

/***********************************************************

Read the value of piece_color and return a all lowercase
string corresponting to that color.

Input:
piece_color color
the color in piece_color enum

Return:
an all-lowercase string corresponding to the enum color

***********************************************************/
string value_to_color(piece_color color) {

	switch (color) {

	case no_color:
		return "no color";
	case brown:
		return "brown";
	default:
		return "invalid";

	}

}

/***********************************************************

Read the value of piece_color and return a all lowercase
string corresponting to that color.

Input:
string color
the color string

Return:
an enum color index corresponding to the string

***********************************************************/
piece_color color_to_value(string color) {

	if (color == "brown") {
		return brown;
	}
	/* if all consisting of white spaces, return no_color */
	else if (all_whitespace(color)) {
		return no_color;
	}
	else {
		return invalid;
	}

}

/***********************************************************

Check if a given string only contains whitespace.

Input:
string & str
the reference to the string

Return:
true if the string contains only whitespace, including space,
tab, vertical tab, return, newline, or formfeed

***********************************************************/
bool all_whitespace(const string & str) {
	for (auto &c : str) {
		if (!isspace(c)) {
			return false;
		}
	}
	return true;
}
