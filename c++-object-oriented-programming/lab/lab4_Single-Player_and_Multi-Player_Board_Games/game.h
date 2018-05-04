/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

this is the header file for common enumerations and helper 
functions.

***********************************************************/
#pragma once

#include "stdafx.h"
#include "game_pieces.h"

#ifndef game_h
#define game_h

#include <string>

/* array index for command line arguments */
enum array_index
{
	PROGRAM_NAME,		/* argv[0] is always the program name */
	ARGUMENT_NAME,		/* argv[1] should be the name of argument */
	EXTRA_ARG1,			/* first extra argument */
	EXTRA_ARG2,			/* second extra argument */
	ARGUMENT_NUM		/* equivalent to argc, indicates there should be 1 arguments */
};

/* return code for Lab3.cpp */
enum return_code
{
	SUCCESS,					/* success run */
	ILLEGAL_ARGUMENT_ERR,		/* illegal argument */
	WRONG_ARGUMENT_NUM_ERR,		/* wrong number of argument */
	NO_GAME_CONSTRUCTED_ERR,	/* no game constructed */
	BAD_GAME_POINTER_ERR,		/* invalid game pointer */
	ALLOCATE_MEMORY_ERR,		/* fails to allocate memory */
	BAD_GAME_CONDITION_ERR,		/* the game is in bad condition */
	FAIL_TO_READ_FILE_ERR,		/* fails to read the file */
	BADLY_FORMED_FILE_ERR,		/* fails to read conditions from the file */
	USER_QUIT,					/* the user chooses to quit */
	NO_VALID_MOVE				/* there is no valid move on the board */
};

/* helper function to print the error message if command line argument is not correct */
void usage_message(const string &, const string &);

/* change all characters in a string to lower case */
void lower_case(string &);

/* trim the string; delete the whitespaces on the left and right end */
void trim(string &);

/* replace the commas in the string to whitespaces */
bool reform(string &);

/* change char* to int */
int char_to_int(char *);

#endif
