// CCStudio1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "detector.h"

#include <iostream>

using namespace std;

class wrapper {
	detector* d;
public:
	wrapper() : d(0) {
		cout << "wrapper() " << this << endl;
		d = new detector();
	}
	~wrapper() {
		cout << "~wrapper() " << this << endl;
		delete d;
	}
	wrapper(const wrapper& w) {
		cout << "wrapper() " << this << endl;
		d = new detector(*w.d);
	}
};

int main()
{

	wrapper w1, w2(w1);

    return 0;
}

