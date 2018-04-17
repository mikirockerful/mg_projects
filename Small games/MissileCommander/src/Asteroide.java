import java.awt.Color;
import java.awt.Graphics;


public class Asteroide {
	int x;
	int y;
	int vidas;
	public Asteroide(){
		this.x=((int)(Math.random()*750))+25;
		this.y=-(((int)(Math.random()*150))+50);
		this.vidas=5;
	}
	public void pintar(Graphics g){
		g.setColor(Color.red);
		g.fillOval(this.x, this.y, 40, 40);
	}
	public void mover(){
		this.y=this.y+2;
		if (this.y+40>=550){
			this.y=-(((int)(Math.random()*150))+50);
		}
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public boolean quitarvida(){
		this.y=-40;
		this.vidas=this.vidas-1;
		if (this.vidas==0){
			return true;
		}
		return false;
	}
}
