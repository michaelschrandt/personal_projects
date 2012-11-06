#include "leftL.h"


LeftL::LeftL()
{
	this->numStates = 4;
	this->dimensions = 3;
	
	shapes = new int** [numStates];
	for(int i = 0; i < numStates; i++)
	{
		shapes[i] = new int* [dimensions];
		for(int j = 0; j < dimensions; j++)
		{
			shapes[i][j] = new int [dimensions];
		}
	}

	//state 1
	shapes[0][0][0] = 1;
	shapes[0][0][1] = 0;
	shapes[0][0][2] = 0;
	shapes[0][1][0] = 1;
	shapes[0][1][1] = 1;
	shapes[0][1][2] = 1;
	shapes[0][2][0] = 0;
	shapes[0][2][1] = 0;
	shapes[0][2][2] = 0;

	//state 2
	shapes[1][0][0] = 0;
	shapes[1][0][1] = 1;
	shapes[1][0][2] = 1;
	shapes[1][1][0] = 0;
	shapes[1][1][1] = 1;
	shapes[1][1][2] = 0;
	shapes[1][2][0] = 0;
	shapes[1][2][1] = 1;
	shapes[1][2][2] = 0;

	//state 3
	shapes[2][0][0] = 0;
	shapes[2][0][1] = 0;
	shapes[2][0][2] = 0;
	shapes[2][1][0] = 1;
	shapes[2][1][1] = 1;
	shapes[2][1][2] = 1;
	shapes[2][2][0] = 0;
	shapes[2][2][1] = 0;
	shapes[2][2][2] = 1;

	//state 4
	shapes[3][0][0] = 0;
	shapes[3][0][1] = 1;
	shapes[3][0][2] = 0;
	shapes[3][1][0] = 0;
	shapes[3][1][1] = 1;
	shapes[3][1][2] = 0;
	shapes[3][2][0] = 1;
	shapes[3][2][1] = 1;
	shapes[3][2][2] = 0;

	TetrisPiece::setShapes(shapes, numStates);
}
LeftL::~LeftL()
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