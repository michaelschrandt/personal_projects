#include "StateOverworld.h"
#include "StateMachine.h"

StateOverworld::StateOverworld(StateMachine* p) : parent(p), map("resources/testMap.txt") , view(sf::FloatRect(0, 0, (float)VISIBLE_W, (float)VISIBLE_H))//view(0, 0, SCREEN_W, SCREEN_H)
{

	initKeyboard();
}

void StateOverworld::handleEvents(sf::Event& ev)
{
	if(ev.type == sf::Event::KeyPressed)
	{	
		switch(ev.key.code)
		{
		case sf::Keyboard::Up:
			player.jump();
			//player.y_vel -= PLAYER_VEL;
			break;
		case sf::Keyboard::Down:
			//player.y_vel += PLAYER_VEL;
			break;
		case sf::Keyboard::Right:
			player.x_vel += PLAYER_VEL;
			break;
		case sf::Keyboard::Left:
			player.x_vel -= PLAYER_VEL;
			break;
		case sf::Keyboard::Z:
			bullets.push_back(player.shoot());
			break;
		case sf::Keyboard::X:
			player.changeBullets();
			break;
		}
	}
	else if(ev.type == sf::Event::KeyReleased)
	{
		switch(ev.key.code)
		{
		case sf::Keyboard::Up:
			if(player.y_vel < 0)
				player.y_vel /= 3;
			//player.y_vel += PLAYER_VEL;
			break;
		case sf::Keyboard::Down:
			//player.y_vel -= PLAYER_VEL;
			break;
		case sf::Keyboard::Right:
			player.x_vel -= PLAYER_VEL;
			break;
		case sf::Keyboard::Left:
			player.x_vel += PLAYER_VEL;
			break;
		}
	}


}

//call this when switching states
void StateOverworld::initKeyboard()
{
	//if(game.keymap.isPressed(KEY_UP))
	//	player.y_vel -= PLAYER_VEL;
	//if(game.keymap.isPressed(KEY_DOWN))
	//	player.y_vel += PLAYER_VEL;
	if(game.keymap.isPressed(KEY_RIGHT))
		player.x_vel += PLAYER_VEL;
	if(game.keymap.isPressed(KEY_LEFT))
		player.x_vel -= PLAYER_VEL;
}

void StateOverworld::correctView()
{
	sf::Vector2f newCenter = player.center();
	
	if(newCenter.x < VISIBLE_W / 2.0)
		newCenter.x = (int) VISIBLE_W / 2;
	else if(newCenter.x > map.getWidth() * TILE_W - VISIBLE_W / 2)
		newCenter.x = (int) (map.getWidth() * TILE_W - VISIBLE_W / 2);

	if(newCenter.y < VISIBLE_H / 2)
		newCenter.y = (int) VISIBLE_H / 2;
	else if (newCenter.y > map.getHeight()*TILE_W - VISIBLE_H / 2)
		newCenter.y = (int) (map.getHeight() * TILE_W - VISIBLE_H / 2);
	

	view.setCenter(newCenter);
	/*
	
	if(view.Left < 0)
	{
		view.Left = 0;
		view.Right = SCREEN_W;
	}
	else if(view.Right > map.getWidth() * 32)
	{
		view.Right = map.getWidth() * 32;
		view.Left = view.Right - SCREEN_W;
	}
	if(view.Top < 0)
	{
		view.Top = 0;
		view.Bottom = SCREEN_H;
	}
	else if(view.Bottom > map.getHeight() * 32)
	{
		view.Bottom = map.getHeight() * 32;
		view.Top = view.Bottom - SCREEN_H;
	}

	*/
}

void StateOverworld::updateBullets()
{
	//update bullets
	vector<int> bulletsToRemove;
	vector<Missile*>::iterator begin;
	int index = 0;
	for(begin = bullets.begin(); begin < bullets.end(); begin++ )
	{
		(*begin)->update();
		if((*begin)->hit(map))
			bulletsToRemove.push_back(index);
		else
			index++;
	}

	//remove bullets that have collided with the map
	while(bulletsToRemove.size() > 0)
	{
		delete *(bullets.begin() + *bulletsToRemove.begin());
		bullets.erase(bullets.begin() + *bulletsToRemove.begin());
		bulletsToRemove.erase(bulletsToRemove.begin());
	}
}

void StateOverworld::logic()
{
	player.update(this);
//DEBUG
//player.move(player.x_vel * game.time.asSeconds(), player.y_vel* game.time.asSeconds());
	correctView();
	updateBullets();
	map.update();
}

void StateOverworld::drawBullets()
{
	vector<Missile*>::iterator begin;
	for(begin = bullets.begin(); begin < bullets.end(); begin++)
		//(*begin)->draw(view);
		(*begin)->draw();
}

void StateOverworld::draw()
{
	view.setViewport(sf::FloatRect(0, 0, (float)VISIBLE_W/SCREEN_W, (float)VISIBLE_H/SCREEN_H));
	game.window.setView(view);
	map.draw();
	drawBullets();
	//player.draw(view);
	player.draw();
	game.window.setView(game.window.getDefaultView());
	drawHud();
}

void StateOverworld::drawHud()
{
	int BULLET_HUD_Y = 445;
	int BULLET_HUD_X = 10;
	game.bulletSelect.setPosition(BULLET_HUD_X + ((int)player.getBulletType())*(25-1), BULLET_HUD_Y);
	game.bulletHud.setPosition(BULLET_HUD_X, BULLET_HUD_Y);
	game.window.draw(game.bulletHud);
	game.window.draw(game.bulletSelect);
}