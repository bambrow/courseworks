/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

this is the source file for common helper functions.

***********************************************************/
#include "stdafx.h"
#include "game.h"
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <iterator>
#include <sstream>
#include <iomanip>

using namespace std;

/***********************************************************

Print the error message when the command line argument is
not correct.

Input:
const string & program_name
the reference to the name of program

***********************************************************/
void usage_message(const string & program_name, const string & option) {
	/* print out the usage message */
	cout << "Usage: " << program_name << " " << option << endl;
}

/***********************************************************

Change all characters in a string to lower case.

Input:
string & str
the reference to the string

***********************************************************/
void lower_case(string & str) {
	for (auto &c : str) {
		c = tolower(c);
	}
}

/***********************************************************

Trim the string; delete the whitespaces on the left and
right end.

Input:
string & str
the reference to the string

***********************************************************/
void trim(string & str) {
	if (str.empty()) return;
	string::const_iterator it = str.begin();
	string::const_reverse_iterator rit = str.rbegin();
	while (it != str.end() && isspace(*it)) {
		++it;
	}
	while (rit.base() != it && isspace(*rit)) {
		++rit;
	}
	str = string(it, rit.base());
}

/***********************************************************

Replace the commas in the string to whitespaces.

Input:
string & str
the reference to the string

Return:
true if at least one replacement happens

***********************************************************/
bool reform(string & str) {
	for (auto &c : str) {
		if (c == ',') {
			c = ' ';
			return true;
		}
	}
	return false;
}

/***********************************************************

Change char* to int.

Input:
char* ch
characters

Return:
i
the int from the characters

***********************************************************/
int char_to_int(char * ch) {
	istringstream strm(ch);
	int i;
	strm >> i;
	return i;
}
