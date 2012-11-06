#pragma once

#include "SFML/Graphics.hpp"
#include "Map.h"
#include <iostream>
#include "ImpactBehavior.h"
//#include "StateOverworld.h"
#include "Game.h"
#include "EnumMissile.h"
#include "Missile.h"

class StateOverworld;

extern Game game;

const int PLAYER_W = 50;
const int PLAYER_H = 50; 
const int PLAYER_COL_H = 30;
const int PLAYER_COL_W = 20;
const int PLAYER_VEL = 200;
const int JUMP_VEL = 430;
const double BULLET_REFRESH = 0.3;
const float ANIM_DELAY = .5;

enum PlayerState
{
	PLAYER_STAND,
	PLAYER_STAND_SHOOT,
	PLAYER_WALK,
	PLAYER_WALK_SHOOT,
	PLAYER_JUMP,
	PLAYER_JUMP_SHOOT,
	PLAYER_FALL,
	PLAYER_FALL_SHOOT,
	N_ANIMATIONS,
};

class Player
{
private:
	sf::Texture spritesheet;
	sf::Sprite sprite;
	sf::Rect<float> collisionBox;
	sf::Rect<int>** animations;
	int* frameCount;

	float x;
	float y;
	int frame;
	PlayerState nextState;
	PlayerState state;
	bool falling;
	bool movingRight;
	EnumMissiles bulletType;

	void switchState();
	void setCollisionBox();
	bool checkCollision(Map &map);

public:
	Player();
	~Player();

	float x_vel;
	float y_vel;

	sf::Vector2<float> center();
	void update(StateOverworld*);
	void setNextState(PlayerState);
	void jump();
	EnumMissiles getBulletType() {return bulletType;}
	Missile *shoot();
	void changeBullets();
	void move(float x_offset, float y_offset);
	void move(Map&);
	void draw();
	void draw(sf::Rect<int> &view);
};