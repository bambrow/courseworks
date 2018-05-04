// DataStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char * argv[])
{
	// cout << argv[0] << endl;
	
	for (auto c = &argv[0][0]; *c != '\0'; ++c) {
		cout << *c << endl;
	}
	
	return 0;

}

