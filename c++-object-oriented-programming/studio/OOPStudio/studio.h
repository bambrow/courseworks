#pragma once

class Studio {
public:
	Studio();
	virtual ~Studio();
	virtual void print();
};

class Studio2 : public Studio {
public:
	Studio2();
	virtual ~Studio2();
	virtual void print();
};