import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Jugador {
	int x;
	int y;
	int xsalida;
	int ysalida;
	int xr;
	int yr;
	int wr;
	int hr;
	int enx;
	int eny;
	Dibujador lienzo;
	boolean salgo;
	boolean pintando;
	public Jugador(Dibujador panel){
		this.lienzo=panel;
		posicioninicial();
		salgo=false;
		pintando=false;
	}
	
	public void pintar(Graphics g){

		if (pintando){
			g.setColor(new Color(0,255,0));
			xr = Math.min(this.x, this.xsalida);
			wr = Math.abs(this.x - this.xsalida);
			yr = Math.min(this.y, this.ysalida);
			hr = Math.abs(this.y - this.ysalida);
			g.drawRect(xr+5,yr+5,wr,hr);
		}
		g.setColor(new Color(255,0,0));
		g.fillOval(this.x, this.y, 11, 11);
		
	}
	public boolean salir(ArrayList<Rectangulos> listarect){
		if (this.detectacolisiones(listarect)){
			this.y+=(-1);
			if (this.y<5){this.y=5;}
			if (!this.detectacolisiones(listarect)){
				this.xsalida=this.x;
				this.ysalida=this.y+1;
				this.pintando=true;
				return true;}
			this.y=this.y+1;
			this.y+=(1);
			if (this.y>548){this.y=548;}
			if (!this.detectacolisiones(listarect)){
				this.xsalida=this.x;
				this.ysalida=this.y-1;
				this.pintando=true;
				return true;}
			this.y=this.y-1;
			this.x+=(-1);
			if (this.x<5){this.x=5;}
			if (!this.detectacolisiones(listarect)){
				this.xsalida=this.x+1;
				this.ysalida=this.y;
				this.pintando=true;
				return true;}
			this.x+=1;
			this.x+=(1);
			if (this.x>760){this.x=760;}
			if (!this.detectacolisiones(listarect)){
				this.xsalida=this.x-1;
				this.ysalida=this.y;
				this.pintando=true;
				return true;}
			this.x=this.x-1;
			return false;
		}
		return true;
	}
	
	public void miraenemigos(ArrayList<Enemigo> listaenemigos){
		xr = Math.min(this.x, this.xsalida);
		wr = Math.abs(this.x - this.xsalida);
		yr = Math.min(this.y, this.ysalida);
		hr = Math.abs(this.y - this.ysalida);
		
		for (int i=0;i<listaenemigos.size();i++){
			enx=listaenemigos.get(i).getx();
			eny=listaenemigos.get(i).gety();
			Rectangle rect1=new Rectangle(this.xr,this.yr,this.wr,this.hr);
			Rectangle rect2=new Rectangle(this.enx,this.eny,10,10);
			if (rect1.intersects(rect2)){
				lienzo.reinicia();
			}
		}
		
	}
	public void mover(ArrayList<Rectangulos> listarect){
		if (!Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_SPACE)){
			if (this.detectacolisiones(listarect)){
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_UP)){
					this.y+=(-1);
					if (!this.detectacolisiones(listarect)){this.y=this.y+1;}
				}
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_DOWN)){
					this.y+=(1);
					if (!this.detectacolisiones(listarect)){this.y=this.y-1;}
				}
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
					this.x+=(-1);
					if (!this.detectacolisiones(listarect)){this.x=this.x+1;}
				}
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
					this.x+=(1);
					if (!this.detectacolisiones(listarect)){this.x=this.x-1;}
				}
				
			}
		} else{
			if (!salgo){
				salgo=this.salir(listarect);
			}
				if (!this.detectacolisiones(listarect)){
					
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_UP)){
						this.y+=(-1);
						if (this.detectacolisiones(listarect)){this.y=this.y+1;}
					}
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_DOWN)){
						this.y+=(1);
						if (this.detectacolisiones(listarect)){this.y=this.y-1;}
					}
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
						this.x+=(-1);
						if (this.detectacolisiones(listarect)){this.x=this.x+1;}
					}
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
						this.x+=(1);
						if (this.detectacolisiones(listarect)){this.x=this.x-1;}
					}
				} else{
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_UP)){
						this.y+=(-1);
						if (!this.detectacolisiones(listarect)){this.y=this.y+1;}
					}
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_DOWN)){
						this.y+=(1);
						if (!this.detectacolisiones(listarect)){this.y=this.y-1;}
					}
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
						this.x+=(-1);
						if (!this.detectacolisiones(listarect)){this.x=this.x+1;}
					}
					if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
						this.x+=(1);
						if (!this.detectacolisiones(listarect)){this.x=this.x-1;}
					}
					salgo=true;
				}
			
		}
	}
	


	public void sueltoespacio(ArrayList<Rectangulos> listarect){
		
		if (pintando){
			listarect.add(new Rectangulos(this.xr+5,this.yr+5,this.wr+1,this.hr+1));
			listarect.get(listarect.size()-1).anadirarea();
			this.pintando=false;

		}
		this.salgo=false;
	}
	public int getx(){
		return this.x;
	}
	
	public int gety(){
		return this.y;
	}
	
	public int getwidth(){
		return 11;
	}
	
	public int getheight(){
		return 11;
	}
	
	private boolean detectacolisiones(ArrayList<Rectangulos> listarect){
		for (int i=0;i<listarect.size();i++){
			Rectangle rect1= new Rectangle(listarect.get(i).getx(),listarect.get(i).gety(),listarect.get(i).getwidth(),listarect.get(i).getheight());
			Rectangle rect2= new Rectangle(this.x+5,this.y+5,1,1);
			if (rect1.intersects(rect2)){
				return true;
			}
		}
		return false;
	}
	
	public void posicioninicial(){
		double in=Math.random();
		if (in<0.25){
			this.x=((int)(Math.random()*765+0.5))+5;
			this.y=5;
		}else{
			if (in<0.5){
				this.y=((int)(Math.random()*553+0.5))+5;
				this.x=5;
			}else {
				if (in<0.75){
					this.x=((int)(Math.random()*765+0.5))+5;
					this.y=558;
				}else{
					if (in<=1.0){
						this.y=((int)(Math.random()*553+0.5))+5;
						this.x=780;
					}
				}
			}
		}
	}


}
