#pragma once

#include "GameState.h"
#include <iostream>
#include <string>

class StateTitle : public GameState
{
private:
	std::string command;
	StateMachine* parent;
public:
	StateTitle(StateMachine* p);
	~StateTitle() { }

	EnumState getID(){ return STATE_TITLE; }
	void handleEvents(sf::Event&);
	void logic();
	void draw();
};