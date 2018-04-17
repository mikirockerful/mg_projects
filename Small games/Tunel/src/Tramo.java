import java.awt.Color;
import java.awt.Graphics;


public class Tramo {
	int x;
	int y;
	int ancho;
	int alto;
	public Tramo(int x,int y,int ancho,int alto){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public int getancho(){
		return this.ancho;
	}
	public int getalto(){
		return this.alto;
	}
	public void pintar(Graphics g) {
		g.setColor(new Color(255,255,255));
		g.fillRect(this.x, this.y,this.ancho, this.alto);
		
	}
	public void mover(Jugador jugador){
		this.x=this.x-1;
		this.comprueba(jugador);
		this.x=this.x-1;
		this.comprueba(jugador);

	}
	public void comprueba(Jugador jugador){
		if ((jugador.getx()+20>this.x) && (this.x+this.ancho>jugador.getx())){
			if (jugador.gety()<this.y){
				//Nos hemos salido
				jugador.pierdes();
			}
			if (jugador.gety()+6>this.y+this.alto){
				//Nos hemos salido
				jugador.pierdes();
			}
		}
	}

}
