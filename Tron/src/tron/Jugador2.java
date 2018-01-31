package tron;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Jugador2 extends Jugador{

	public Jugador2(){
		super();
		this.x=600;
		this.y=280;
		this.vx=-1;
		this.colorj1=new Color(255,0,0);
	}
	
	public void teclapulsada(int code){
		
		switch (code){
		
		case KeyEvent.VK_W: 	
			if (vert==0){			
				
								vy=-1;
								vx=0;
								horiz=0;
								vert=1;}
								break;
		case KeyEvent.VK_S: 
			if (vert==0){		vy=1;
								vx=0;
								horiz=0;
								vert=1;}
								break;
		case KeyEvent.VK_A: 	
			if (horiz==0){		
								
								vx=-1;
								vy=0;
								vert=0;
								horiz=1;}
								break;
		case KeyEvent.VK_D: 
			if (horiz==0){		vx=1;
								vy=0;
								vert=0;
								horiz=1;}
								break;
		default: break;
		}
	}
	
	public void pierdo(){
		System.out.println("Jugador 2 pierde");
		System.exit(0);
	}
	
}
