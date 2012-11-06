#pragma once

#include "EnumStates.h"
#include "SFML/Graphics.hpp" //for events
#include <iostream>

//GameState must be known, but not necessarily defined yet
class GameState;

class StateMachine
{
private:
	GameState *currentState;
	EnumState nextState;

public:
	StateMachine();
	~StateMachine() { };

	void setNextState(EnumState);
	void changeState();
	EnumState getID();

	void handleEvents(sf::Event&);
	void logic();
	void draw();
};