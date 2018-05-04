// ClassesStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "ClassesStudio.h"
#include <iostream>
#include <string>

using namespace std;

int main()
{
	/*
	IntVariables iv;
	const IntVariables civ;
	
	cout << "iv: var1: " << iv.get_var1() << "; var2: " << iv.get_var2() << endl;
	cout << "civ: var1: " << civ.get_var1() << "; var2: " << civ.get_var2() << endl;

	iv.set_var1(1);
	iv.set_var2(2);
	// civ.set_var1(1);
	// civ.set_var2(2);

	cout << "iv mutated" << endl;
	cout << "iv: var1: " << iv.get_var1() << "; var2: " << iv.get_var2() << endl;
	*/

	IntVariables iv;
	cout << "iv.var1 = " << iv.get_var1() << " and iv.var2 = " << iv.get_var2() << endl;
	iv.set_var1(7).set_var2(3);
	cout << "iv.var1 = " << iv.get_var1() << " and iv.var2 = " << iv.get_var2() << endl;
	IntVariables iv2(iv);
	cout << "iv2.var1 = " << iv2.get_var1() << " and iv2.var2 = " << iv2.get_var2() << endl;
	iv2.set_var1(1).set_var2(2);
	cout << "iv.var1 = " << iv.get_var1() << " and iv.var2 = " << iv.get_var2() << endl;

    return 0;
}

