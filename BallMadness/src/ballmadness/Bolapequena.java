package ballmadness;

import java.util.ArrayList;


public class Bolapequena extends Bolagrande {
	public Bolapequena(int x, int y,int factor, int color){
		super(x, y, factor, color);
		this.vrebote=11*this.factor;
		this.obtensprite(this.elegircolor(this.color));
	}
	public String elegircolor(int color){
		switch (color){
		case 1:
			return "resources/bolapequenas.png";
		case 2: 
			return "resources/bolapequenasa.png";
		case 3: 
			return "resources/bolapequenasv.png";
		default:
			return "resources/bolapequenas.png";
		}
	}
	public void degrada(ArrayList<Bolagrande> listabolas, int i, Ventana miventana){
		listabolas.remove(i);
		miventana.explosion();
	}
}
