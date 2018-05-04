/***********************************************************

Lab0: Basic C++ Program Structure and Data Movement
Author: Weiqiang Li

***********************************************************/

#include <vector>
#include <string>

using std::vector;
using std::string;

/* array index for command line arguments */
enum array_index 
{
	PROGRAM_NAME,		/* argv[0] is always the program name */
	INPUT_FILE_NAME,	/* argv[1] should be the name of input file */
	ARGUMENT_NUM		/* equivalent to argc, indicates there should be 2 arguments */
};

/* return code for Lab0.cpp */
enum return_code 
{
	SUCCESS,					/* success run */
	ILLEGAL_NUM_OF_ARGS_ERR,	/* the value of argc is not correct */
	FILE_OPEN_FAILURE_ERR		/* failure to open the input file */
};

/* parse the input file and read all its contents to a vector */
int parse_file(vector<string> &, char*);

/* helper function to print the error message if the number of command line arguments is not correct */
int usage_message(const string &);
