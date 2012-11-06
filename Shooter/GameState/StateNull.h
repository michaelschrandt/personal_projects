//state template
#pragma once

#include "GameState.h"

class StateNull : public GameState
{
private:
	StateMachine* parent;
public:
	StateNull(StateMachine* p){ parent = p; }
	EnumState getID(){ return STATE_NULL; }
	void handleEvents(sf::Event&){ }
	void logic(){ }
	void draw(){ }
};