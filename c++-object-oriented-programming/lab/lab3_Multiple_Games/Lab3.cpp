/***********************************************************

Lab3: Multiple Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

#include "stdafx.h"
#include "game.h"
#include "game_pieces.h"
#include "game_base.h"
#include "nine_almonds.h"
#include <iostream>
#include <string>
#include <memory>

using namespace std;

/* reate the game and throw the exceptions
return whatever return code it gives */

int main(int argc, char * argv[])
{

	try {
		shared_ptr<GameBase> game(GameBase::start(argc, argv));
		if (game == nullptr) {
			throw return_code::NO_GAME_CONSTRUCTED_ERR;
		}
		return game->play();
	}
	catch (bad_alloc) {
		return return_code::ACCOCATE_MEMORY_ERR;
	}
	catch (return_code err) {
		return err;
	}

}
