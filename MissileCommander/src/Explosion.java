import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Explosion extends Misil {
//Cuidado si heredas, que aumentas el numero de misiles
	int x;
	int y;
	public Explosion(int x, int y){
		super(x,y);
		this.x=x-50;
		this.y=y-50;
		Misil.numero=Misil.numero-1;
	}
	public void pintar(Graphics g){
		g.fillOval(this.x, this.y, 100, 100);
	}
	public void mover(ArrayList<Misil> listamisiles){
		//Que no se mueva
	}
	public void mirarcolision(ArrayList<Asteroide> listaasteroides){
		for (int i=0;i<listaasteroides.size();i++){
			Rectangle rect1=new Rectangle(this.x,this.y,100,100);
			Rectangle rect2=new Rectangle(listaasteroides.get(i).getx(),listaasteroides.get(i).gety(),40,40);
			if(rect1.intersects(rect2)){
				if(listaasteroides.get(i).quitarvida()){
					listaasteroides.remove(i);
				}
			}
		}
	}
}
