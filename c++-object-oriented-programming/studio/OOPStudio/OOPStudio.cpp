// OOPStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "studio.h"
#include <iostream>

using namespace std;

void pass_by_reference(Studio& s) {
	s.print();
}

void pass_by_value(Studio s) {
	s.print();
}

void pass_by_value(Studio* s) {
	s->print();
}


int main()
{

	/*
	Studio s;
	Studio2 t;

	cout << endl;
	*/

    /*
	Studio& rs1 = s;
	Studio& rs2 = t;
	Studio2& rt = t;
	*/

	/*
	Studio* rs1 = new Studio;
	cout << endl;
	Studio* rs2 = new Studio2;
	cout << endl;
	Studio2* rt = new Studio2;
	cout << endl;

	delete rs1;
	cout << endl;
	delete rs2;
	cout << endl;
	delete rt;

	cout << endl;
	*/

	/*
	cout << "rs1: ";
	rs1->print();

	cout << "rs2: ";
	rs2->print();

	cout << "rt: ";
	rt->print();
	*/

	Studio s;
	Studio2 t;

	Studio& rs1 = s;
	Studio& rs2 = t;
	Studio2& rt = t;

	Studio* ps1 = new Studio;
	Studio* ps2 = new Studio2;
	Studio2* pt = new Studio2;

	cout << endl;
	pass_by_reference(rs1);
	cout << endl;
	pass_by_reference(rs2);
	cout << endl;
	pass_by_reference(rt);
	cout << endl;

	pass_by_value(ps1);
	cout << endl;
	pass_by_value(ps2);
	cout << endl;
	pass_by_value(pt);
	cout << endl;

	pass_by_value(rs1);
	cout << endl;
	pass_by_value(rs2);
	cout << endl;
	pass_by_value(rt);
	cout << endl;

    return 0;
}

