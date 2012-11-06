#include "square.h"


Square::Square()
{
	this->numStates = 1;
	this->dimensions = 2;
	
	shapes = new int** [numStates];
	for(int i = 0; i < numStates; i++)
	{
		shapes[i] = new int* [dimensions];
		for(int j = 0; j < dimensions; j++)
		{
			shapes[i][j] = new int [dimensions];
		}
	}

	shapes[0][0][0] = 1;
	shapes[0][0][1] = 1;
	shapes[0][1][0] = 1;
	shapes[0][1][1] = 1;

	TetrisPiece::setShapes(shapes, numStates);
}

Square::~Square()
{

	for(int i = 0; i < numStates; i++)
	{
		for(int j = 0; j < dimensions; j++)
		{
			delete [] shapes[i][j];
		}
		delete [] shapes[i];
	}

	delete [] shapes;

}
