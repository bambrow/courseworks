/***********************************************************

Lab2: Nine Almonds Game
Author: Weiqiang Li

***********************************************************/

#include "stdafx.h"
#include "game_pieces.h"
#include "game.h"
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char * argv[])
{

	/* if the number of arguments is not correct, print usage message and return error code */
	if (argc != array_index::ARGUMENT_NUM && argc != array_index::ARGUMENT_NUM - 1) {
		usage_message(argv[array_index::PROGRAM_NAME], "NineAlmonds [auto_play]");
		return return_code::ILLEGAL_NUM_OF_ARGS_ERR;
	}

	/* if the argument passed is not expected, print usage message and return error code*/
	string arg_name = argv[array_index::ARGUMENT_NAME];
	if (arg_name != "NineAlmonds") {
		usage_message(argv[array_index::PROGRAM_NAME], "NineAlmonds [auto_play]");
		return return_code::WRONG_ARGS_ERR;
	}
	if (argc == array_index::ARGUMENT_NUM) {
		string arg_option = argv[array_index::EXTRA_OPTION];
		if (arg_option != "auto_play") {
			usage_message(argv[array_index::PROGRAM_NAME], "NineAlmonds [auto_play]");
			return return_code::WRONG_ARGS_ERR;
		}
	}

	/* create a game object and returns whatever the play method returns */
	NineAlmondsGame game = NineAlmondsGame();
	if (argc == array_index::ARGUMENT_NUM - 1) {
		return game.play();
	}
	else {
		return game.auto_play();
	}

}
