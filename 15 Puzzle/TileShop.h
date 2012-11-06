#include "Tile.h"
#ifndef TILESHOP_H
#define TILESHOP_H

class TileShop
{
    public:
        BITMAP* pictures;
        SAMPLE* click;
        int moves;
        Tile tiles[16];
        TileShop();
        ~TileShop();
        void moveTile(int, int);
        void randomizeTiles();
        void drawBoard(BITMAP*);
        void sortTiles();
        bool solved();
};

#endif // TILESHOP_H
