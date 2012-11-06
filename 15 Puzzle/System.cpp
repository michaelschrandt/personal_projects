#include <allegro.h>
#include "System.h"

System::System(int width, int height, bool full)
{
            screenHeight = height;
            screenWidth = width;
            fullscreen = full;
}

void System::setup()
{
      allegro_init();
      install_keyboard();
      install_timer();
      install_mouse();
      install_sound(DIGI_AUTODETECT,
                              MIDI_AUTODETECT, 0);
      set_color_depth(32);

      if (fullscreen)
            set_gfx_mode(GFX_AUTODETECT_FULLSCREEN, screenWidth, screenHeight, 0, 0);
      else
            set_gfx_mode(GFX_AUTODETECT_WINDOWED, screenWidth, screenHeight, 0, 0);
}
