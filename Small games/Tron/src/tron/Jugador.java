package tron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Jugador {
	int x;
	int y;
	int vx;
	int vy;
	int vert;
	int horiz;
	Color colorj1;
	public Jugador(){
		this.x=200;
		this.y=280;
		this.colorj1=new Color(0,255,0);
		this.vx=1;
		this.horiz=1;
		this.vert=0;
	}
	public void pintar(Graphics g){
		g.setColor(colorj1);
		g.drawLine(this.x, this.y, this.x+1, this.y);
	}
	public void mover(){
		try{
		if (Dibujador.matriz[this.y+this.vy][this.x+this.vx]==0){
		this.x=this.x+vx;
		this.y=this.y+vy;
		Dibujador.matriz[this.y][this.x]=1;
		}else {
			pierdo();
		}} catch(Exception e){
			pierdo();
		}
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public void teclapulsada(int code){
		
		switch (code){
		
		case KeyEvent.VK_UP: 	
			if (vert==0){			
				
								vy=-1;
								vx=0;
								horiz=0;
								vert=1;}
								break;
		case KeyEvent.VK_DOWN: 
			if (vert==0){		vy=1;
								vx=0;
								horiz=0;
								vert=1;}
								break;
		case KeyEvent.VK_LEFT: 	
			if (horiz==0){		
								
								vx=-1;
								vy=0;
								vert=0;
								horiz=1;}
								break;
		case KeyEvent.VK_RIGHT: 
			if (horiz==0){		vx=1;
								vy=0;
								vert=0;
								horiz=1;}
								break;
		default: break;
		}
	}
	public void pierdo(){
		System.out.println("Jugador 1 pierde");
		System.exit(0);
	}
}
