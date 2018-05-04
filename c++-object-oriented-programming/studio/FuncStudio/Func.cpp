
#include "stdafx.h"
#include "FuncStudio.h"

/*
long long factorial(unsigned int i) {
	if (i == 0 || i == 1) {
		return 1;
	}
	else {
		return i * factorial(i - 1);
	}
}
*/

/*
void factorial(unsigned int i, unsigned int& r) {
	r = 1;
	if (i == 0 || i == 1) {
		return;
	}
	for (unsigned int j = i; j != 1; --j) {
		r *= j;
	}
	return;
}
*/

void factorial(unsigned int i, unsigned int* r) {
	*r = 1;
	if (i == 0 || i == 1) {
		return;
	}
	for (unsigned int j = i; j != 1; --j) {
		(*r) *= j;
	}
	return;
}

unsigned long power(unsigned int a, unsigned int b) {
	if (a == 0) {
		return 0;
	}
	if (b == 0) {
		return 1;
	}
	return a * power(a, --b);
}

