#pragma once

#include "tetrisPiece.h"

class Tee : public TetrisPiece
{
public:
	Tee();
	~Tee();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 3;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
