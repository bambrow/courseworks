#include "stdafx.h"
#include "detector.h"
#include <iostream>

using namespace std;

unsigned int detector::x_static = 0;

detector::detector() {
	x = x_static++;
	cout << "detector() " << this << " " << x << endl;
}

detector::~detector() {
	cout << "~detector() " << this << " " << x << endl;
}

int detector::get_x() {
	return x;
}
