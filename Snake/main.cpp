// Michael Schrandt
//
// Snake Clone

#include "game.h"

volatile long speed_counter = 0;
void increment_speed_counter();

int main()
{
    Game game;
    readkey();
    LOCK_VARIABLE(speed_counter);
    LOCK_FUNCTION(increment_speed_counter);
    install_int_ex(increment_speed_counter, BPS_TO_TIMER(10));//Set our FPS


    while (!key[KEY_ESC] && !game.done)
    {

        while (speed_counter > 0)
        {
            game.update();

            speed_counter--;
        }
        game.drawBoard();

    }
    clear_keybuf();
    readkey();
    return 0;
}
END_OF_MAIN();

void increment_speed_counter()
{
  speed_counter++; 
}
END_OF_FUNCTION(increment_speed_counter);
