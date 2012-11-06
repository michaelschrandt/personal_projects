#pragma once

#include "ImpactBehavior.h"

class ElectricImpactBehavior : public ImpactBehavior
{
private:
	virtual bool hitAir(Map&, int, int) { return false; }
	virtual bool hitGrass(Map&, int, int) { return false; }
	virtual bool hitDirt(Map&, int, int) { return false; }
	virtual bool hitRock(Map&, int, int) { return false; }
	virtual bool hitWater(Map& m, int x, int y) { return false; }
	virtual bool hitIce(Map& m, int x, int y) { return false; }
	virtual bool hitFlow(Map& m, int x, int y) { return false; }
public:
};