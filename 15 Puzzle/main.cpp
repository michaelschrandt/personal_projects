/* Michael Schrandt
** 7/1/2010
**
** 15 Puzzle: The game starts with a shuffled board of tiles labled
**                    1 - 15 and one empty piece. The object of the game is
**                    "slide" each piece through the empty piece to return
**                     the puzzle to its original order (1 in the upper left corner,
**                     blank space in the lower right corner.) The program
**                     features a timer which starts when the first move is made,
**                     a counter for each move made, a reset button and a quit
**                     button.
**
*/

#include <allegro.h>    //uses the allegro library
#include <ctime>          // needed to keep track of time
#include "System.h"    // handles the "system" (allegro initialization, the window, etc)
#include "TileShop.h" // handles the collection of tiles

const int HEIGHT = 576; //height of the screen
const int WIDTH = 512;  // width of the screen
volatile long speed_counter = 0;
void increment_speed_counter();
void initialize(TileShop&); //randomely shuffles the board and resets the counters
bool firstClick = false; //needed for when the program will set the initial time
time_t currentSeconds;
time_t startSeconds; // time in seconds as the first move is made
int totalSeconds;
bool quit = false;

//TOO MUCH STUFF IN HERE
//TO DO: Create a "game" class or something that I can clean
// most of this up with.
int main()
{
    System system(WIDTH, HEIGHT, false);
    system.setup();
    TileShop tiles;

    //regulate FPS
    LOCK_VARIABLE(speed_counter);
    LOCK_FUNCTION(increment_speed_counter);
    install_int_ex(increment_speed_counter, BPS_TO_TIMER(10));//Set our BPS

    //Load pictures
    BITMAP* information = load_bitmap("bar.bmp", NULL);
    BITMAP* bar = create_sub_bitmap(information, 0, 0, 512, 64);
    BITMAP* winPic = create_sub_bitmap(information, 512, 0, 256, 64);
    BITMAP* buffer = create_bitmap(WIDTH, HEIGHT);

    //initialize tiles for the first time
    initialize(tiles);

    //Game loop
    while (!key[KEY_ESC] && quit == false)
    {
        //FPS things go in here
        while (speed_counter > 0)
        {
            //if the left mouse button is pressed down
            if (mouse_b & 1)
            {
                //if the cursor is on the reset button
                if (mouse_x >= 430 && mouse_x <= 510
                      && mouse_y >= 520 && mouse_y <= 545)
                      {
                          initialize(tiles);
                      }
                //if the cursor is on the quit button
                else if (mouse_x>= 452 && mouse_x <= 510
                      && mouse_y >= 546 && mouse_y <= 570)
                      {
                          quit = true;
                      }
                //if the tiles are not in the correct order and the cursor
                // is within the tile section of the window
                else if (!tiles.solved() && mouse_x <= 512 && mouse_y <= 512)
                {
                    tiles.moveTile(mouse_x, mouse_y);
                    //if this is the first click
                    if (firstClick == false)
                    {
                        //don't run this more than once per initialization();
                        firstClick = true;
                        //set a starting time in seconds
                        startSeconds = time (NULL);
                    }
                }
            }
            speed_counter--;
        }

        //Timer updating
        if  (firstClick == true && !tiles.solved())
        {
            currentSeconds = time (NULL);
            totalSeconds = currentSeconds - startSeconds;
        }

        //Draw stuff to the buffer
        blit(bar, buffer, 0, 0, 0, 512, bar->w, bar->h);
        textprintf_ex(buffer, font, 100, 520, makecol(164,80,43), -1, "%d:%d", totalSeconds / 60, totalSeconds % 60);
        textprintf_ex(buffer, font, 100, 555, makecol(164,80,43), -1, "%d", tiles.moves);
        tiles.drawBoard(buffer);

        //draw the "You Win!" bitmap if the tiles are correctly ordered
        if (tiles.solved())
        {
            blit(winPic, buffer, 0, 0, 128, 224, 256, 64);
        }

        //draw the mouse and finally the buffer to the screen
        show_mouse(buffer);
        blit(buffer, screen, 0, 0, 0, 0, WIDTH, HEIGHT);
        clear_bitmap(buffer);
    }

    //Clean up
    show_mouse(NULL);
    destroy_bitmap(winPic);
    destroy_bitmap(bar);
    destroy_bitmap(information);
    destroy_bitmap(buffer);
    return 0;
}
END_OF_MAIN();

//takes the address of the TileShop being used and
// randomly shuffles the board and resets timers and stats
void initialize(TileShop &tiles)
{
    tiles.randomizeTiles();
    tiles.moves = 0;
    firstClick = false;
    totalSeconds = 0;
}


//generic speed loop
void increment_speed_counter()
{
  speed_counter++; // This will just increment the speed counter by one. :)
}
END_OF_FUNCTION(increment_speed_counter);
