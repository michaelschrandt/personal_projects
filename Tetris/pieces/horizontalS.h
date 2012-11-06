#pragma once


#include "tetrisPiece.h"

class HorizontalS : public TetrisPiece
{
public:
	HorizontalS();
	~HorizontalS();
	virtual int getDim() {return this->dimensions;}
	virtual int getCol() {return 5;}
private:

	int*** shapes;
	int dimensions;
	int numStates;
};
