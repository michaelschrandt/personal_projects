// Michael Schrandt

#include "Tile.h"

Tile::Tile()
{
	//should probably initialize everything to some default tile
}

Tile::Tile(bool solid, int numFrames, sf::Sprite *images, EnumTile type)
{
	this->solid = solid;
	this->frame = 0;
	this->numFrames = numFrames;
	this->images = images;
	this->type = type;
	this->time = 0;
}

void Tile::update()
{
	frame++;
	if(frame >= numFrames)
		frame = 0;
}

void Tile::draw(int x, int y)
{
	images[frame].setPosition((float)x, (float)y);
	game.window.draw(images[frame]);
}

void Tile::draw()
{
	game.window.draw(images[frame]);
	std::cout << type;
}