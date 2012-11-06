#pragma once
#include <allegro.h>

class System
{
      private:
            int screenHeight, screenWidth;
            bool fullscreen, done;
      public:
            System();
            void setup();
            int getScreenHeight(){ return screenHeight; };
            int getScreenWidth(){ return screenWidth; };
            void toggleFullscreen();
            void quit();
};
