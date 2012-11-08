//simple timer used to regulate FPS

#pragma once

#include "SDL.h"

class Timer
{
public:
	Timer();

	void start();
	void stop();

	int getTicks();
	bool isStarted();

private:
	int startTicks;
	bool started;

};