#include "TileShop.h"
#include <cstdlib>
#include <ctime>

//default constructor which initializes tiles in order from 1 - 15
TileShop::TileShop()
{
    moves = 0;
    pictures = load_bmp("tiles.bmp", NULL);
    click = load_sample("click.wav");
    for (int i = 0; i < 16; i++)
    {
        tiles[i] = Tile(i, pictures);
    }
    sortTiles();

}

TileShop::~TileShop()
{
    for (int i = 0; i < 16; i++)
    {
        destroy_bitmap(tiles[i].picture);
    }
    destroy_bitmap(pictures);
    destroy_sample(click);
}

//move tiles around randomly
void TileShop::randomizeTiles()
{
    srand(time(0));

    for (int i = 0; i < 1000; i++)
    {
        int rx = rand() % 512;
        int ry = rand() % 512;
        moveTile(rx, ry);
    }


}

//UNFINISHED
//only sorts tiles that are in the
// order from the default constructor
void TileShop::sortTiles()
{
    Tile temp = tiles[0];
    for (int i = 0; i < 15; i++)
    {
        tiles[i] = tiles[i+1];
    }
    tiles[15] = temp;
}


//move a tile to the blank space (if it's next to it)
void TileShop::moveTile(int mx, int my)
{
    Tile temp;
    int amount;
    int x = mx / 128;
    int y = my / 128;
    int indexClicked = x + 4 * y;

    if (indexClicked % 4 != 3 && tiles[indexClicked + 1].number == 0)
        amount = 1;

     else if (indexClicked % 4 != 0 && tiles[indexClicked -1].number == 0)
        amount = -1;

     else if (indexClicked > 3 && tiles[indexClicked - 4].number == 0)
        amount = -4;

     else if (indexClicked < 12 && tiles[indexClicked + 4].number == 0)
         amount = 4;

     else
        amount = 0;
    if (amount != 0)
    {
        temp = tiles[indexClicked];
        tiles[indexClicked] = tiles[indexClicked + amount];
        tiles[indexClicked + amount] = temp;
        play_sample(click, 200, 128, 1000,false );
        moves++;
    }


}


//Check solution
bool TileShop::solved()
{
    for (int i = 0; i < 15; i++)
    {
        if (tiles[i].number != i + 1)
            return false;
    }

    return true;
}

//draw board to the buffer
void TileShop::drawBoard(BITMAP *buffer)
{
    int currentTile = 0;
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++, currentTile++)
        {
            tiles[currentTile].drawTile(buffer, j * 128, i * 128);
        }
    }
}
