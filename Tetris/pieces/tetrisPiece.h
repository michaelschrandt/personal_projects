// Michael Schrandt
//
// Tetrimino contains collision information, display data, and orientation of a single Tetris piece.

#pragma once
#include <iostream>
#include "SDL.h"
#include "SDL_draw.h"

#include "global.h"
using namespace std;




class TetrisPiece
{
public:
	TetrisPiece() { shape = NULL; shapes = NULL; this->currentState = 0; this->numStates = 0;};
	TetrisPiece(int*** sh, int n) { this->currentState = 0; setShapes(sh, n); }
	virtual ~TetrisPiece() {};

	//methods
	inline int** getShape() { return this->shape; }
	virtual int getDim() = 0;
	virtual int getCol() = 0;
	int numStates;
	void print()
	{
		for (int i = 0; i < getDim(); i++)
		{
			for(int j = 0; j < getDim(); j++)
			{
				if(shape[i][j] == 0)
					cout << " ";
				else
					cout << getCol();
			}

			cout << endl;
		}
	}

	void draw(SDL_Surface*, int, int, int);
	inline void resetState() {this->currentState = 0; this->shape = shapes[0];}
	inline void setShapes(int*** sh, int n) {this->shapes = sh; (this->shape) = shapes[0]; this->numStates = n;} 
	inline void rotateClock() { (this->shape) = shapes[abs(++currentState % numStates)]; }
	inline void rotateAntiClock()  { (this->shape) = shapes[abs(--currentState % numStates)]; }
private:

	//fields
	int **shape; //pointer to 2-d array
	int ***shapes; //array of 2-d arrays
	int currentState;


};

