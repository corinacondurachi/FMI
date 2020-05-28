import pygame
pygame.init()
win = pygame.display.set_mode((968,572))
pygame.display.set_caption("Omu cu bombe")

bg = pygame.image.load('bg1.png')
char = pygame.image.load('player.png')
char2 = pygame.image.load('player2.png')


x = 44
y = 44
x2 = 132
y2 = 176
width = 64
height = 64
vel = 44
run = True
screen_width = 968
screen_height = 572


def redrawGameWindow():
    
    win.blit(bg, (0,0))  # This will draw our background image at (0,0)
    win.blit(char, (x, y))  
    win.blit(char2, (x2, y2)) 
    pygame.display.update() 


while run:
    pygame.time.delay(100)
    
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            run = False
            
        keys = pygame.key.get_pressed()
        
        if keys[pygame.K_LEFT] and x > vel:
            x -= vel
        
        if keys[pygame.K_RIGHT] and x < screen_width - width - vel:
            x += vel
            
        if keys[pygame.K_UP] and y > vel:
            y -= vel
            
        if keys[pygame.K_DOWN] and y < screen_height - height - vel :
            y += vel
            
        redrawGameWindow()

       
        
pygame.quit()        
