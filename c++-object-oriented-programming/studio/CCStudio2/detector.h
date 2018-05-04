#pragma once

#include "stdafx.h"

#ifndef detector_h
#define detector_h

class detector {
	static unsigned int x_static;
	unsigned int x;
	friend class wrapper;
public:
	detector();
	~detector();
	detector(const detector&);
};

#endif
