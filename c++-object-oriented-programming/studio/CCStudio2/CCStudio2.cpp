// CCStudio2.cpp : Defines the entry point for the console application.
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
		cout << "number of detector: " << detector::x_static << endl;
	}
	~wrapper() {
		cout << "~wrapper() " << this << endl;
		delete d;
	}

	wrapper(const wrapper& w) {
		cout << "wrapper(const wrapper&) " << this << endl;
		d = new detector(*w.d);
		cout << "number of detector: " << detector::x_static << endl;
	}
	wrapper& operator=(const wrapper& w) {
		wrapper temp(w);
		swap(*this, temp);
		return *this;
	}

	wrapper(wrapper &&w) : d(move(w.d)) {
		w.d = nullptr;
		cout << "number of detector: " << detector::x_static << endl;
	}

	wrapper &wrapper:: operator=(wrapper &&w) {
		if (&w != this) {
			delete d;
			d = w.d;
			w.d = nullptr;
		}
		return *this;
	}

};


int main()
{
	/*
	int a = 12;
	int b = 33;
	int& la = a;
	int& lb = b;
	int&& ra = move(a);
	int&& rb = move(b);

	int c = ra;
	int d = rb;

	cout << "a: " << a << endl
		<< "b: " << b << endl
		<< "la: " << la << endl
		<< "lb: " << lb << endl
		<< "ra: " << ra << endl
		<< "rb: " << rb << endl
		<< "c: " << c << endl
		<< "d: " << d << endl;
	*/

	cout << "create w1" << endl;
	wrapper w1;

	
	cout << "create w2" << endl;
	wrapper w2(w1);

	cout << "create w3" << endl;
	wrapper w3 = w1;
	

	cout << "create w4" << endl;
	wrapper w4(move(w1));

	cout << "create w5" << endl;
	wrapper w5 = move(w1);

    return 0;
}

