/***********************************************************

Lab1: Game Boards and Pieces
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "game_pieces.h"
#include "common.h"
#include <string>

using namespace std;

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
		case red:
			return "red";
		case black:
			return "black";
		case white:
			return "white";
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

	if (color == "red") {
		return red;
	}
	else if (color == "black") {
		return black;
	}
	else if (color == "white") {
		return white;
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
