// AlgsStudio1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <algorithm>
#include <iterator>
#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char* argv[])
{
	int arr[] = { -2, 19, 80, -47, 80, 80, -2 };
	int* arr_end = end(arr);
	vector<int> vec;
	ostream_iterator<int> strm(cout, " ");
	copy(begin(arr), arr_end, strm);
	cout << endl;

	back_insert_iterator<vector<int>> back_iter = back_inserter(vec);
	copy(begin(arr), arr_end, back_iter);
	copy(vec.begin(), vec.end(), strm);
	cout << endl;

	sort(begin(arr), end(arr));
	cout << "array after sorting: " << endl;
	copy(begin(arr), arr_end, strm);
	cout << endl;

	sort(vec.begin(), vec.end());
	cout << "vector after sorting: " << endl;
	copy(vec.begin(), vec.end(), strm);
	cout << endl << endl;

	vector<int>::iterator res1 = adjacent_find(vec.begin(), vec.end());
	vector<int>::iterator res2 = adjacent_find(res1 + 1, vec.end());
	vector<int>::iterator res3 = adjacent_find(res2, vec.end());
	copy(vec.begin(), res1, strm);
	cout << endl;
	copy(res1, res2, strm);
	cout << endl;
	copy(res2, res3, strm);
	cout << endl;
	copy(res3, vec.end(), strm);
	cout << endl;


	return 0;
}

