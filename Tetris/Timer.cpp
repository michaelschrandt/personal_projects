#include "Timer.h"

Timer::Timer()
{
	startTicks = 0;
	started = false;
}

void Timer::start()
{
	started = true;

	startTicks = SDL_GetTicks();
}

void Timer::stop()
{
	started = false;
}

int Timer::getTicks()
{
	if (started)
		return SDL_GetTicks() - startTicks;
	return 0;
}

bool Timer::isStarted()
{
	return started;
}