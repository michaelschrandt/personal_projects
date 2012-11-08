// Michael Schrandt
//
// Game object

#pragma once
#include <allegro.h>
#include "Apple.h"
#include "Snake.h"
#include "System.h"

class Game
{
    private:
        BITMAP* buffer;
        Snake snake;

    public:
        Game();
        ~Game();
        System system;
        Apple apple;
        bool done;
        void init();
        void update();
        void drawBoard();
};
