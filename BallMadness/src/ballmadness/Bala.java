package ballmadness;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bala {
	int x;
	int y;
	int vy;
	int yfinal;
	Image imagen;
	ImageIcon icono;
	Image imagenprev;
	int factor;

	public Bala(int x, int factor, int color){
		this.factor=factor;
		this.vy=-10*this.factor;
		this.x=x;
		if (color==1){
		this.icono=new ImageIcon("resources/balas.png");
		}else{
		this.icono=new ImageIcon("resources/balasaz.png");
		}
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.imagen=icono.getImage();
		this.y=64+192*this.factor;
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
				break;
			}
			this.y=this.y-1;
		}
		
	}
	public void pintar(Graphics g){
		g.setClip(this.x, 64, 10*this.factor, 192*this.factor);
		g.drawImage(this.imagen,this.x,this.y,null);
		g.setClip(48, 64, 256*this.factor, 192*this.factor);
	}
	public boolean colisionbolas(ArrayList<Bolagrande> listabolas, Ventana miventana){
		for (int i=0;i<listabolas.size();i++){
			Rectangle rect1=new Rectangle (this.x,this.y,imagen.getWidth(null),imagen.getHeight(null));
			Rectangle rect2=new Rectangle (listabolas.get(i).getx(),listabolas.get(i).gety(),listabolas.get(i).getancho(),listabolas.get(i).getalto());
			if (rect1.intersects(rect2)){
				listabolas.get(i).degrada(listabolas,i, miventana);
				return true;
			}
		}
		return false;
	}

}
