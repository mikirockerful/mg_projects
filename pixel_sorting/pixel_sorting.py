#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""Rearranges the pixels of an image. Credits to The Coding Train YouTube channel for suggesting the idea"""
__author__ = "Miguel Gamallo"
__copyright__ = "2017  Miguel Gamallo"


import pygame, sys, cv2, random


WIDTH=512
HEIGHT=512

img=cv2.imread('baboon.png')

if __name__ == "__main__":
	#Setup
	pygame.init()
	screen = pygame.display.set_mode((WIDTH,HEIGHT))
	pygame.display.set_caption('Pixel sorting')
	clock = pygame.time.Clock()
	FRAMES_PER_SECOND=30
	
	abandon = False
	
	img=cv2.imread('baboon.png')
	screen.fill((0,0,0))
	for y in range(0,WIDTH):
		for x in range(0,HEIGHT):
			pix=img[x,y,:]
			c=pygame.Color(int(pix[2]),int(pix[1]),int(pix[0]))
			pygame.draw.ellipse(screen,c,(y,x,1,1))
	#Main loop
	while not abandon:
		
		#Event processing
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				pygame.quit()
				sys.exit(0)
			if hasattr(event,'key'):
				if event.key == pygame.K_ESCAPE:
					pygame.quit()
					sys.exit(0)

		for i in range(0,300):
			#Logic goes here-----------------------------------------
			x=random.randint(0,HEIGHT-1)
			y=random.randint(0,WIDTH-1)
			pix=img[x,y,:]
			c=pygame.Color(int(pix[0]),int(pix[1]),int(pix[2]))
			
		


			#Drawing goes here--------------------------------------------
			pygame.draw.ellipse(screen,c,(y,x,1,1))

		pygame.display.update()
		deltat=clock.tick(FRAMES_PER_SECOND)
