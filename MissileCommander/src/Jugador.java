import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Jugador {
	int x;
	int y;
	public Jugador(){
		this.x=200;
		this.y=200;
	}
	public void pintar(Graphics g){
		g.setColor(new Color(0,255,0));
		g.fillRect(this.x,this.y,10,10);
	}
	public void mover(ArrayList<Integer> teclaspulsadas){
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
			this.x=this.x-10;
		}
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
			this.x=this.x+10;
		}
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_UP)){
			this.y=this.y-10;
		}
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_DOWN)){
			this.y=this.y+10;
		}
		if (this.x+10>794){
			this.x=794-10;
		}
		if (this.x<0){
			this.x=0;
		}
		if (this.y<0){
			this.y=0;
		}
		if (this.y+10>550){
			this.y=550-10;
		}
	}
	public void ponmisil(ArrayList<Misil> listamisiles) {
		listamisiles.add(new Misil(this.x,this.y));
		
	}
}
