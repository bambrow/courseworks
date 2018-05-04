// TempStudio2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
#include<array>
#include<list>
#include "template.h"

using namespace std;

int main()
{

	int arr[] = { 1,2,3,4 };
	char arr2[] = { 'h','e','l','l','o',' ','w','o','r','l','d','!' };
	list<int> li = { 1,2,3,4,5,6,7 };

	cout << count_func(begin(arr), end(arr)) << endl;
	cout << endl;
	cout << count_func(begin(arr2), end(arr2)) << endl;
	cout << endl;
	cout << count_func(li.begin(), li.end()) << endl;

	// cout << li.end() - li.begin() << endl;

    return 0;
}

