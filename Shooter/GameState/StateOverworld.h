#pragma once

#include "GameState.h"
#include <vector>
#include <iostream>
#include <string>
#include "Map.h"
#include "Player.h"
#include "Missile.h"

class StateOverworld : public GameState
{
private:
	StateMachine* parent;
	Map map;
	//sf::Rect<int> view;
	sf::View view;
	vector<Missile*> bullets;

	void initKeyboard();
	void correctView();
	void updateBullets();
	void drawBullets();
	Player player;


public:
	StateOverworld(StateMachine* p);
	~StateOverworld() { }

	EnumState getID(){ return STATE_OVERWORLD; }
	Map& getMap(){ return this->map; }
	void addObject(Missile *m){ bullets.push_back(m); }
	void handleEvents(sf::Event&);
	void logic();
	void draw();
	void drawHud();
};