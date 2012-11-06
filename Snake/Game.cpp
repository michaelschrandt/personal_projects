#include "Game.h"

Game::Game()
{
    init();
}

Game::~Game()
{
    destroy_bitmap(buffer);
}

void Game::init()
{
    done = false;
    buffer = create_bitmap(640, 480);
}

void Game::update()
{
    if (key[KEY_UP])
        snake.direction = UP;
    else if (key[KEY_DOWN])
        snake.direction = DOWN;
    else if (key[KEY_LEFT])
        snake.direction = LEFT;
    else if (key[KEY_RIGHT])
        snake.direction = RIGHT;

    //returns -1 if the head is out of bounds
    if (snake.moveSnake() == -1)
        done = true;

    int snakeX = snake.snake[0].x;
    int snakeY = snake.snake[0].y;

    if ((snakeX >= (apple.x) && snakeX < apple.x + 32) &&
          (snakeY >= (apple.y) && snakeY < apple.y + 32))
    {
        snake.addLink();
        apple.moveApple();
    }
}

void Game::drawBoard()
{
    const int GRID_PX = 32;

    apple.drawApple(buffer);
    snake.drawSnake(buffer);


    for (int i = 0; i < 640; i += GRID_PX)
    {
        line(buffer, i, 0, i, 480, makecol(0,0,255));
    }
    for (int i = 0; i < 480; i += GRID_PX)
    {
        line(buffer, 0, i, 640, i, makecol(0,0,255));
    }

    blit(buffer, screen, 0, 0, 0, 0, 640, 480);
    clear_bitmap(buffer);
}
