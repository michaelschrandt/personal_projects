#Michael Schrandt   00616486
#12/6/2011
#driver file. Main Loop is defined here.

import pygame

import Map
from Map import *

import Player
from Player import *

import AITEST
from AITEST import *

pygame.init()
quitGame = False
#Global constants
WIDTH = 640
HEIGHT = 480


#Window stuff



while (not quitGame):
    #Game stuff
    path = None
    command = input('Input Command: ')
    if (command == 'quit'):
        break
    command = command.split(' ')
    if (command[0] == 'solve' or command[0] == 'play'):
        s, start, end = readMap(command[1])
        if (not s):
            print('Map not found.')
            continue
        start[0] = int(start[0])*32
        start[1] = int(start[1])*32

        end[0] = int(end[0])*32
        end[1] = int(end[1])*32
        player = Player(start[1], start[0],0)
        xVelocity = 0
        view = pygame.Rect(0, 0, WIDTH, HEIGHT)
        if (command[0] == 'solve'):
            print('Solving...')
            path = solve(s, (start[0]+PLAYER_W, start[1]+PLAYER_W/2), (end[0]+32, end[1]+16))
        solved = False
    else:
        continue
    
    screenSize = [WIDTH, HEIGHT]
    screen = pygame.display.set_mode(screenSize)
    pygame.display.set_caption("Platforming AI")
    pygame.display.set_icon(pygame.image.load("physix.png"))

    done = False
    clock = pygame.time.Clock()

    currentStep = 0

    #drawMap(screen, s, view)
    while not done:
        if (not solved):
            if (path is not None and currentStep/NODE_THRESHOLD < len(path)):
                if (path[int(currentStep/NODE_THRESHOLD)].y < player.location.bottom):
                    player.jump()
                elif (player.yVelocity < 0):
                    player.yVelocity = 0
                if (path[int(currentStep/NODE_THRESHOLD)].x > player.location.center[0]):
                    xVelocity = MAX_SPEED
                elif (path[int(currentStep/NODE_THRESHOLD)].x < player.location.center[0]):
                    xVelocity = -1*MAX_SPEED
                else:
                    xVelocity = 0
                currentStep += 1
            else:
                xVelocity = 0
                solved = True
                #player.yVelocity = 0


        #Handle events
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                done=True
            if (solved):    
                if event.type == pygame.KEYDOWN:

                    if event.key == pygame.K_LEFT:
                        xVelocity= -1*MAX_SPEED

                    if event.key == pygame.K_RIGHT:
                        xVelocity= MAX_SPEED

                    #only jump if the player is not currently in the air
                    if (event.key == pygame.K_UP):
                        player.jump()

                         
                if event.type == pygame.KEYUP:
                    
                    if event.key == pygame.K_LEFT:
                        xVelocity = 0

                    if event.key == pygame.K_RIGHT:
                        xVelocity = 0

                    #only stop the jump early if the player is moving upward
                    if event.key == pygame.K_UP:
                        if (player.yVelocity < 0):
                            #player.yVelocity /= 2
                            player.yVelocity = 0

        #Move the player
        player.move(xVelocity, s)

        #Adjust the camera
        view.center = player.location.center
        if (view.left < 0):
            view.left = 0
        elif (view.right > (len(s[0]) * 32)):
            view.right = len(s[0]) * 32
        if (view.top < 0):
            view.top = 0
        elif (view.bottom > (len(s)*32)):
            view.bottom = len(s)*32

        #Draw the changes to the screen
        screen.fill((0,0,0))
        drawMap(screen, s, view, end)
        if (path is not None):
            drawPath(screen, path, view)
        player.draw(screen, view)
        pygame.display.flip()
        clock.tick(FPS)
    pygame.display.quit()

pygame.quit()



