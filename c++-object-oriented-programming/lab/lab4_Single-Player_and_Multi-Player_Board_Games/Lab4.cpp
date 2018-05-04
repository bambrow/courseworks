/***********************************************************

Lab4: Single-Player and Multi-Player Board Games
Author: Weiqiang Li, Mingyu Cao, Ciel Wu

***********************************************************/

/***********************************************************

This is the main application source file.

***********************************************************/
#include "stdafx.h"
#include "game.h"
#include "game_pieces.h"
#include "game_base.h"
#include <iostream>
#include <string>
#include <memory>

using namespace std;

/* reate the game and throw the exceptions; return whatever return code it gives */
int main(int argc, char * argv[])
{
	try {
		GameBase::start(argc, argv);
		if (GameBase::instance() == nullptr) {
			throw return_code::NO_GAME_CONSTRUCTED_ERR;
		}
		else
		{
			return GameBase::instance()->play();
		}
	}
	catch (bad_alloc) {
		return return_code::ALLOCATE_MEMORY_ERR;
	}
	catch (return_code err) {
		return err;
	}

}