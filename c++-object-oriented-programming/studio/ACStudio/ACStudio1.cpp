// ACStudio1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
#include<set>
#include<iterator>
#include<string>
#include<algorithm>

using namespace std;

bool compare(const string& s1, const string& s2) {
	return s1 > s2;
}

int main(int argc, char * argv[])
{
	/*
	set<string> s;
	copy(argv, argv + argc, inserter(s, s.begin()));
	ostream_iterator<string> strm(cout, " ");
	copy(s.begin(), s.end(), strm);
	*/

	/*
	set<char *> s;
	copy(argv, argv + argc, inserter(s, s.begin()));
	ostream_iterator<string> strm(cout, " ");
	copy(s.begin(), s.end(), strm);
	*/
	
	

	/*
	string s1 = "a"; 
	string s2 = "b";
	cout << (s1 < s2) << endl;
	*/

	
	char * c1 = "a";
	char * c2 = "a";
	cout << (c1 == c2) << endl;
	cout << (c1 > c2) << endl;
	cout << strcmp(c1, c2) << endl;
	

	/*
	set<string, decltype(compare)*> s(compare);
	copy(argv, argv + argc, inserter(s, s.begin()));
	ostream_iterator<string> strm(cout, " ");
	copy(s.begin(), s.end(), strm);
	*/

    return 0;
}

