#pragma once

#include "EnumTile.h"
#include "Tile.h"
#include "SFML/Graphics.hpp"

const float UPDATE_TIME = .5;

class TileFactory
{
public:
	TileFactory();
	~TileFactory();
	Tile* getTile(EnumTile);
	void update();

private:
	float time;
	sf::Texture tileSheet;
	sf::Sprite** sprites;
	Tile* tiles;
};