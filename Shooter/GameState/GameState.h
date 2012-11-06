//abstract game state
#pragma once

#include "EnumStates.h"
#include "SFML/Graphics.hpp" //for events

//child classes must be aware of StateMachine
class StateMachine;

class GameState
{
public:
	virtual ~GameState() {};
	virtual EnumState getID() = 0;
	virtual void handleEvents(sf::Event&) = 0;
	virtual void logic() = 0;
	virtual void draw() = 0;
};