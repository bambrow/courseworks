// StmtStudio.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
#include<string>

using namespace std;

void func() {
	cout << "func() running" << endl;
	// int i = 2;
	char j = 'c';
	// string s = "c++";
	// int* s = & i;
	char* s = &j;
	throw s;
}

enum { SUCCESS, INT_ERR, CHAR_ERR, LONG_ERR, STRING_ERR, CHARP_ERR, INTP_ERR };

int main()
{
	// func();
	try {
		func();
	}
	catch(int & err_value) {
		cout << "catch exception int " << err_value << "!" << endl;
		return INT_ERR;
	}
	catch (char & err_value) {
		cout << "catch exception char " << err_value << "!" << endl;
		return CHAR_ERR;
	}
	catch (long & err_value) {
		cout << "catch exception long " << err_value << "!" << endl;
		return LONG_ERR;
	}
	catch (string & err_string) {
		cout << "catch exception string " << err_string << "!" << endl;
		return STRING_ERR;
	}
	catch (int* &err_value) {
		cout << "catch exception int* " << *err_value << "!" << endl;
		return INTP_ERR;
	}
	catch (char* &err_value) {
		cout << "catch exception char* " << *err_value << "!" << endl;
		return CHARP_ERR;
	}
    return SUCCESS;
}

