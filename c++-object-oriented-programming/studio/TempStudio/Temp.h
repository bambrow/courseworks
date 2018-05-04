#pragma once

#include <iterator>

template <typename Iterator>
size_t count_func(Iterator first, Iterator last) {
	size_t count = 0;
	while (first != last) {
		++first;
		++count;
	}
	return count;
}

template <typename T>
struct struc {
	T t;
	struc(const T& that) {
		t = that;
	}
};