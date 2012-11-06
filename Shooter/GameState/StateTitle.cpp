#include "StateTitle.h"
#include "StateMachine.h"

StateTitle::StateTitle(StateMachine* p) : parent(p)
{
}

void StateTitle::handleEvents(sf::Event &ev)
{
	if(ev.type == sf::Event::KeyPressed)
		parent->setNextState(STATE_OVERWORLD);
}

void StateTitle::logic()
{

}

void StateTitle::draw()
{

}