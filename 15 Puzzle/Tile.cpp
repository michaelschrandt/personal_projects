#include "Tile.h"
#include <allegro.h>

Tile::Tile(int num, BITMAP* pictures)
{
    number = num;
    picture = create_sub_bitmap(pictures, number * 128, 0, number * 128 + 128, 128);
    width = 128;
    height = 128;
}

Tile::Tile()
{
    number = 0;
    width = 128;
    height = 128;
}

Tile::Tile(int num, int x, int y)
{
    number = num;
    width = x;
    height = y;
}


void Tile::drawTile(BITMAP *buffer, int x, int y)
{
    //what if there is no picture? I should default to rectangles
    // with numbers centered in them.
    blit(picture, buffer, 0, 0, x, y, picture -> w, picture ->h);
}
