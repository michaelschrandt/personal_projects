// Michael Schrandt
//


#include "tetrisBoard.h"

long TetrisBoard::highScore = 0;

TetrisBoard::TetrisBoard()
{
	//initialize grid
	srand(time(NULL));
	for (int i = 0; i < GRID_H; i++)
		for (int j = 0; j < GRID_W; j++)
			grid[i][j] = 0;

	currentPiece = newPiece();
	nextPiece = newPiece();
	heldPiece = NULL;

	alive = true;
	canHold = true;

	pos_x = GRID_W/2 - ceil(currentPiece->getDim()/2.0);
	pos_y = 0;
	linesCleared = 0;

}

TetrisBoard::~TetrisBoard()
{
	//if(heldPiece != NULL)
	//	delete heldPiece;
	//if(currentPiece != NULL)
	//	delete currentPiece;
	//if(nextPiece != NULL)
	//	delete nextPiece;
}

//when key is pressed
void TetrisBoard::handleInput(SDL_Event& event)
{
	switch( event.key.keysym.sym )
	{
	case SDLK_UP:
		{
		//drop piece
			clearPieceFromBoard();
			int tempy = pos_y;
			while(!checkCollision(pos_x, ++tempy));
			pos_y = tempy-1;
			drawPieceToBoard();

			update();
			break;
		}
	case SDLK_LEFT:
		{
			clearPieceFromBoard();
			if (!checkCollision(pos_x - 1, pos_y))
				pos_x--;
			drawPieceToBoard();
			break;
		}
	case SDLK_RIGHT:
		//move right if possible
		{
			clearPieceFromBoard();
			if (!checkCollision(pos_x + 1, pos_y))
				pos_x++;
			drawPieceToBoard();
			break;
		}

	case SDLK_DOWN:
		//move down if possible
		{
			clearPieceFromBoard();
			if (!checkCollision(pos_x, pos_y + 1))
				pos_y++;
			drawPieceToBoard();
			break;
		}
	case SDLK_z:
		{
			//rotate clockwise
			clearPieceFromBoard();
			currentPiece->rotateClock();
			if(checkCollision(pos_x, pos_y))
				currentPiece->rotateAntiClock();

			drawPieceToBoard();
			break;
		}
	case SDLK_x:
		{
			//rotate counterclockwise
			clearPieceFromBoard();
			currentPiece->rotateAntiClock();
			if(checkCollision(pos_x, pos_y))
				currentPiece->rotateClock();

			drawPieceToBoard();
			break;
		}
	case SDLK_c:
		{	//hold piece
			if (canHold)
			{
				canHold = false;

				clearPieceFromBoard();
				TetrisPiece * temp = heldPiece;
				heldPiece = currentPiece;
				if(temp == NULL)
				{
					currentPiece = nextPiece;
					nextPiece = newPiece();
				}
				else
				{
					currentPiece = temp;
				}

				heldPiece->resetState();
				pos_y = 0;
				pos_x = GRID_W/2 - ceil(currentPiece->getDim()/2.0);
			}

		}
	default:
		break;

	}
}

//when a key is held down: action is repeated
void TetrisBoard::handleInput(Uint8* keystates)
{
	/*if(keystates[ SDLK_UP ])
	{
		//drop piece
		clearPieceFromBoard();
		int tempy = pos_y;
		while(!checkCollision(pos_x, ++tempy));
		pos_y = tempy-1;
		drawPieceToBoard();

		update();

	}*/

	if(keystates[ SDLK_RIGHT])
	{
		clearPieceFromBoard();
		if (!checkCollision(pos_x + 1, pos_y))
			pos_x++;
		drawPieceToBoard();
	}

	if(keystates[ SDLK_LEFT])
	{
		clearPieceFromBoard();
		if (!checkCollision(pos_x - 1, pos_y))
			pos_x--;
		drawPieceToBoard();

	}

	if(keystates[SDLK_DOWN])
	{
		clearPieceFromBoard();
		if (!checkCollision(pos_x, pos_y + 1))
			pos_y++;
		drawPieceToBoard();
	}
	/*
	if(keystates[SDLK_z])
	{
		clearPieceFromBoard();
		currentPiece->rotateClock();
		if(checkCollision(pos_x, pos_y))
			currentPiece->rotateAntiClock();

		drawPieceToBoard();
	}

	if(keystates[SDLK_x])
	{
		clearPieceFromBoard();
		currentPiece->rotateAntiClock();
		if(checkCollision(pos_x, pos_y))
			currentPiece->rotateClock();

		drawPieceToBoard();
	}*/
}

//random piece generator
TetrisPiece* TetrisBoard::newPiece()
{
	int n = rand() % 7 + 1;
	switch(n)
	{
	case 1:
		return new Line();
	case 2:
		return new Square();
	case 3:
		return new Tee();
	case 4:
		return new VerticalS();
	case 5:
		return new HorizontalS();
	case 6:
		return new LeftL();
	case 7:
		return new RightL();
	}

	return NULL;
}

//check if a piece potentially collides with the grid, or other pieces
bool TetrisBoard::checkCollision(int tempx, int tempy)
{
	for (int i = 0; i < (*currentPiece).getDim(); i++)
		for(int j = 0; j < (*currentPiece).getDim(); j++)
		{
			if ((
					(i + tempy) >= GRID_H || 
					(j + tempx) >= GRID_W ||
					(j + tempx) < 0 ||
					grid[i + tempy][j + tempx] > 0
				) && (currentPiece->getShape()[i][j] > 0))
				return true;
		}
	
	return false;
}

//did we lose?
bool TetrisBoard::lost()
{
	for(int i = 0; i < GRID_W; i++)
	{
		if(grid[1][i] != 0)
			return true;
	}

	return false;
}

//clear all rows that are filled
int TetrisBoard::clearFullRows()
{
	int lines = 0;
	
	//starting from the top row
	for (int i = 0; i < GRID_H; i++)
	{
		//check if a spot is empty
		bool allOnes = true;
		for(int j = 0; j < GRID_W; j++)
		{
			if (grid[i][j] == 0)
			{
				allOnes = false;
			}
		}
		
		if(allOnes) //shift upper rows down one
		{
			lines++;
			for(int k = i-1; k >= 0; k--)
			{
				for(int l = 0; l < GRID_W; l++)
					grid[k+1][l] = grid[k][l];
			}
		}
	}

	return lines;
}

//called once each cycle
void TetrisBoard::update()
{
	clearPieceFromBoard();
	if (checkCollision(pos_x,pos_y+1))	//current piece locked in
	{
		drawPieceToBoard();
	
		delete currentPiece;

		linesCleared += clearFullRows();
		setHighScore();

		if(lost())
		{
			alive = false;
			return;
		}

		//set up new piece
		currentPiece = nextPiece;
		nextPiece = newPiece();
		canHold = true;

		pos_y = 0;
		pos_x = GRID_W/2 - ceil(currentPiece->getDim()/2.0);

		getBestPosition(); //AI COMPONENT
		
	}
	else //move current piece down one row
	{
		pos_y++;
		drawPieceToBoard();
	}

}

//AI COMPONENT
//
// - Select a position for the current piece that yields the highest score.
// - All possible positions are tested
// - If the "held" piece has a higher score, swap current piece with held piece
vector<char> TetrisBoard::getBestPosition()
{
	int maxScore = -100;
	int bestPerm = 0;
	int bestX = 0;
	clearPieceFromBoard();

	//for every rotation of the current piece
	for(int i = 0; i < currentPiece->numStates; i++)
	{
		//check every x-value that it can fall, starting from left to right
		int tempx = pos_x;
		while(!checkCollision(--tempx, pos_y));
		tempx++;

		for(; !checkCollision(tempx, pos_y); tempx++)
		{
			int currentScore = getScore(tempx, i);
			
			//check if this spot is the best so far
			if(currentScore > maxScore)
			{
				maxScore = currentScore;
				bestPerm = i;
				bestX = tempx;
			}
		}
		
		//rotate, and try again
		currentPiece->rotateClock();
	}
	//if there is currently no held piece, and the score is really bad...
	if(heldPiece == NULL && maxScore < 20)
	{
			//just hold the current piece
			heldPiece = currentPiece;
			currentPiece = nextPiece;
			nextPiece = newPiece();
	}
	
	//if there is a held piece, check its score too
	
	//THIS COULD BE IN ITS OWN METHOD, SAME CODE AS ABOVE
	else if(heldPiece != NULL)
	{
		TetrisPiece * temp = currentPiece;
		currentPiece = heldPiece;
		
		int maxHeldScore = -100;
		int bestHeldPerm = 0;
		int bestHeldX = 0;
		for(int i = 0; i < currentPiece->numStates; i++)
		{
			int tempx = pos_x;
			while(!checkCollision(--tempx, pos_y));
			tempx++;

			for(; !checkCollision(tempx, pos_y); tempx++)
			{
				int currentScore = getScore(tempx, i);
				if(currentScore > maxHeldScore)
				{
					maxHeldScore = currentScore;
					bestHeldPerm = i;
					bestHeldX = tempx;
				}
			}

			currentPiece->rotateClock();
		}

		if(maxHeldScore > maxScore)
		{
			bestX = bestHeldX;
			bestPerm = bestHeldPerm;
			this->heldPiece = temp;
		}
		else
		{
			this->currentPiece = temp;
		}
	}

	for(int i = 0; i < bestPerm; i++)
		currentPiece->rotateClock();
	pos_x = bestX;
	drawPieceToBoard();



	return vector<char>();

}

// Calculate the score of a potential spot for a piece:
//	- Default is 0
//	- For every edge touching a wall or use tile, +2
//	- For every edge facing down, left or right (but not up), -10
//	- Tweaked scores may yield better results
int TetrisBoard::getScore(int tempx, int rotation)
{
	//clearPieceFromBoard();
	int tempy = pos_y;
	while(!checkCollision(tempx, ++tempy));
	tempy--;
	//drawPieceToBoard();

	int score = 0;
	int** shape = currentPiece->getShape();
	score += tempy/2;
	for(int i = 0; i < currentPiece->getDim(); i++)
	{
		for(int j = 0; j < currentPiece->getDim(); j++)
		{
			if(shape[i][j] == 0)
				continue;

			if(i + tempy == GRID_H - 1) //touching ground
				score += 2;
			
			if(i + tempy != GRID_H - 1 && grid[i + tempy + 1][j + tempx] != 0) //touching adjacent piece (below)
				score += 2;
			
			if(i + tempy != GRID_H - 1 && i < currentPiece->getDim() - 1 && shape[i][j] != 0 && shape[i+1][j] == 0 &&
				grid[i+tempy+1][j+tempx] == 0) //touching blank tile, BAD
				score -= 10;

			if((j + tempx == 0 || j + tempx == GRID_W - 1)) //touching wall
				score += 2;
			
			if(!(j + tempx == 0 || j + tempx == GRID_W - 1) && (grid[i + tempy][j + tempx + 1] != 0))  //touching adjacent piece (from the left)
				score += 2;

			if(!(j + tempx == 0 || j + tempx == GRID_W - 1) && grid[i + tempy][j + tempx - 1] != 0) //touching adjacent piece (from the right)
				score += 2;

		}
	}

	return score;
}

//draw board
void TetrisBoard::draw(SDL_Surface* screen, TTF_Font* font)
{
	const int TILEW = 20;
	int xStart = 120;
	int yStart = 40;
	int nextPieceOffset = 20;
	int heldPieceOffset = 20 + 4 * TILEW;

	SDL_Rect offset;
	string s;
	char tempString[10];

	Uint32 c_white		= SDL_MapRGB(screen->format, 255,255,255);
	Uint32 c_cyan		= SDL_MapRGB(screen->format,  32,255,255);
	Uint32 c_yellow		= SDL_MapRGB(screen->format, 255,255, 32);
	Uint32 c_purple		= SDL_MapRGB(screen->format, 255, 32,255);
	Uint32 c_green		= SDL_MapRGB(screen->format,  32,255, 32);
	Uint32 c_red		= SDL_MapRGB(screen->format, 255, 32, 32);
	Uint32 c_blue		= SDL_MapRGB(screen->format,  32, 32,255);
	Uint32 c_orange		= SDL_MapRGB(screen->format, 255,128, 32);
	Uint32 c_drkRed		= SDL_MapRGB(screen->format, 225, 16, 16);
	Uint32 c_gray		= SDL_MapRGB(screen->format, 150,150,150);
	Uint32 c_drkGray	= SDL_MapRGB(screen->format, 100,100,100);
	Uint32 c_black		= SDL_MapRGB(screen->format,   0,  0,  0);
	SDL_Color drkRed = {225, 16, 16};

	
	//clear screen...
	Draw_FillRect(screen, 0, 0, screen->w, screen->h, c_black);

	// draw frame
	Draw_Rect(screen, xStart, yStart, TILEW*GRID_W + 1, TILEW*(GRID_H - 2), c_drkRed);


	// draw grid
	for(int i = TILEW; i < TILEW*GRID_W; i+=TILEW)
	{
		Draw_Line(screen, i + xStart, yStart + 1, i + xStart, yStart + TILEW*(GRID_H-2) - 1, c_drkGray);
	}
	for(int i = TILEW; i < TILEW*(GRID_H-2); i += TILEW)
	{
		Draw_Line(screen, xStart + 1, yStart + i, xStart + TILEW*GRID_W - 1, yStart + i, c_drkGray);
	}

	//draw ghost piece

	clearPieceFromBoard();
	int ghosty = pos_y;
	while(!checkCollision(pos_x, ++ghosty));
	int temp = pos_y;
	pos_y = ghosty-1;
	drawPieceToBoard(-1);

	pos_y = temp;
	drawPieceToBoard();
	

	
	//draw next piece
	nextPiece->draw(screen, xStart+TILEW*GRID_W+nextPieceOffset, yStart, TILEW);
	Draw_Rect(screen, xStart + TILEW*GRID_W + nextPieceOffset - 4, yStart, TILEW*4 + 8, TILEW*4, c_drkRed);
	
	offset.x = xStart + TILEW*GRID_W + nextPieceOffset + 8;
	offset.y = yStart + TILEW*4 + 4;
	SDL_Surface* nextText = TTF_RenderText_Solid( font, "next", drkRed);
	SDL_BlitSurface(nextText, NULL, screen, &offset );
	SDL_FreeSurface(nextText);

	//draw hold piece
	if(heldPiece != NULL)
		heldPiece->draw(screen, xStart - heldPieceOffset, yStart, TILEW);
	Draw_Rect(screen, xStart - heldPieceOffset - 4, yStart, TILEW*4 + 8, TILEW*4, c_drkRed);

	offset.x = xStart - heldPieceOffset + 4;
	offset.y = yStart + TILEW*4 + 4;
	SDL_Surface* holdText = TTF_RenderText_Solid( font, "hold", drkRed);
	SDL_BlitSurface(holdText, NULL, screen, &offset );
	SDL_FreeSurface(holdText);

	//draw stats
	offset.x = xStart + TILEW*GRID_W + nextPieceOffset ;
	offset.y = yStart + TILEW*GRID_H / 2;
	
	s = "lines: ";
	s += itoa(linesCleared, tempString, 10);
	SDL_Surface* lineText = TTF_RenderText_Solid( font, s.c_str(), drkRed);
	SDL_BlitSurface(lineText, NULL, screen, &offset);
	SDL_FreeSurface(lineText);

	offset.y += 30;
	s = "max: ";
	s += itoa(highScore, tempString, 10);
	SDL_Surface* levelText = TTF_RenderText_Solid( font, s.c_str(), drkRed);
	SDL_BlitSurface(levelText, NULL, screen, &offset);
	SDL_FreeSurface(levelText);
	

	// fill in grid
	for(int i = 2; i < GRID_H; i++)
	{
		for(int j = 0; j < GRID_W; j++)
		{
			Uint32 temp;
			switch(grid[i][j])
			{
			case -1:
				temp = c_gray;
				break;
			case 1:
				temp = c_cyan;
				break;
			case 2:
				temp = c_yellow;
				break;
			case 3:
				temp = c_purple;
				break;
			case 4:
				temp = c_green;
				break;
			case 5:
				temp = c_red;
				break;
			case 6:
				temp = c_blue;
				break;
			case 7:
				temp = c_orange;
				break;
			default:
				temp = c_black;
				break;
			}

			Draw_FillRect(screen, xStart + 1 + TILEW*j, yStart + 1 + TILEW*(i-2), TILEW - 2, TILEW - 2, temp);
		}
	}

	//delete ghost piece (we don't want it affecting game behavior)
	if(pos_y != ghosty)
	{
		int temp = pos_y;
		pos_y = ghosty-1;
		drawPieceToBoard(0);
		pos_y = temp;
	}

}

void TetrisBoard::clearPieceFromBoard()
{
	for (int i = 0; i < (*currentPiece).getDim(); i++)
		for(int j = 0; j < (*currentPiece).getDim(); j++)
		{
			if (currentPiece->getShape()[i][j] > 0)
				grid[i + pos_y][j + pos_x] = 0;
		}
}

void TetrisBoard::drawPieceToBoard(int glyph)
{
	for (int i = 0; i < (*currentPiece).getDim(); i++)
		for(int j = 0; j < (*currentPiece).getDim(); j++)
		{
			if (currentPiece->getShape()[i][j] > 0)
				grid[i + pos_y][j + pos_x] = glyph;
		}
}
void TetrisBoard::drawPieceToBoard()
{
	/*for (int i = 0; i < (*currentPiece).getDim(); i++)
		for(int j = 0; j < (*currentPiece).getDim(); j++)
		{
			if (currentPiece->getShape()[i][j] > 0)
				grid[i + pos_y][j + pos_x] = currentPiece->getCol();
		}*/
	drawPieceToBoard(currentPiece->getCol());
}
void TetrisBoard::print()
{
	for (int i = 0; i < GRID_H; i++)
	{
		for (int j = 0; j < GRID_W; j++)
			cout << grid[i][j];
		cout << endl;
	}
}
