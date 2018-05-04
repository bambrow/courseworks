#include "stdafx.h"
#include "detector.h"
#include <iostream>

using namespace std;

unsigned int detector::x_static = 0;

detector::detector() {
	x = ++x_static;
	cout << "detector() " << this << " " << x << endl;
}

detector::detector(const detector & d) {
	x = ++x_static;
	cout << "detector(const detector&) " << this << " " << x << endl;
}

detector::~detector() {
	cout << "~detector() " << this << " " << x << endl;
	--x_static;
}

