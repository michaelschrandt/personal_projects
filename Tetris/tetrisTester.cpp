#include "tetrisBoard.h"
#include "Timer.h"
#include "SDL.h"
#include "SDL_ttf.h"
#include <iostream>

const int SCREEN_W = 540;
const int SCREEN_H = 480;
const int FPS = 40;


using namespace std;
int main( int argc, char** argv)
{
	SDL_Surface* screen = NULL;
	TTF_Font* font = NULL;
	
	SDL_Init( SDL_INIT_EVERYTHING );
	
	screen = SDL_SetVideoMode( SCREEN_W, SCREEN_H, 32, SDL_SWSURFACE );
	SDL_WM_SetCaption( "Tetris", NULL);
	
	TTF_Init();
	font = TTF_OpenFont( "west_england.ttf", 24);
	
	//initialize
	TetrisBoard game;
	Timer fps;
	int frame = 0;
	int keyFrame = 0;
	SDL_Event event;
	bool quit = false;
	bool FPSCap = true;

	while (quit == false && game.isAlive())
	{
		fps.start();
		if (SDL_PollEvent( &event ))
		{

			if (event.type == SDL_KEYDOWN)
			{
				if (event.key.keysym.sym == SDLK_ESCAPE)
				{
					fps.stop();
					quit = true;
				}
				else if(event.key.keysym.sym == SDLK_SPACE)
				{
					FPSCap = !FPSCap;
				}
				else
				{
					game.handleInput(event);
					keyFrame = -4; // errors can sometimes lead to awesome hacks
				}
			}
			else if (event.type == SDL_QUIT)
			{
				fps.stop();
				quit = true;
			}
		}

		if(keyFrame > 0)
		{
			Uint8* keystates = SDL_GetKeyState(NULL);
			game.handleInput(keystates);
		}


		//FIX LEVEL PROGRESSION
		//if(game.getLevel() >= 10)
			//game.update();

		//else if(frame%(FPS - game.getLevel()*4) == 0)
			game.update();

		if(!game.isAlive())
		{
			game.setHighScore();
			game = TetrisBoard();
			continue;
		}
		game.draw(screen, font);
		SDL_Flip(screen);
		
		frame++;
		keyFrame++;
		if(FPSCap && fps.getTicks() < 1000.0 / FPS)
			SDL_Delay( ( 1000.0 / FPS) - fps.getTicks() );
	}


	TTF_CloseFont( font );
	TTF_Quit();
	SDL_Quit();

	return 0;
}
