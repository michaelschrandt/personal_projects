#include "TileFactory.h"

TileFactory::TileFactory()
{
	//load sprite sheet
	if(!tileSheet.loadFromFile("resources/tilesheettest.png"))
	{
		std::cout << "Could not open tilesheet.png" << std::endl;
		exit(0);
	}

	time = 0;
	tiles = new Tile[TILE_NULL];
	sprites = new sf::Sprite*[TILE_NULL];

	int* numFrames = new int[TILE_NULL];
	numFrames[TILE_AIR] = 1;
	numFrames[TILE_GRASS] = 1;
	numFrames[TILE_DIRT] = 1;
	numFrames[TILE_ROCK] = 1;
	numFrames[TILE_WATER] = 3;
	numFrames[TILE_ICE] = 2;
	numFrames[TILE_FLOW] = 3;

	//initialize sprites
	for(int i = 0; i < TILE_NULL; i++)
	{
		sprites[i] = new sf::Sprite[numFrames[i]];

		for(int j = 0; j < numFrames[i]; j++)
		{
			sf::Sprite sprite;
			sprite.setTexture(tileSheet);
			sprite.setTextureRect(sf::IntRect(i*TILE_W, j*TILE_W, (i+1)*TILE_W, (j+1)*TILE_W ));
			sprites[i][j] = sprite;
		}
	}


	//create every tile only once!
	tiles[TILE_AIR]		= Tile(false,	numFrames[TILE_AIR], sprites[TILE_AIR],		TILE_AIR);
	tiles[TILE_GRASS]	= Tile(true,	numFrames[TILE_GRASS], sprites[TILE_GRASS],	TILE_GRASS);
	tiles[TILE_DIRT]	= Tile(true,	numFrames[TILE_DIRT], sprites[TILE_DIRT],		TILE_DIRT);
	tiles[TILE_ROCK]	= Tile(true,	numFrames[TILE_ROCK], sprites[TILE_ROCK],		TILE_ROCK);
	tiles[TILE_WATER]	= Tile(false,	numFrames[TILE_WATER], sprites[TILE_WATER],	TILE_WATER);
	tiles[TILE_ICE]		= Tile(true,	numFrames[TILE_ICE], sprites[TILE_ICE],		TILE_ICE);
	tiles[TILE_FLOW]	= Tile(true,	numFrames[TILE_FLOW], sprites[TILE_FLOW],		TILE_FLOW);
	//tiles[TILE_OTHER] = TILE(isSolid, image, TILE_OTHER);
}

TileFactory::~TileFactory()
{
	delete [] tiles;

	for(int i = 0; i < TILE_NULL; i++)
		delete [] sprites[i];
	delete [] sprites;
}

void TileFactory::update()
{
	time += game.time.asSeconds();
	if(time > UPDATE_TIME)
	{
		time = 0;
		for(int i = 0; i < TILE_NULL; i++)
			tiles[i].update();
	}
}

Tile* TileFactory::getTile(EnumTile type)
{
	return &tiles[type];
}