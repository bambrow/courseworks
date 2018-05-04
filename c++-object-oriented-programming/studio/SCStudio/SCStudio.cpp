// SCStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<vector>
#include<deque>
#include<list>
#include<forward_list>
#include<iostream>

using namespace std;

void print_vector(vector<int> & v) {
	cout << "vector: ";
	for (vector<int>::iterator vi = v.begin(); vi != v.end(); ++vi) {
		cout << *vi << ", ";
	}
	cout << endl;
}

int main()
{
	vector<int> v;
	deque<int> d, d2;
	list<int> l, l2;
	forward_list<int> f;

	v.push_back(1);
	v.pop_back();
	// no front

	d.push_back(1);
	d.pop_back();
	d.push_front(1);
	d.pop_front();

	l.push_back(1);
	l.pop_back();
	l.push_front(1);
	l.pop_front();

	f.push_front(1);
	f.pop_front();
	// no back

	vector<int> sample{ 1, 2, 3, 4, 5, 6 };
	cout << "original: ";
	for (const int i : sample) {
		v.push_back(i);
		d.push_back(i);
		d2.push_front(i);
		l.push_back(i);
		l2.push_front(i);
		f.push_front(i);
		cout << i << ", ";
	}
	cout << endl;
	cout << endl;

	cout << "vector: ";
	for (auto vi = v.begin(); vi != v.end(); ++vi) {
		cout << *vi << ", ";
	}
	cout << endl;

	cout << "deque(push_back): ";
	for (auto di = d.begin(); di != d.end(); ++di) {
		cout << *di << ", ";
	}
	cout << endl;

	cout << "deque(push_front): ";
	for (auto di = d2.begin(); di != d2.end(); ++di) {
		cout << *di << ", ";
	}
	cout << endl;

	cout << "list(push_back): ";
	for (auto li = l.begin(); li != l.end(); ++li) {
		cout << *li << ", ";
	}
	cout << endl;

	cout << "list(push_front): ";
	for (auto li = l2.begin(); li != l2.end(); ++li) {
		cout << *li << ", ";
	}
	cout << endl;

	cout << "forward_list: ";
	for (auto fi = f.begin(); fi != f.end(); ++fi) {
		cout << *fi << ", ";
	}
	cout << endl;
	cout << endl;

	cout << "function: " << endl;
	print_vector(v);
	cout << endl;

	cout << "random access: " << endl;
	cout << "vector: ";
	for (unsigned int i = 0; i != v.size(); ++i) {
		cout << v[i] << ", ";
	}
	cout << endl;

	cout << "deque: ";
	for (unsigned int i = 0; i != d.size(); ++i) {
		cout << d[i] << ", ";
	}
	cout << endl;

	/*
	cout << "list: ";
	for (unsigned int i = 0; i != l.size(); ++i) {
		cout << l[i] << ", ";
	}
	cout << endl;
	*/
	// list does not support

	cout << "list does not support random access!" << endl;

	/*
	cout << "forward_list: ";
	for (unsigned int i = 0; i != f.size(); ++i) {
		cout << f[i] << ", ";
	}
	cout << endl;
	*/
	// forward_list does not support

	cout << "forward_list does not support random access!" << endl;

	cout << endl;
	cout << "copy: " << endl;

	vector<int> cv(v);
	// vector<int> cv2(d);
	// vector<int> cv3(l);
	// vector<int> cv4(f);
	deque<int> cd(d);
	// deque<int> cd2(v);
	// deque<int> cd3(l);
	// deque<int> cd4(f);
	list<int> cl(l);
	// list<int> cl2(v);
	// list<int> cl2(d);
	// list<int> cl2(f);
	forward_list<int> cf(f);
	// forward_list<int> cf2(v);
	// forward_list<int> cf3(d);
	// forward_list<int> cf4(l);

	cout << "vector: ";
	for (auto vi = v.begin(); vi != v.end(); ++vi) {
		cout << *vi << ", ";
	}
	cout << endl;
	cout << "copied vector: ";
	for (auto vi = cv.begin(); vi != cv.end(); ++vi) {
		cout << *vi << ", ";
	}
	cout << endl;

	cout << "deque: ";
	for (auto di = d.begin(); di != d.end(); ++di) {
		cout << *di << ", ";
	}
	cout << endl;
	cout << "copied deque: ";
	for (auto di = cd.begin(); di != cd.end(); ++di) {
		cout << *di << ", ";
	}
	cout << endl;

	cout << "list: ";
	for (auto li = l.begin(); li != l.end(); ++li) {
		cout << *li << ", ";
	}
	cout << endl;
	cout << "copied list: ";
	for (auto li = cl.begin(); li != cl.end(); ++li) {
		cout << *li << ", ";
	}
	cout << endl;

	cout << "forward_list: ";
	for (auto fi = f.begin(); fi != f.end(); ++fi) {
		cout << *fi << ", ";
	}
	cout << endl;
	cout << "copied forward_list: ";
	for (auto fi = cf.begin(); fi != cf.end(); ++fi) {
		cout << *fi << ", ";
	}
	cout << endl;

    return 0;
}

