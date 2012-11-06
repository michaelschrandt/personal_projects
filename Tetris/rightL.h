#pragma once

#include "tetrisPiece.h"

class RightL : public TetrisPiece
{
public:
	RightL();
	~RightL();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 7;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
