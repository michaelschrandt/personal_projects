#pragma once

#include "ImpactBehavior.h"

class DrillImpactBehavior : public ImpactBehavior
{
private:
	virtual bool hitAir(Map&, int, int) { return false; }
	virtual bool hitGrass(Map&, int, int) { return true; }
	virtual bool hitDirt(Map& m, int x, int y) {  m[y][x] = m.tileFactory.getTile(TILE_AIR); return true; }
	virtual bool hitRock(Map&, int, int) { return true; }
	virtual bool hitWater(Map& m, int x, int y) {return false; }
	virtual bool hitIce(Map& m, int x, int y) {  m[y][x] = m.tileFactory.getTile(TILE_AIR); return true; }
	virtual bool hitFlow(Map& m, int x, int y) { return false; }
public:
};