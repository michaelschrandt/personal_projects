#include "StateMachine.h"

//new states must be included here
#include "GameState.h"
#include "StateNull.h"
#include "StateTitle.h"
#include "StateOverworld.h"
//#include "StateOther.h"

//default state should be defined in this constructor
StateMachine::StateMachine() :  nextState(STATE_NULL)
{
	currentState = new StateTitle(this);
}

void StateMachine::setNextState(EnumState next)
{
	nextState = next;
}

void StateMachine::changeState()
{
	if(nextState != STATE_NULL)
	{
		GameState *temp = currentState;

		//possible state switches must be listed here
		switch(nextState)
		{
		case STATE_TITLE:
			currentState = new StateTitle(this);
			break;
		case STATE_OVERWORLD:
			currentState = new StateOverworld(this);
			break;
		//case STATE_OTHER:
			//currentState = new StateOther(this);
			//break;
		default:
			currentState = new StateNull(this);
		}

		delete(temp);
		nextState = STATE_NULL;
	}
}

EnumState StateMachine::getID()
{
	return currentState->getID();
}

void StateMachine::handleEvents(sf::Event &ev)
{
	currentState->handleEvents(ev);
}

void StateMachine::logic()
{
	currentState->logic();
}

void StateMachine::draw()
{
	currentState->draw();
}