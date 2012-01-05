 
import pygame
from AStar import AStar 
# Define colors
black    = (   0,   0,   0)
white    = ( 255, 255, 255)
green    = (   0, 255,   0)
red      = ( 255,   0,   0)
 

boardsize = 100
size=[500,500]
search = AStar(boardsize,(4,1),(40,70))
board = search.getBoard()
pygame.init()
  
# Setup screen
screen=pygame.display.set_mode(size)
 
pygame.display.set_caption("My Game")
 
#Loop until the user clicks the close button.
done=False
clock=pygame.time.Clock()
 
while done==False:
    for event in pygame.event.get(): 
        if event.type == pygame.QUIT: 
            done=True #Exit main loop 
 
    screen.fill(white)
    # Limit to 20 frames per second
    clock.tick(20)
    
    # Loop for each cell on board
    i = 0
    for row in board:
        n = 0
        for cell in row:
            if cell == 2:
		# Show path
                pygame.draw.rect(screen, green, (n*size[0]/boardsize+1, i*size[1]/boardsize+1, size[0]/boardsize,size[1]/boardsize))
            elif cell == 1:
		# Show walls
                pygame.draw.rect(screen, red, (n*size[0]/boardsize+1, i*size[1]/boardsize+1, size[0]/boardsize,size[1]/boardsize))
            n = n+1
        i = i+1
    
    # Draw cell lines
    i = 0
    while i < boardsize:
        pygame.draw.line(screen, black, (i*size[0]/boardsize,0), (i*size[0]/boardsize,size[1]),1)

        pygame.draw.line(screen, black, (0,i*size[1]/boardsize), (size[0],i*size[1]/boardsize),1)
        i = i+1
    # Draw changes on screen
    pygame.display.flip()
     
pygame.quit ()
