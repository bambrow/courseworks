========================================================================

Lab0: Basic C++ Program Structure and Data Movement
Author: Weiqiang Li

========================================================================

Lab.h
	This is the header file.

Lab0.cpp
    This is the main application source file.

/////////////////////////////////////////////////////////////////////////////

This program parses the strings in a certain input file. After that, it will
print out every string that contains non-digit character(s) first, and will
print out every string that contains only digit(s) later.

Example:
Input: 
			one 1 two 2 three 3
output:		
			one
			two
			three
			1
			2
			3

/////////////////////////////////////////////////////////////////////////////

Errors or warnings when developing the solution: none.

/////////////////////////////////////////////////////////////////////////////

Cases:

1. no argument given

> Lab0.exe
Usage: Lab0.exe <input_file_name>
> echo %errorlevel%
1

2. more than one arguments given

> Lab0.exe in1.txt in2.txt
Usage: Lab0.exe <input_file_name>
> echo %errorlevel%
1

3. file does not exist
> Lab0.exe out.txt
Cannot open file out.txt.
> echo %errorlevel%
2

4. file contains no digit
sample file in.txt: 
			to be or not to be
> Lab0.exe in.txt
to
be
or
not
to
be
> echo %errorlevel%
0

5. file contains only digits
sample file in1.txt: 
			1993 2017 2333 6666
> Lab0.exe in1.txt
1993
2017
2333
6666
> echo %errorlevel%
0

6. file contains both non-digit strings and digit strings
sample file in2.txt: 
			C++ 1 Java 2 Python 3 C 4 PHP 5 JavaScript 6
> Lab0.exe in2.txt
C++
Java
Python
C
PHP
JavaScript
1
2
3
4
5
6
> echo %errorlevel%
0

In all test cases, the program runs correctly.

/////////////////////////////////////////////////////////////////////////////

