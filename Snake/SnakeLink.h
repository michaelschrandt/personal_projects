#pragma once
#include <allegro.h>

class SnakeLink
{
    private:

    public:
        int x, y;
        SnakeLink();
        SnakeLink(int, int);
        int getX() { return x;}
        int getY() { return y;}
        void drawLink(BITMAP*);
        void moveLink(int);
        void moveLink(SnakeLink);
};
