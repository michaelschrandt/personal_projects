// Michael Schrandt
//
// Map is a 2d grid of tiles.

#pragma once

#include <iostream>
#include <fstream>
#include "Tile.h"
#include "TileFactory.h"

using namespace std;

class Map
{
public:
	//Map();
	Map(std::string);
	~Map();

	void update() {tileFactory.update(); }
	int getHeight() { return height; }
	int getWidth() { return width; }
	void draw(sf::Rect<int>&);
	void draw();
	Tile**& operator[](unsigned int i) {return grid[i];}
	TileFactory tileFactory;

private:
	int width;
	int height;
	Tile*** grid; //2d array of tile pointers

};