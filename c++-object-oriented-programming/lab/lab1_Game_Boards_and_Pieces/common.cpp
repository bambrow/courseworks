/***********************************************************

Lab1: Game Boards and Pieces
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "common.h"
#include <vector>
#include <string>
#include <fstream>
#include <iostream>

using namespace std;

/***********************************************************

Print the error message when the number of command line
arguments is not correct.

Input:
const string & program_name
the reference to the name of program

Return:
return_code::ILLEGAL_NUM_OF_ARGS_ERR
the number of arguments is not correct

***********************************************************/
int usage_message(const string & program_name) {

	/* print out the usage message */
	cout << "Usage: " << program_name << " <input_file_name>" << endl;

	return return_code::ILLEGAL_NUM_OF_ARGS_ERR;

}

/***********************************************************

Parse the input file and read all its contents to a vector.
If the file cannot be opened, error code will be returned.

Input:
vector<string> & string_vector
the reference to the vector where the strings to be stored
char* file_name
the name of input file

Return:
return_code::SUCCESS
the function runs successfully
return_code::FILE_OPEN_FAILURE_ERR
the input file cannot be opened

***********************************************************/
int parse_file(vector<string> & string_vector, char* file_name) {

	ifstream input(file_name);

	/* return error code if the file cannot be opened */
	if (!input.is_open()) {
		cout << "Cannot open file " << file_name << "." << endl;
		return return_code::FILE_OPEN_FAILURE_ERR;
	}

	string word;

	while (input >> word) {

		/* pass empty strings */
		if (word.empty()) {
			continue;
		}

		/* store non-empty strings in the vector */
		string_vector.push_back(word);

	}

	return return_code::SUCCESS;

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
