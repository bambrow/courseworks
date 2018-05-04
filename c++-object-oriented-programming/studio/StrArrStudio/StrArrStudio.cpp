// StrArrStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <cstddef>
#include <vector>
#include <sstream>
#include <fstream>

using namespace std;

int main(int argc, char * argv[])
{
	/*
	unsigned int arr[2][3][5] = { 0 };
	for (size_t i = 0; i != 2; ++i) {
		for (size_t j = 0; j != 3; ++j) {
			for (size_t k = 0; k != 5; ++k) {
				arr[i][j][k] = i * j * k;
			}
		}
	}

	for (size_t i = 0; i != 2; ++i) {
		for (size_t j = 0; j != 3; ++j) {
			for (size_t k = 0; k != 5; ++k) {
				cout << "arr[" << i << "][" << j << "][" << k << "] = " << arr[i][j][k] << endl;
			}
		}
	}
	*/

	/*
	vector<string> vec;
	for (int i = 0; i != argc; ++i) {
		vec.push_back(argv[i]);
	}
	for (auto p = vec.begin(); p != vec.end(); ++p) {
		cout << *p << endl;
	}
	*/

	/*
	string str, arg;
	for (int i = 0; i != argc; ++i) {
		str += argv[i];
	}
	istringstream record(str);
	while (record >> arg) {
		cout << arg << endl;
	}
	*/

	if (argc == 3) {
		ifstream input(argv[1]);
		if (!input.is_open()) {
			cout << "cannot read file" << argv[1] << endl;
			return 2;
		}
		ofstream output(argv[2]);
		if (!output.is_open()) {
			cout << "cannot write file" << argv[2] << endl;
			return 3;
		}
		string str;
		while (!input.eof()) {
			input >> str;
			output << str;
		}

 		return 0;
	}
	else {
		cout << argv[0] << endl;
		cout << "please run the program with two additional arguments" << endl;
		return 1;
	}

    
}

