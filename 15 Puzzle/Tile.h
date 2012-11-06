#include <allegro.h>
#ifndef TILE_H
#define TILE_H

class Tile
{
    public:
        BITMAP* picture;
        int number;
        int width, height;
        int x1, y1;
        Tile();
        Tile(int, BITMAP*);
        Tile(int, int, int);
        //~Tile();
        void drawTile(BITMAP*, int, int);
    private:

};


#endif // TILE_H_INCLUDED
