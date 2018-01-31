package ballmadness;

import java.util.ArrayList;



public class Bolamediana extends Bolagrande {
	public Bolamediana(int x, int y, int factor, int color){
		super(x, y, factor, color);
		this.vrebote=14*this.factor;
		this.obtensprite(this.elegircolor(this.color));
	}
	public String elegircolor(int color){
		switch (color){
		case 1:
			return "resources/bolamedianas.png";
		case 2: 
			return "resources/bolamedianasa.png";
		case 3: 
			return "resources/bolamedianasv.png";
		default:
			return "resources/bolamedianas.png";
		}
	}
	public void degrada(ArrayList<Bolagrande> listabolas, int i, Ventana miventana){
		
		listabolas.add(new Bolamedianapequena(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor, this.color));
		listabolas.get(listabolas.size()-1).setvx(this.vx);
		listabolas.get(listabolas.size()-1).setvy(-7*this.factor);
		listabolas.add(new Bolamedianapequena(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor, this.color));
		listabolas.get(listabolas.size()-1).setvx(-this.vx);
		listabolas.get(listabolas.size()-1).setvy(-7*this.factor);
		listabolas.remove(i);
		miventana.explosion();
	}

}
