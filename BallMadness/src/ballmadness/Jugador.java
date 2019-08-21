package ballmadness;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Jugador {
	int x;
	int y;
	int vx;
	int xfinal;
	int vidas;
	int factor;
	boolean suelto;
	Image imagen;
	Image imagenprev;
	ImageIcon icono;
	Image quieto;
	Image disparando;
	Image andando1;
	Image andando2;
	Image andando3;
	Image andando4;
	Image andando5;
	Image andando6;
	Image andando7;
	Image andando8;
	Image imvidas;
	int contador;
	int contador2;
	Dibujador lienzo;
	public Jugador(Dibujador lienzo, int factor){
		this.factor=factor;
		this.contador=1;
		this.contador2=1;
		this.suelto=true;
		this.vidas=5;
		this.vx=10*this.factor;
		this.lienzo=lienzo;
		this.cargarimagenes();
		this.imagen=this.quieto;
		this.x=48+128*this.factor-this.imagen.getWidth(null);
		this.y=64+192*this.factor-this.imagen.getHeight(null);
	}
	
	public void pintar(Graphics g, ArrayList<Integer> teclaspulsadas) {
		this.imagen=this.quieto;
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
			switch (contador) {
			case 1: this.imagen=this.andando1;
			break;
			case 2: this.imagen=this.andando2;
			break;
			case 3: this.imagen=this.andando3;
			break;
			case 4: this.imagen=this.andando4;
			break;
			}
			if (this.contador2==3){
			this.contador+=1;
			if (this.contador==5){this.contador=1;}
			this.contador2=1;
			}
			this.contador2+=1;
		}
		else if (teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
			switch (contador) {
			case 1: this.imagen=this.andando5;
			break;
			case 2: this.imagen=this.andando6;
			break;
			case 3: this.imagen=this.andando7;
			break;
			case 4: this.imagen=this.andando8;
			break;
			}
			if (this.contador2==3){
			this.contador+=1;
			if (this.contador==5){this.contador=1;}
			this.contador2=1;
			}
			this.contador2+=1;
		}
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT) && teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
			this.imagen=this.quieto;
		}
		if (this.suelto){
			if (teclaspulsadas.contains((Integer)KeyEvent.VK_SPACE)){
				this.imagen=this.disparando;
				this.suelto=false;
			} 
		}
		
		g.drawImage(this.imagen,this.x,this.y,null);
		
		for (int i=0;i<this.vidas;i++){
			g.drawImage(this.imvidas, (241-i*18)*this.factor+48, 64+2*this.factor, null);
		}
	}
	public void sumarvida(){
		this.vidas=this.vidas+1;
	}
	
	public boolean mover(ArrayList<Integer> teclaspulsadas, ArrayList<Bolagrande> listabolas, Dibujador dibujador){
		if(this.colisionbolas(listabolas)==true){
			//Perdemos
			this.quitarvida(dibujador);
			return false;
		}
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_RIGHT)){
			this.xfinal=this.x+vx;
			this.x=this.x+1;
			while (this.x<this.xfinal){
				if(this.colisionbolas(listabolas)==true){
					this.x=this.x-1;
					//Perdemos
					this.quitarvida(dibujador);
					return false;
				}
				if (this.x>=256*this.factor+48-this.imagen.getWidth(null)){
					this.x=256*this.factor+48-this.imagen.getWidth(null);
					break;
				}
				this.x=this.x+1;
			}
		}
		if (teclaspulsadas.contains((Integer)KeyEvent.VK_LEFT)){
			this.xfinal=this.x-vx;;
			this.x=this.x-1;
			while (this.xfinal<this.x){
				if(this.colisionbolas(listabolas)==true){
					this.x=this.x+1;
					//Perdemos
					this.quitarvida(dibujador);
					return false;
				}
				if (this.x<=48){
					this.x=48;
					break;
				}
				this.x=this.x-1;
			}
		}
		return true;
	}
	public int getx(){
		return this.x;
	}
	public void quitarvida(Dibujador dibujador){
		//System.out.println("pierdes");
		//System.out.println(this.vidas);
		this.vidas=this.vidas-1;
		if (this.vidas==0){
			dibujador.resetgame();
		}
		this.lienzo.resetlevel();
	}
	public void centrar(){
		this.x=48+128*this.factor-this.imagen.getWidth(null);
	}
	public boolean colisionbolas(ArrayList<Bolagrande> listabolas){//Habra que ver si colisionamos o no
		for (int i=0;i<listabolas.size();i++){
			Rectangle rect1=new Rectangle (this.x,this.y,imagen.getWidth(null),imagen.getHeight(null));
			Rectangle rect2=new Rectangle (listabolas.get(i).getx(),listabolas.get(i).gety(),listabolas.get(i).getancho(),listabolas.get(i).getalto());
			if (rect1.intersects(rect2)){
				return true;
			}
		}
		return false;
	}
	public int gety(){
		return this.y;
	}
	public int getancho(){
		return this.imagen.getWidth(null);
	}
	public int getalto(){
		return this.imagen.getHeight(null);
	}
	public void cargarimagenes(){
		
		this.icono=new ImageIcon("resources/disparando2s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.quieto=icono.getImage();
		
		this.icono=new ImageIcon("resources/disparando3s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.disparando=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando1s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando1=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando2s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando2=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando3s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando3=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando4s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando4=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando5s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando5=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando6s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando6=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando7s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando7=icono.getImage();
		
		this.icono=new ImageIcon("resources/andando8s.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.andando8=icono.getImage();
		
		this.icono=new ImageIcon("resources/vidas.png");//Reescalado de la imagen mediante el getScaledInstance y creacion de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.imvidas=icono.getImage();

	}

}
