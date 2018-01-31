package ballmadness;

import java.util.ArrayList;



public class Bolagrandemediana extends Bolagrande {
	public Bolagrandemediana(int x,int y,int factor, int color){
		super(x,y,factor,color);
		this.vrebote=16*this.factor;
		this.obtensprite(this.elegircolor(this.color));
	}
	public String elegircolor(int color){
		switch (color){
		case 1:
			return "resources/bolagrandemedianas.png";
		case 2: 
			return "resources/bolagrandemedianasa.png";
		case 3: 
			return "resources/bolagrandemedianasv.png";
		default:
			return "resources/bolagrandemedianas.png";
		}
	}
	public void degrada(ArrayList<Bolagrande> listabolas, int i, Ventana miventana){
		
		listabolas.add(new Bolamediana(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor,this.color));
		listabolas.get(listabolas.size()-1).setvx(this.vx);
		listabolas.get(listabolas.size()-1).setvy(-6*this.factor);
		listabolas.add(new Bolamediana(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor,this.color));
		listabolas.get(listabolas.size()-1).setvx(-this.vx);
		listabolas.get(listabolas.size()-1).setvy(-6*this.factor);
		listabolas.remove(i);
		miventana.explosion();
	}
}
