#pragma once

#include "tetrisPiece.h"

class Square : public TetrisPiece
{
public:
	Square();
	~Square();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 2;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
