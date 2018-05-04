/***********************************************************

Lab0: Basic C++ Program Structure and Data Movement
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "Lab0.h"
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <sstream>

using namespace std;

/***********************************************************

Parse the input file and read all its contents to a vector.
If the file cannot be opened, error code will be returned.

Input:	
		vector<string> & string_vector
				the vector where the strings to be stored
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

Print the error message when the number of command line arguments is not correct.

Input:
		const string & program_name
				the name of program

Return:
		return_code::ILLEGAL_NUM_OF_ARGS_ERR
				the number of arguments is not correct

***********************************************************/
int usage_message(const string & program_name) {

	/* print out the usage message */
	cout << "Usage: " << program_name << " <input_file_name>" << endl;

	return return_code::ILLEGAL_NUM_OF_ARGS_ERR;

}

int main(int argc, char * argv[])
{

	/* if the number of arguments is not correct, print usage message and return error code */
	if (argc != array_index::ARGUMENT_NUM) {
		return usage_message(argv[array_index::PROGRAM_NAME]);
	}

	vector<string> string_vector;

	/* store the return value of parse_file function */
	int parse_file_return_value = parse_file(string_vector, 
		argv[array_index::INPUT_FILE_NAME]);

	/* if the return value is not SUCCESS, return that error code */
	if (parse_file_return_value != return_code::SUCCESS) {
		return parse_file_return_value;
	}

	vector<int> int_vector;

	/* loop through the string vector */
	for (auto string_vector_iter = string_vector.begin(); 
		string_vector_iter != string_vector.end(); ++string_vector_iter) {

		/* boolean value indicates if the string only contains numeric digits */
		bool numeric_string = true;

		/* loop through each string in string vector */
		for (auto string_iter = string_vector_iter->begin();
			string_iter != string_vector_iter->end(); ++string_iter) {

			/* if the string contains non-digit, print it out and set numeric_string to false */
			if (!isdigit(*string_iter)) {
				cout << *string_vector_iter << endl;
				numeric_string = false;
				break;
			}

		}

		/* if the string contains only numeric digits, convert it to integer and store it into int vector */
		if (numeric_string) {
			istringstream strm(*string_vector_iter);
			int converted_integer;
			strm >> converted_integer;
			int_vector.push_back(converted_integer);
		}

	}

	/* loop though the int vector and print out every integer inside */
	for (auto int_vector_iter = int_vector.begin();
		int_vector_iter != int_vector.end(); ++int_vector_iter) {

		cout << *int_vector_iter << endl;

	}

    return return_code::SUCCESS;

}

