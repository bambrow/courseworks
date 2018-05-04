#pragma once

class detector {
	unsigned int x;
	static unsigned int x_static;
public:
	detector();
	~detector();
	int get_x();
};
