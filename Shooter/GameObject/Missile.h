#pragma once

#include "EnumTile.h"
#include "Game.h"
#include "Map.h"
#include "SFML/Graphics.hpp"
#include "SFML/Audio.hpp"
#include "EnumMissile.h"
#include "ImpactBehavior.h"
#include "FireImpactBehavior.h"
#include "IceImpactBehavior.h"
#include "ElectricImpactBehavior.h"
#include "DrillImpactBehavior.h"

const int MISSILE_VELOCITY = 500;
const int BULLET_SPRITE_W = 16;
extern Game game;

class Missile
{
private:
	bool positive;
	float x;
	float y;
	float realX(){ return x + 8; }
	float realY(){ return y + 8; }

	sf::Sound* sound;
	ImpactBehavior* impactBehavior;

	sf::Sprite sprite;

public:
	Missile();
	//Missile(const Missile&);
	Missile(float, float, bool, EnumMissiles type, sf::Sound*);
	~Missile();

	bool hit(Map&);
	void update();
	void draw();
	void draw(sf::Rect<int>&);
};