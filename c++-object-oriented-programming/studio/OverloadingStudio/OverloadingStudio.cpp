// OverloadingStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class A {
	friend ostream& operator<< (ostream& os, const A& a);
	friend bool operator<(const A& a1, const A& a2);
	friend bool operator==(const A& a1, const A& a2);
public:
	A(int i) : a(i) {}
	A() : A(0) {}
	// A() = default;
	A operator+(const A& that) const {
		return A(a + that.a);
	}
	A operator=(const A& that) {
		a = that.a;
		return that;
	}
private:
	int a;
};

ostream& operator<<(ostream& os, const A& a) {
	os << a.a << " ";
	return os;
}

bool operator<(const A& a1, const A& a2) {
	return a1.a < a2.a;
}

bool operator==(const A& a1, const A& a2) {
	return a1.a == a2.a;
}


int main()
{
	
	/*
	A a1 = A(1);
	A a2 = A(2);
	A a3 = A(3);
	A a4 = A(1);

	cout << (a1 < a2) << " " << (a2 < a3) << " " << (a3 < a4) << endl;
	cout << (a1 == a4) << " " << (a2 == a4) << endl;
	a2 = a1;
	cout << a1 << a2 << endl;
	a1 = a2 = a3;
	cout << a1 << a2 << a3 << endl;
	cout << (a1 + a2 + a3) << endl;
	a1 = a1 + a2 + a3;
	cout << a1 << endl;
	*/

	
	const A a1 = A(1);
	const A a2 = A(2);
	const A a3 = A(3);
	const A a4 = A(4);

	vector<A> v;
	v.push_back(a2);
	v.push_back(a4);
	v.push_back(a3);
	v.push_back(a1);

	cout << "before sorting: ";
	for (auto a : v) {
		cout << a;
	}
	cout << endl;

	sort(v.begin(), v.end());

	cout << "after sorting: ";
	for (auto a : v) {
		cout << a;
	}
	cout << endl;
	

    return 0;
}

