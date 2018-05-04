#pragma once
#include "stdafx.h"

class IntVariables {
public:
	IntVariables() = default;
	IntVariables(int ivar1, int ivar2): var1(ivar1), var2(ivar2) { }
	IntVariables(const IntVariables& iv): var1(iv.var1), var2(iv.var2) { }
	int get_var1() const { return var1; };
	int get_var2() const { return var2; };
	IntVariables& set_var1(int ivar1) { var1 = ivar1; return *this; };
	IntVariables& set_var2(int ivar2) { var2 = ivar2; return *this; };
private:
	int var1 = 0;
	int var2 = 0;
};