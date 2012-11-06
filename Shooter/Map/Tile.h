#pragma once

#include "Game.h"
#include "EnumTile.h"
#include <iostream>
#include "SFML/Graphics.hpp"

extern Game game;

class Tile
{
private:
	bool solid;
	int frame;
	int numFrames;
	sf::Sprite *images;
	EnumTile type;
	float time;

public:
	Tile();
	Tile(bool solid, int numFrames, sf::Sprite *images, EnumTile type);
	bool isSolid() {return solid;}
	sf::Sprite getImage() {return images[frame];}
	EnumTile getType() {return type;}
	
	void update();
	void draw(int x, int y);
	void draw();
};