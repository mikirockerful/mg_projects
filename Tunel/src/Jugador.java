import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Jugador {
	int x;
	int y;
	boolean perdido;

	ArrayList<Tramo> tramos;
	public Jugador(){
		this.x=50;
		this.y=300;
		this.perdido=false;

	}
	public void pintar(Graphics g){
		g.setColor(new Color(255,0,0));
		g.fillRect(this.x, this.y, 20, 6);
	}
	public void mover(Carretera carretera){
		if (!perdido){
			for (int i=0;i<4;i++){
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_UP)){
					this.y=this.y-1;
					mirarsalida(carretera);
				}
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_DOWN)){
					this.y=this.y+1;
					mirarsalida(carretera);
				}
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
					this.x=this.x+1;
					mirarsalida(carretera);
				}
				if (Dibujador.teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
					this.x=this.x-1;
					mirarsalida(carretera);
				}
			}
		}
	}
	public void mirarsalida(Carretera carretera){
		tramos=carretera.gettramos();
		for (int i=0;i<tramos.size();i++){
			if ((tramos.get(i).getx()<this.x+20) && (tramos.get(i).getx()+tramos.get(i).getancho()>this.x)){
				if (this.y<tramos.get(i).gety()){
					//Nos hemos salido
					pierdes();
				}
				if (this.y+6>tramos.get(i).gety()+tramos.get(i).getalto()){
					//Nos hemos salido
					pierdes();
				}
				
			}
		}
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public void pierdes(){
		if (!perdido){
		System.out.println("Has perdido");
		}
		this.perdido=true;
	}
}
