#include "Map.h"

Map::Map(std::string path)
{
	ifstream inFile(path);
	int width = 0;
	int height = 0;

	if(inFile.fail())
	{
		cout << "Could not open map file: " << path << endl;
		exit(0);
	}

	//get bounds
	inFile >> height >> width;
	this->width = width;
	this->height = height;

	//init map
	grid = new Tile**[height];
	for(int i = 0; i < height; i++)
		grid[i] = new Tile*[width];

	//fill map
	int cur;
	for(int i = 0; i < height; i++)
	{
		for(int j = 0; j < width; j++)
		{
			inFile >> cur;
			grid[i][j] = tileFactory.getTile(EnumTile(cur));
		}
	}
}

Map::~Map()
{
	for(int i = 0; i < height; i++)
		delete [] grid[i];

	delete [] grid;
}

void Map::draw()
{
	sf::Vector2f center = game.window.getView().getCenter();
	//const sf::FloatRect &viewRect = game.window.getView();
	for(int i = (int) (center.y - VISIBLE_H/2)/TILE_W; i < height && i <= (center.y + VISIBLE_H/2)/TILE_W; i++)
	{
		for(int j = (int) (center.x - VISIBLE_W/2)/TILE_W; j < width && j <= (center.x + VISIBLE_W/2)/TILE_W; j++)
		{
			//if(view.Contains(j*TILE_W, i*TILE_W))
				//grid[i][j]->draw();
				grid[i][j]->draw(j*(TILE_W), i*(TILE_W));
		}
	}
}

void Map::draw(sf::Rect<int> &view)
{
	//only draw tiles that in within the viewing rectangle
	//for(int i = view.Top/TILE_W; i < height && i <= view.Bottom/32; i++)
	//{
	//	for(int j = view.Left/TILE_W; j < width && j <= view.Right/32; j++)
	//	{
			//if(view.Contains(j*TILE_W, i*TILE_W))
				//grid[i][j]->draw(j*TILE_W - view.Left, i*TILE_W - view.Top);
	//	}
	//}
} 