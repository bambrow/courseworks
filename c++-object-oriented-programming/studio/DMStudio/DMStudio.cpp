// DMStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "detector.h"
#include <iostream>
#include <memory>
#include <vector>

using namespace std;

shared_ptr<detector> func(shared_ptr<detector> p) {

	cout << endl << "-------func scope begins-------" << endl << endl;

	shared_ptr<detector> pp = p;

	cout << endl << "-------func scope ends-------" << endl << endl;

	return pp;
	
}

int main()
{
	cout << endl << "-------main scope begins-------" << endl << endl;

	// shared_ptr<detector> p = make_shared<detector>();
	// shared_ptr<detector> pp = func(p);


	shared_ptr<detector> p1 = make_shared<detector>();
	shared_ptr<detector> p2 = p1;
	shared_ptr<detector> p3 = p2;

	vector<shared_ptr<detector>> v;
	v.push_back(p1);
	v.push_back(p2);
	v.push_back(p3);

	
	cout << "original:" << endl
		<< p1.get() << " " << p2.get() << " " << p3.get() << endl
		<< "in vector:" << endl
		<< v[0].get() << " " << v[1].get() << " " << v[2].get() << endl;
    
	

	cout << endl << "-------main scope ends-------" << endl << endl;

	
    return 0;
}

