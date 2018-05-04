// ACStudio2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
#include<string>
#include<set>
#include<map>
#include<ostream>
#include<iterator>
#include<algorithm>

using namespace std;


int main()
{
	/*
	multiset<string> s;
	auto i1 = s.insert("a");
	cout << (i1 == --s.end()) << endl;
	auto i2 = s.insert("b");
	cout << (i2 == --s.end()) << endl;
	auto i3 = s.insert("a");
	cout << (i3 == --s.end()) << endl;

	for (auto e : s) {
		cout << e << " ";
	}
	cout << endl;
	*/

	/*
	multiset<string> s;

	for (int i = 0; i != 5; ++i) {
		s.insert("b");
	}

	s.insert("a");
	s.insert("c");

	ostream_iterator<string> strm(cout, " ");

	auto pos = s.equal_range("b");
	// copy(pos.first, pos.second, strm);

	// s.erase(pos.first);
	// s.erase(pos.first, pos.second);
	s.erase("b");
	auto pos2 = s.equal_range("b");
	copy(pos2.first, pos2.second, strm);
	*/

	multimap<string, int> m;

	m.insert({ "b", 1 });
	m.insert({ "b", 3 });
	m.insert({ "b", 5 });
	m.insert({ "b", 4 });
	m.insert({ "b", 2 });
	m.insert({ "a", 2 });
	m.insert({ "c", 2 });

	auto pos = m.equal_range("b");
	m.erase("b");

	auto pos2 = m.equal_range("b");
	for (; pos2.first != pos2.second; ++pos2.first) {
		cout << pos2.first->first << " " << pos2.first->second << endl;
	}

    return 0;
}

