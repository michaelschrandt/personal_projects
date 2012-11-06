#pragma once

#include "tetrisPiece.h"

class VerticalS : public TetrisPiece
{
public:
	VerticalS();
	~VerticalS();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 4;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
