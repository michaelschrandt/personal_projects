#pragma once

#include "tetrisPiece.h"

class Line : public TetrisPiece
{
public:
	Line();
	~Line();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 1;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
