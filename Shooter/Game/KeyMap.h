#pragma once

#include "EnumKeys.h"

class KeyMap
{
private:
	bool* keys;
public:
	KeyMap(){ keys = new bool[KEY_LENGTH];	clear();}
	~KeyMap(){ delete [] keys; }

	void clear(){ for(int i = 0; i < KEY_LENGTH; i++) keys[i] = false; }
	
	EnumKeys enumify( sf::Keyboard::Key c)
	{
		switch(c)
				{
				case sf::Keyboard::Up:
					return KEY_UP;
				case sf::Keyboard::Down:
					return KEY_DOWN;
				case sf::Keyboard::Left:
					return KEY_LEFT;
				case sf::Keyboard::Right:
					return KEY_RIGHT;
				case sf::Keyboard::Z:
					return KEY_Z;
				case sf::Keyboard::X:
					return KEY_X;
				case sf::Keyboard::Space:
					return KEY_SPACE;
				case sf::Keyboard::Escape:
					return KEY_ESC;
				default:
					return KEY_LENGTH;
					break;	
				}
	}
	void press(EnumKeys k){ if(k < KEY_LENGTH) keys[k] = true; }
	void release(EnumKeys k){ if(k < KEY_LENGTH) keys[k] = false; }
	bool isPressed(EnumKeys k){ return k < KEY_LENGTH && keys[k]; }
};