import java.awt.Color;
import java.awt.Graphics;


public class Rectangulos {
	
	int x;
	int y;
	int height;
	int width;
	
	public Rectangulos(int x0,int y0,int ancho,int alto){
		this.x=x0;
		this.y=y0;
		this.width=ancho;
		this.height=alto;
	}
	
	public void pintar(Graphics g){
		g.setColor(new Color(0,255,0));
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public void mover(){
		
	}
	
	public int getx(){
		return this.x;
	}
	
	public int gety(){
		return this.y;
	}
	
	public int getwidth(){
		return this.width;
	}
	
	public int getheight(){
		return this.height;
	}
	public void anadirarea(){
		Dibujador.areapintada+=(this.width*this.height);
	}
}
