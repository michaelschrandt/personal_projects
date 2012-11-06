#ifndef SYSTEM_H
#define SYSTEM_H

class System
{
      private:
            int screenHeight, screenWidth;
            bool fullscreen;
      public:
            System(int, int, bool);
            void setup();
};

#endif // SYSTEM_H
