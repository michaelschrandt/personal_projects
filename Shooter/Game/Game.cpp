#include "Game.h"
#include <iostream>

Game::Game() :	window(sf::VideoMode(SCREEN_W, SCREEN_H), "The Game")
{
	init();
}

Game::~Game()
{
	deinit();
}

void Game::init()
{
	window.setKeyRepeatEnabled(false);
	window.setFramerateLimit(FPS);
	font = sf::Font::getDefaultFont();


	bulletSelectTexture.loadFromFile("resources/bulletSelect.png");
		//fix this
		sf::Image j;
		j = bulletSelectTexture.copyToImage();
		j.createMaskFromColor(sf::Color(255, 0, 255));
		bulletSelectTexture.loadFromImage(j);
	bulletHudTexture.loadFromFile("resources/bulletHud.png");
	bulletSelect.setTexture(bulletSelectTexture);
	bulletHud.setTexture(bulletHudTexture);

	//init bullets
	bulletSoundBuffers = new sf::SoundBuffer[NUM_MISSILES];
	bulletSounds = new sf::Sound[NUM_MISSILES];
	bulletSoundBuffers[MISSILE_NORMAL].loadFromFile("resources/shootReg.wav");
	bulletSoundBuffers[MISSILE_FIRE].loadFromFile("resources/shootFire.wav");
	bulletSoundBuffers[MISSILE_ICE].loadFromFile("resources/shootIce.wav");
	bulletSoundBuffers[MISSILE_ELECT].loadFromFile("resources/shootLightening.wav");
	bulletSoundBuffers[MISSILE_DRILL].loadFromFile("resources/shootDrill.wav");

	for(int i = 0; i < NUM_MISSILES; i++)
		bulletSounds[i].setBuffer(bulletSoundBuffers[i]);

	if(!bulletspritesheet.loadFromFile("resources/bullets.png"))
	{
		std::cout << "Could not open bullets.png" << std::endl;
		exit(0);
	}

		//fix this
		sf::Image i;
		i = bulletspritesheet.copyToImage();
		i.createMaskFromColor(sf::Color(255, 0, 255));
		bulletspritesheet.loadFromImage(i);

}

void Game::deinit()
{
	delete [] bulletSounds;
	delete [] bulletSoundBuffers;
}

//GAME LOOP
void Game::play()
{
	clock.restart();
	machine.draw();

	while(window.isOpen())
	{
		time = clock.restart();

		//handle input
		sf::Event ev;
		while(window.pollEvent(ev))
		{
			if(ev.type == sf::Event::Closed)
				window.close();

			//update keymap
			else if(ev.type == sf::Event::KeyPressed)
				keymap.press(keymap.enumify(ev.key.code));
			else if(ev.type == sf::Event::KeyReleased)
				keymap.release(keymap.enumify(ev.key.code));

			//handle additional input
			machine.handleEvents(ev);
		}

		//do stuff
		machine.logic();
		machine.changeState();

		//render
		window.clear();
		machine.draw();
		displayfps();

		window.display();

	}
}

void Game::displayfps()
{
	static float time = 0;
	static float fps = 1/ this->time.asSeconds();

	if(DEBUG)
	{
		sf::Text text;

		time += this->time.asSeconds();
		if((int)time == 1)
		{
			fps = 1/ (this->time.asSeconds());
			time = 0;
		}
		std::stringstream out;
		out << fps;
		std::string s = "fps: " + out.str();

		//window.setTitle("The Game: " + s);

		text.setString(s);

		window.draw(text);
	}
}