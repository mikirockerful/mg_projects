#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""Small version of the popular game Flappy Bird using pygame"""
__author__ = "Miguel Gamallo"
__copyright__ = "2017  Miguel Gamallo"


import pygame, sys, random


WIDTH=640
HEIGHT=360

class wall():
	def __init__(self):
		self.color=pygame.Color("red")
		self.position=WIDTH
		self.hole_pos=random.randint(0,HEIGHT-150)
		self.hole_size=random.randint(50,150)
		self.xspeed=-3
		
	def draw(self):
		pygame.draw.rect(screen,self.color,(self.position,0,20,self.hole_pos))
		pygame.draw.rect(screen,self.color,(self.position,self.hole_pos+self.hole_size,20,HEIGHT))
		
	def move(self):
		self.position=self.position+self.xspeed
		
class bird():
	def __init__(self):
		self.color=pygame.Color("green")
		self.position=[200,200]
		self.yspeed=-15
		
	def draw(self):
		pygame.draw.ellipse(screen,self.color,(self.position[0],self.position[1],20,20))
		
	def move(self):
		self.position[1]=self.position[1]+self.yspeed
		self.yspeed=self.yspeed+1
	def jump(self):
		self.yspeed=-10
	def detect_collisions(self,wallarray):
		for item in wallarray:
			if (self.position[0]+20 >= item.position and self.position[1]<=item.hole_pos and self.position[0] <item.position+20):
				pygame.quit()
				sys.exit(0)
			if (self.position[0]+20 >= item.position and self.position[0] <item.position+20 and self.position[1]>=item.hole_pos+item.hole_size):
				pygame.quit()
				sys.exit(0)
		if (self.position[1]+20 >= HEIGHT) or (self.position[1]<0):
			pygame.quit()
			sys.exit(0)

if __name__ == "__main__":
	#Setup
	pygame.init()
	screen = pygame.display.set_mode((WIDTH,HEIGHT))
	pygame.display.set_caption('Flappy bird')
	clock = pygame.time.Clock()
	FRAMES_PER_SECOND=30
	
	abandon = False
	
	wallarray=[]
	wallarray.append(wall())
	
	mybird=bird()
	
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
				if event.key == pygame.K_SPACE:
					mybird.jump()

		#Game logic goes here-----------------------------------------
		for item in wallarray:
			item.move()
		mybird.move()
		
		#De-reference objects that disappeared from screen	
		wallarray[:] = [item for item in wallarray if item.position>=-(WIDTH/10)]
		
		#New walls
		if wallarray[-1].position<WIDTH-WIDTH/4:
			wallarray.append(wall())

		mybird.detect_collisions(wallarray)

		#Drawing goes here--------------------------------------------
		screen.fill((0,0,255))
		for item in wallarray:
			item.draw()
		mybird.draw()

		pygame.display.update()
		deltat=clock.tick(FRAMES_PER_SECOND)
