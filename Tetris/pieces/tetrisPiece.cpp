#include "tetrisPiece.h"

void TetrisPiece::draw(SDL_Surface* screen, int x, int y, int w)
{
	
	Draw_FillRect(screen, x, y, w*4, w*4, 0);
	x += -w/2*getDim() + 2*w;
	y += w;
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


	for(int i = 0; i < getDim(); i++)
	{
		for(int j = 0; j < getDim(); j++)
		{
			Uint32 temp;
			switch(this->getShape()[i][j] * getCol())
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

			Draw_FillRect(screen, x + w*j, y + w*(i), w - 2, w - 2, temp);
		}
	}
}