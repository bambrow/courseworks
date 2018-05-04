
#include "stdafx.h"
#include "studio.h"
#include <iostream>

using namespace std;

Studio::Studio() {
	cout << "Studio constructor" << endl;
}

Studio::~Studio() {
	cout << "Studio destructor" << endl;
}

void Studio::print() {
	cout << "Studio print" << endl;
}

Studio2::Studio2() {
	cout << "Studio2 constructor" << endl;
}

Studio2::~Studio2() {
	cout << "Studio2 destructor" << endl;
}

void Studio2::print() {
	cout << "Studio2 print" << endl;
}
