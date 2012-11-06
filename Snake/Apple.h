#pragma once
#include <allegro.h>

class Apple
{
    private:

    public:
        int x, y;
        Apple();
        void moveApple();
        bool eaten;
        void drawApple(BITMAP* buffer);

};
