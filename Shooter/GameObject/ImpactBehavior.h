#pragma once

#include "Map.h"
#include "EnumTile.h"

class ImpactBehavior
{
private:
	virtual bool hitAir(Map&, int, int) { return false; }
	virtual bool hitGrass(Map&, int, int) { return true; }
	virtual bool hitDirt(Map&, int, int) { return true; }
	virtual bool hitRock(Map&, int, int) { return true; }
	virtual bool hitWater(Map& m, int x, int y) { return false; }
	virtual bool hitIce(Map& m, int x, int y) { return true; }
	virtual bool hitFlow(Map&, int, int) { return true; }
public:
	ImpactBehavior() {}
	virtual ~ImpactBehavior() {}
	virtual bool hit(Map& map, int x, int y)
	{
		switch( map[y][x]->getType() )
		{
		case TILE_AIR:
			return hitAir(map, x, y);
			break;
		case TILE_GRASS:
			return hitGrass(map, x, y);
			break;
		case TILE_DIRT:
			return hitDirt(map, x, y);
			break;
		case TILE_ROCK:
			return hitRock(map, x, y);
			break;
		case TILE_WATER:
			return hitWater(map, x, y);
			break;
		case TILE_ICE:
			return  hitIce(map, x, y);
			break;
		case TILE_FLOW:
			return hitFlow(map, x, y);
			break;
		default:
			//do nothing
			return false;
			break;
		}
	}
};