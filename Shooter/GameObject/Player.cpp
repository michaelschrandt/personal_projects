#include "Player.h"
#include "StateOverworld.h"

int i = 0;

Player::Player()
{
	//load spritesheet
	if(!spritesheet.loadFromFile("resources/character2.png"))
	{
		std::cout << "Could not load resources/character.png" << std::endl;
		exit(0);
	}
	
		//fix this
		sf::Image i;
		i = spritesheet.copyToImage();
		i.createMaskFromColor(sf::Color(255, 0, 255));
		spritesheet.loadFromImage(i);

	//initialize char sprite
	sprite.setTexture(spritesheet);
	sprite.setTextureRect(sf::IntRect(0, 0, PLAYER_W, PLAYER_H));
	//spritesheet.createMaskFromImage(sf::Color(255, 0, 255));

	//split up animations from sprite sheet
	int shootOffset = PLAYER_H * 2;
	animations = new sf::Rect<int>*[N_ANIMATIONS];
	frameCount = new int[N_ANIMATIONS];

	frameCount[PLAYER_STAND] = 1;
	frameCount[PLAYER_STAND_SHOOT] = 1;
	frameCount[PLAYER_WALK] = 5;
	frameCount[PLAYER_WALK_SHOOT] = 5;
	frameCount[PLAYER_JUMP] = 1;
	frameCount[PLAYER_JUMP_SHOOT] = 1;
	frameCount[PLAYER_FALL] = 1;
	frameCount[PLAYER_FALL_SHOOT] = 1;

	animations[PLAYER_STAND] = new sf::Rect<int>[frameCount[PLAYER_STAND]];
	animations[PLAYER_STAND_SHOOT] = new sf::Rect<int>[frameCount[PLAYER_STAND]];
	animations[PLAYER_WALK] = new sf::Rect<int>[frameCount[PLAYER_WALK]];
	animations[PLAYER_WALK_SHOOT] = new sf::Rect<int>[frameCount[PLAYER_WALK]];
	animations[PLAYER_JUMP] = new sf::Rect<int>[frameCount[PLAYER_JUMP]];
	animations[PLAYER_JUMP_SHOOT] = new sf::Rect<int>[frameCount[PLAYER_JUMP]];
	animations[PLAYER_FALL] = new sf::Rect<int>[frameCount[PLAYER_FALL]];
	animations[PLAYER_FALL_SHOOT] = new sf::Rect<int>[frameCount[PLAYER_FALL]];

	animations[PLAYER_STAND][0] = sf::Rect<int>(0, 0, PLAYER_W, PLAYER_H);
	animations[PLAYER_STAND_SHOOT][0] = sf::Rect<int>(0, shootOffset, PLAYER_W, PLAYER_H);
	for(int i = 0; i < 5; i++)
	{
		animations[PLAYER_WALK][i] = sf::Rect<int>((i+1)*PLAYER_W, 0, (PLAYER_W), PLAYER_H);
		animations[PLAYER_WALK_SHOOT][i] = sf::Rect<int>((i+1)*PLAYER_W, shootOffset, (PLAYER_W), PLAYER_H);
	}
	animations[PLAYER_JUMP][0] = sf::Rect<int>(0, PLAYER_H, PLAYER_W, PLAYER_H);
	animations[PLAYER_JUMP_SHOOT][0] = sf::Rect<int>(0, shootOffset + PLAYER_H, PLAYER_W, PLAYER_H);
	animations[PLAYER_FALL][0] = sf::Rect<int>(PLAYER_W, PLAYER_H, PLAYER_W, PLAYER_H);
	animations[PLAYER_FALL_SHOOT][0] = sf::Rect<int>(PLAYER_W, shootOffset + PLAYER_H, PLAYER_W, PLAYER_H);

	//initialize data
	movingRight = true;
	falling = true;
	nextState = PLAYER_STAND;
	state = PLAYER_STAND;
	bulletType = MISSILE_NORMAL;
	frame = 0;
	x_vel = 0;
	y_vel = 0;
	x = 30;
	y = 300;
	collisionBox.height = PLAYER_COL_H;
	collisionBox.width = PLAYER_COL_W;
	setCollisionBox();
}

Player::~Player()
{
	for(int i = 0; i < N_ANIMATIONS; i++)
		delete [] animations[i];
	delete [] animations;
	delete [] frameCount;
}

sf::Vector2<float> Player::center()
{
	return sf::Vector2<float>((int) (x + PLAYER_W/2),(int) (y + PLAYER_H/2));
}

void Player::update(StateOverworld* gamestate)
{
	static float bulletTimer = 0;

	move(gamestate->getMap());

	if(game.keymap.isPressed(KEY_Z))
	{
		bulletTimer += game.time.asSeconds();
		if(bulletTimer > BULLET_REFRESH)
		{
			gamestate->addObject(shoot());
			bulletTimer = 0;
		}
	}
	else
		bulletTimer = 0;
}

void Player::move(Map &map)
{
	//adjust sprite
	if(movingRight && x_vel < 0)
	{
		movingRight = false;
		sprite.setScale(-1.f, 1.f);
		sprite.setOrigin(PLAYER_W, 0);
		//sprite.flipX(true);
	}
	else if(!movingRight && x_vel > 0)
	{
		movingRight = true;
		sprite.setScale(1.f, 1.f);
		sprite.setOrigin(0, 0);
		//sprite.FlipX(false);
	}

	float gravityAdjustment = GRAVITY * game.time.asSeconds();
	y_vel += gravityAdjustment;
	float newx = x_vel * game.time.asSeconds();
	float newy = y_vel * game.time.asSeconds();

	//move sprite (x)
	move(newx, 0);
	if(newx != 0)
		while(checkCollision(map))
			move(-1*(newx/abs(newx)), 0);

	//move sprite (y)
	move(0, newy);
	while(checkCollision(map))
	{
		y_vel = 0;
		if(newy > 0)
			falling = false;

		move(0, -1*(newy/abs(newy)));
	}
	
	//check if falling
	move(0, 1);
	if(!checkCollision(map))
		falling = true;
	else
		y_vel = 0;
	move(0, -1);

	//set state
	if(falling)
	{
		if(y_vel > 0)
			setNextState(PLAYER_FALL);
		else
			setNextState(PLAYER_JUMP);
	}
	else
	{
		if(x_vel == 0)
			setNextState(PLAYER_STAND);
		else
			setNextState(PLAYER_WALK);
	}

	switchState();
}

void Player::move(float x_offset, float y_offset)
{
	x += x_offset;
	y += y_offset;

	setCollisionBox();
}

bool Player::checkCollision(Map &map)
{
	int x1 = (int) collisionBox.left/TILE_W;
	int x2 = (int) (collisionBox.left + collisionBox.width)/TILE_W;
	int y1 = (int) collisionBox.top/TILE_W;
	int y2 = (int) (collisionBox.top + collisionBox.height)/TILE_W;
	if(collisionBox.left < 0 || x2 >= map.getWidth())
		return true;
	if(collisionBox.top < 0 || y2 >= map.getHeight())
		return true;

	//check all possible tiles
	for(int i = y1; i <= y2; i++)
	{
		for(int j = x1; j <= x2; j++)
		{
			if(map[i][j]->isSolid())
				return true;
		}
	}

	return false;
}

void Player::setNextState(PlayerState nextState)
{
	this->nextState = nextState;
}

void Player::switchState()
{

	if(state/2 != nextState/2)
	{
		frame = 0;
		state = nextState;
	}

	if(game.keymap.isPressed(KEY_Z))
		state = PlayerState( int(state)/2 * 2 + 1 );
	else
		state = PlayerState( int(state)/2 * 2);

}

void Player::jump()
{
	if(!falling)
		y_vel -= JUMP_VEL;
		falling = true;
}

Missile *Player::shoot()
{
	Missile * m = new Missile(x+PLAYER_W/3, y+PLAYER_H/3, movingRight, bulletType, &(game.bulletSounds[bulletType]));
	return m;
}

void Player::changeBullets()
{
	bulletType = (EnumMissiles)((bulletType + 1) % NUM_MISSILES);
}

void Player::setCollisionBox()
{
	if(game.keymap.isPressed(KEY_Z))
	{
		int i = 4;
	}
	collisionBox.left = x + (PLAYER_W - PLAYER_COL_W)/2;
	//collisionBox.Right = collisionBox.left + PLAYER_COL_W;

	collisionBox.top = y + (PLAYER_H - PLAYER_COL_H)/2;
	//collisionBox.Bottom = collisionBox.Top + PLAYER_COL_H;
}

void Player::draw(sf::Rect<int> &view)
{
	static float time = 0;

	if(frame >= frameCount[state])
		frame = 0;
	sprite.setTextureRect(animations[state][frame]);
	
	time += game.time.asSeconds();
	if(time > .05)
	{
		time = 0;
		frame++;
	}
	sprite.setPosition(x - view.left, y - view.top);

	game.window.draw(sprite);
}

void Player::draw()
{
	static float time = 0;

	if(frame >= frameCount[state])
		frame = 0;
	sprite.setTextureRect(animations[state][frame]);
	
	time += game.time.asSeconds();
	if(time > .05)
	{
		time = 0;
		frame++;
	}

	sprite.setPosition(floor(x), floor(y));

	game.window.draw(sprite);
}