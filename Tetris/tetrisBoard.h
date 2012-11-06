#pragma once

#include "tetrisPiece.h"
#include "square.h"
#include "tee.h"
#include "horizontalS.h"
#include "verticalS.h"
#include "rightL.h"
#include "leftL.h"
#include "line.h"
#include "SDL.h"
#include "global.h"
#include <ctime>
#include <cstdlib>
#include <cmath>
#include "SDL_draw.h"
#include "SDL_ttf.h"
#include "SDL_image.h"
#include <string>
#include <vector>

#define GRID_W 10
#define GRID_H 22

class TetrisBoard
{
public:
	TetrisBoard();
	~TetrisBoard();

	TetrisPiece * newPiece();

	void update();
	void print();
	void clearPieceFromBoard();
	void drawPieceToBoard();
	void drawPieceToBoard(int);
	void setHighScore(){if(linesCleared > TetrisBoard::highScore) TetrisBoard::highScore = linesCleared;};
	int clearFullRows();
	bool checkCollision(int, int);
	bool lost();
	inline bool isAlive(){return alive;}
	inline int getLevel(){return linesCleared/10;}

	vector<char> getBestPosition();
	int getScore(int, int);

	void draw(SDL_Surface*, TTF_Font*);
	void handleInput(SDL_Event&);
	void handleInput(Uint8*);
	static long highScore;
private:
	//bool lockPenalty;
	bool alive;
	bool canHold;

	int grid[GRID_H][GRID_W];
	int pos_x, pos_y;
	long linesCleared;

	TetrisPiece * currentPiece;
	TetrisPiece * nextPiece;
	TetrisPiece * heldPiece;
};

