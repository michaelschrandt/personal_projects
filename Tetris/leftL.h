#pragma once

#include "tetrisPiece.h"

class LeftL : public TetrisPiece
{
public:
	LeftL();
	~LeftL();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 6;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
