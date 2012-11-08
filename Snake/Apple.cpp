// Michael Schrandt
//
// Apple object: when picked up, snake adds one SnakeLink

#include "Apple.h"
#include <ctime>

Apple::Apple()
{
    moveApple();
    eaten = false;
}

void Apple::moveApple()
{
    srand(time(0));
    x = (rand() % 20) * 32;
    y = (rand() % 15) * 32;
}

void Apple::drawApple(BITMAP* buffer)
{
    rectfill(buffer, x, y, x + 32, y + 32, makecol(255, 0, 0));
}
