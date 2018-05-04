#pragma once

#include<iostream>

using namespace std;

template <typename T>
size_t count_func(T* first, T* last) {
	cout << "pointer version" << endl;
	return last - first;
}

template <>
size_t count_func<int>(int* first, int* last) {
	size_t count = 0;
	while (first != last) {
		cout << *first << " ";
		++first;
		++count;
	}
	cout << endl;
	return count;
}

template <>
size_t count_func<char>(char* first, char* last) {
	size_t count = 0;
	while (first != last) {
		cout << *first;
		++first;
		++count;
	}
	cout << endl;
	return count;
}

template <typename Iterator>
size_t count_func(Iterator first, Iterator last) {
	cout << "iterator version" << endl;
	size_t count = 0;
	while (first != last) {
		++first;
		++count;
	}
	return count;
}

