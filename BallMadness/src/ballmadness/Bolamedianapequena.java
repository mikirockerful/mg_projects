package ballmadness;

import java.util.ArrayList;



public class Bolamedianapequena extends Bolagrande {
	public Bolamedianapequena(int x, int y, int factor, int color){
		super(x, y, factor, color);
		this.vrebote=13*this.factor;
		this.obtensprite(this.elegircolor(this.color));
	}
	public String elegircolor(int color){
		switch (color){
		case 1:
			return "resources/bolamedianapequenas.png";
		case 2: 
			return "resources/bolamedianapequenasa.png";
		case 3: 
			return "resources/bolamedianapequenasv.png";
		default:
			return "resources/bolamedianapequenas.png";
		}
	}
	public void degrada(ArrayList<Bolagrande> listabolas, int i, Ventana miventana){
		
		listabolas.add(new Bolapequena(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor,this.color));
		listabolas.get(listabolas.size()-1).setvx(this.vx);
		listabolas.get(listabolas.size()-1).setvy(-8*this.factor);
		listabolas.add(new Bolapequena(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor,this.color));
		listabolas.get(listabolas.size()-1).setvx(-this.vx);
		listabolas.get(listabolas.size()-1).setvy(-8*this.factor);
		listabolas.remove(i);
		miventana.explosion();
	}
}
