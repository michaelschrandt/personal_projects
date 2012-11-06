#pragma once

#include "SFML/System.hpp"
#include "SFML/Graphics.hpp"
#include "SFML/Audio.hpp"
#include "EnumMissile.h"
#include "StateMachine.h"
#include "KeyMap.h"

#include <iostream>
#include <sstream>
#include <string>
const bool DEBUG = false;
const int FPS = 60;
const int GRAVITY = 800;
const int TILE_W = 32;
const int SCREEN_W = 640;
const int SCREEN_H = 480;
const int VISIBLE_W = 640;
const int VISIBLE_H = 440;


class Game
{
public:
	Game();
	~Game();

	void play();
	sf::SoundBuffer* bulletSoundBuffers;
	sf::Sound* bulletSounds;

	sf::Texture bulletSelectTexture;
	sf::Texture bulletHudTexture;
	sf::Texture bulletspritesheet;

	sf::Sprite bulletSelect;
	sf::Sprite bulletHud;

	sf::RenderWindow window;
	sf::Font font;
	sf::Clock clock;
	sf::Time time;
	sf::Rect<int> mapView;
	KeyMap keymap;

private:
	StateMachine machine;

	void displayfps();
	void init();
	void deinit();
};