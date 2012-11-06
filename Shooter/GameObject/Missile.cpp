#include "Missile.h"


Missile::Missile()
{
}

Missile::Missile(float x, float y, bool positive, EnumMissiles type, sf::Sound* s)
{
	//set sprite
	sprite.setTexture(game.bulletspritesheet);
	sprite.setTextureRect(sf::IntRect((int)type * BULLET_SPRITE_W, 0, BULLET_SPRITE_W, BULLET_SPRITE_W)); //((int)type+1) * BULLET_SPRITE_W - 1 , BULLET_SPRITE_W - 1));

	this->x = x;
	this->y = y;
	this->positive = positive;
	if(!positive)
	{
		sprite.setScale(-1.f, 1.f);
		sprite.setOrigin(BULLET_SPRITE_W, 0);
	}


	//set sound
	this->sound = s;
	
	//set impact behavior
	switch( type )
	{
	case MISSILE_NORMAL:
		impactBehavior = new ImpactBehavior;
		break;
	case MISSILE_FIRE:
		impactBehavior = new FireImpactBehavior;
		break;
	case MISSILE_ICE:
		impactBehavior = new IceImpactBehavior;
		break;
	case MISSILE_ELECT:
		impactBehavior = new ElectricImpactBehavior;
		break;
	case MISSILE_DRILL:
		impactBehavior = new DrillImpactBehavior;
		break;
	default:
		impactBehavior = new ImpactBehavior;
		break;
	}
	
	s->play();
}


Missile::~Missile()
{
	delete impactBehavior;
}

void Missile::update()
{
	if(positive)
		this->x += MISSILE_VELOCITY * game.time.asSeconds();
	else
		this->x -= MISSILE_VELOCITY * game.time.asSeconds();
}

bool Missile::hit(Map& map)
{
	if(realY()/TILE_W < 0 || realY()/TILE_W >= map.getHeight() ||
		realX()/TILE_W < 0 || realX()/TILE_W >= map.getWidth())
		return true;
	return impactBehavior->hit(map, (int) realX()/TILE_W, (int) realY()/TILE_W);
}


void Missile::draw(sf::Rect<int> &view)
{
	sprite.setPosition(x - view.left, y - view.top);

	game.window.draw(sprite);
}

void Missile::draw()
{
	sprite.setPosition(x, y);
	game.window.draw(sprite);
}