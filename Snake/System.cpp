// Michael Schrandt

#include "System.h"

System::System()
{
    setup();
}

// Initialize Allegro
void System::setup()
{
    screenWidth = 640;
    screenHeight = 480;
    done = false;
    fullscreen = false;

    allegro_init();
    install_keyboard();
    install_mouse();
    install_timer();
    install_sound( DIGI_AUTODETECT, MIDI_AUTODETECT, 0 );
    set_color_depth( 16 );

    if ( fullscreen )
    {
        set_gfx_mode(GFX_AUTODETECT, screenWidth, screenHeight, 0, 0);
    }
    else
    {
        set_gfx_mode(GFX_AUTODETECT_WINDOWED, screenWidth, screenHeight, 0, 0);
    }
}

void System::toggleFullscreen()
{
    if ( fullscreen )
    {
        fullscreen = false;
        set_gfx_mode(GFX_AUTODETECT_WINDOWED, screenWidth, screenHeight, 0, 0);
    }
        else
    {
        fullscreen = true;
        set_gfx_mode(GFX_AUTODETECT, screenWidth, screenHeight, 0, 0);
    }
}

void System::quit()
{
    done = true;
}

