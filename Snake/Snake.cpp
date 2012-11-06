#include "Snake.h"

Snake::Snake()
{
    length = 5;
    direction = RIGHT;
    previousX = 32;
    previousY = 32 + (32*5);
    snake = new SnakeLink[length];
    snake[0] = SnakeLink(32, 32);
    snake[1] = SnakeLink(32, 32 + (32 * 1));
    snake[2] = SnakeLink(32, 32 + (32 * 2));
    snake[3] = SnakeLink(32, 32 + (32 * 3));
    snake[4] = SnakeLink(32, 32 + (32 * 4));
}

Snake::~Snake()
{
    delete [] snake;
}

int Snake::moveSnake()
{
    previousX = snake[length - 1].x;
    previousY = snake[length - 1].y;
    for (int i = length - 1; i > 0; i-- )
    {
        snake[i].moveLink(snake[i-1]);
    }
    snake[0].moveLink(direction);

    //return -1 if the head is out of bounds
    bool snakeXOutOfBounds = snake[0].x < 0 || snake[0].x > (640 - 32);
    bool snakeYOutOfBounds = snake[0].y < 0 || snake[0].y > (480 - 32);

    if (snakeXOutOfBounds || snakeYOutOfBounds)
    {
        return -1;
    }

    //return 0 otherwise
    else
        return 0;


}

void Snake::drawSnake(BITMAP* buffer)
{
    for (int i = 0; i < length; i++)
    {
        snake[i].drawLink(buffer);
    }
}

void Snake::addLink()
{

    length++;

    //make a new array of the correct size
    SnakeLink* temp = new SnakeLink[length];

    //copy contents of the old array to the temp
    for (int i = 0; i < length - 1; i++)
    {
        temp[i] = SnakeLink(snake[i].x, snake[i].y);
    }

    //delete old snake
    delete [] snake;

    //make a new snake of the correct length
    snake = new SnakeLink[length];

    //copy temp to new snake
    for (int i = 0; i < length - 1; i++)
    {
        snake[i] = SnakeLink(temp[i].x, temp[i].y);
    }

    //delete temp
    delete [] temp;

    //and add the last link
    snake[length - 1] = SnakeLink(previousX, previousY);

}
