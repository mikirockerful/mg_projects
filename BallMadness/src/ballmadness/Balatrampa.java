package ballmadness;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Balatrampa extends Bala {
	public Balatrampa(int x, int factor, int color){
		super(x,factor,color);
		this.icono=new ImageIcon("resources/balasa.png");
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.imagen=icono.getImage();
	}
	
	public void mover(ArrayList<Bala> listabalas, int ii,ArrayList<Bolagrande> listabolas, Ventana miventana){
		
		this.yfinal=this.y+vy;
		this.y=this.y+1;
		while (this.yfinal<this.y){
			if(this.colisionbolas(listabolas, miventana)==true){
				//Rompemos la bala
				listabalas.remove(ii);
				break;
			}
			if (this.y<64){
				listabalas.remove(ii);
				listabolas.add(new Bolamedianapequena(this.x,64,this.factor,(int)(Math.random()*3+1)));
				if(Math.random()<0.5){
				listabolas.get(listabolas.size()-1).setvx(6*this.factor);
				}else{
				listabolas.get(listabolas.size()-1).setvx(-6*this.factor);
				}
				break;
			}
			this.y=this.y-1;
		}
		
	}
	
}
