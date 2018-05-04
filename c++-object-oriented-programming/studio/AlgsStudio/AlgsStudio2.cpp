// AlgsStudio2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<string>
#include<deque>
#include<vector>
#include<list>
#include<forward_list>
#include<iostream>
#include<iterator>
#include<algorithm>
#include<functional>

using namespace std;

struct compare_to {
	bool operator()(int x, int y) { 
		return x > y ? true : false;
	};
};


bool is_greater(const int& a, const int &b) {
	return a > b ? true : false;
}

int main()
{
	deque<int> d;
	list<int> l;
	forward_list<int> f;
	vector<int> v;

	for (int i = 1; i != 6; ++i) {
		d.push_back(i);
		l.push_back(i);
		f.push_front(i);
		v.push_back(i);
	}

	auto di = d.begin() + 2;
	auto li = l.begin(); ++li; ++li;
	auto fi = f.begin(); ++fi; ++fi;
	auto vi = v.begin() + 2;

	cout << "deque: " << *di << endl;
	cout << "list: " << *li << endl;
	cout << "forward_list: " << *fi << endl;
	cout << "vector: " << *vi << endl;

	ostream_iterator<int> strm(cout, " ");
	cout << endl << "deque, begin to end: " << endl;
	copy(d.begin(), d.end(), strm);
	cout << endl << "deque, di to di: " << endl;
	copy(di, di, strm);
	cout << endl;

	cout << endl << "forward_list, begin to end: " << endl;
	copy(f.begin(), f.end(), strm);
	cout << endl << "forward_list, fi to fi: " << endl;
	copy(fi, fi, strm);
	cout << endl;

	cout << endl;
	int arr[] = { -2, 19, 80, -47, 80, 80, -2 };
	copy(arr, end(arr), strm);
	cout << endl;
	sort(arr, end(arr), greater<int>());
	cout << endl << "after using greater: " << endl;
	copy(arr, end(arr), strm);
	cout << endl;
	sort(arr, end(arr), is_greater);
	cout << endl << "self-write function: " << endl;
	copy(arr, end(arr), strm);
	cout << endl;
	sort(arr, end(arr), compare_to());
	cout << endl << "self-write struct: " << endl;
	copy(arr, end(arr), strm);
	cout << endl;


    return 0;
}



