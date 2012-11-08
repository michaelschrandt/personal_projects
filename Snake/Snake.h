// Michael Schrandt
//
// A Snake is a collection of snake links.

#pragma once
#include "SnakeLink.h"

enum dir {UP, DOWN, LEFT, RIGHT};

class Snake
{
    private:
        int length;
        int previousX, previousY;

    public:
        Snake();
        ~Snake();
        dir direction;
        SnakeLink* snake;
        int getLength() { return length;}
        int moveSnake();
        void drawSnake(BITMAP* buffer);
        void addLink();

};
