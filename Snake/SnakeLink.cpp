// Michael Schrandt

#include "SnakeLink.h"

SnakeLink::SnakeLink()
{

}

SnakeLink::SnakeLink(int xVal, int yVal)
{
    x = xVal;
    y = yVal;

}

void SnakeLink::drawLink(BITMAP* buffer)
{
    rectfill(buffer, x, y, x + 32, y + 32, makecol(0, 255, 0));
}

//SnakeLink other could be replaced with x/y coords of the snake ahead of the
//  current link
void SnakeLink::moveLink(SnakeLink other)
{
    x = other.x;
    y = other.y;
}

//this is for the head
// enum dir {UP, DOWN, LEFT, RIGHT};
void SnakeLink::moveLink(int dir)
{
    switch (dir)
    {
        case 0 :
            y -= 32;
            break;

        case 1 :
            y += 32;
            break;

        case 2 :
            x -= 32;
            break;

        case 3 :
            x += 32;
            break;

        default :
            ;
    }

}
