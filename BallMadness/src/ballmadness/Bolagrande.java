package ballmadness;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bolagrande {
	int x;
	int xfinal;
	int y;
	int yfinal;
	int vx;
	int vy;
	int vrebote;
	int ay;
	int factor;
	int color;
	boolean techo;
	Image imagen;
	ImageIcon icono;
	Image imagenprev;
	public Bolagrande(int x,int y,int factor, int color){
		this.factor=factor;
		this.color=color;
		this.vx=5*this.factor;
		this.x=x;
		this.y=y;
		this.vy=0;
		this.vrebote=17*this.factor;
		this.obtensprite(elegircolor(this.color));
	}
	public String elegircolor(int color){
		switch (color){
		case 1:
			return "resources/bolagrandes.png";
		case 2: 
			return "resources/bolagrandesa.png";
		case 3: 
			return "resources/bolagrandesv.png";
		default:
			return "resources/bolagrandes.png";
		}
	}
	public void pintar(Graphics g){
		g.drawImage(this.imagen,this.x,this.y,null);
	}
	public boolean mover(Jugador jugador, Dibujador dibujador){
		this.xfinal=this.x+vx;
		if (this.x<this.xfinal){
			this.x=this.x+1;
			while (this.x<this.xfinal){
				if(this.colisionjugador(jugador)==true){
					this.x=this.x-1;
					//Perdemos
					jugador.quitarvida(dibujador);
					return true;
				}
				if (this.x+imagen.getWidth(null)>=48+256*this.factor){
					this.x=48+256*this.factor-imagen.getWidth(null)-(this.xfinal-this.x);
					this.vx=-(this.vx);
					break;
				}
				this.x=this.x+1;
			}
		}else{
			this.xfinal=this.x+vx;;
			this.x=this.x-1;
			while (this.xfinal<this.x){
				if(this.colisionjugador(jugador)==true){
					this.x=this.x+1;
					//Perdemos
					jugador.quitarvida(dibujador);
					return true;
				}
				if (this.x<=48){
					this.x=48+(this.x-this.xfinal);
					this.vx=-(this.vx);
					break;
				}
				this.x=this.x-1;
			}
		}
		if (vy>=0){//Estamos bajando, queremos ir acelerando
			this.yfinal=this.y+this.vy;
			this.y=this.y+1;
				while (this.y<this.yfinal){
					if(this.colisionjugador(jugador)==true){
						this.y=this.y-1;
						//Perdemos
						jugador.quitarvida(dibujador);
						return true;
					}
					if (this.y+imagen.getHeight(null)>=64+192*this.factor){
						this.y=64+192*this.factor-imagen.getHeight(null)-(this.yfinal-this.y);
						this.vy=-(this.vrebote);
						break;
					}
					this.y=this.y+1;
				}
			this.vy=this.vy+this.factor;
		}else{//Estamos subiendo, queremos ir frenando
			this.yfinal=this.y+this.vy;
			this.y=this.y-1;
			this.techo=false;
				while (this.yfinal<this.y){
					if(this.colisionjugador(jugador)==true){
						this.y=this.y+1;
						//Perdemos
						jugador.quitarvida(dibujador);
						return true;
					}
					if (this.y<=64){
						this.y=63;
						this.vy=0;
						this.techo=true;
						break;
					}
					this.y=this.y-1;
				}
			if (!this.techo){
			this.vy=this.vy+this.factor;
			}
		}
		return false;
	}
	public void obtensprite(String nombre){
		icono=new ImageIcon(nombre);
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.imagen=icono.getImage();
	}
	public int getx(){
		return this.x;
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
	public void setvx(int nvx){
		this.vx=nvx;
	}
	public void setvy(int nvy){
		this.vy=nvy;
	}
	public boolean colisionjugador(Jugador jugador){//Habrá que ver si colisionamos o no
		if (jugador!=null){
				Rectangle rect1=new Rectangle (this.x,this.y,imagen.getWidth(null),imagen.getHeight(null));
				Rectangle rect2=new Rectangle (jugador.getx(),jugador.gety(),jugador.getancho(),jugador.getalto());
				if (rect1.intersects(rect2)){
					return true;
			}
			return false;
		} else{
		return false; 
		}
	}
	public void degrada(ArrayList<Bolagrande> listabolas, int i, Ventana miventana){
		
		listabolas.add(new Bolagrandemediana(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor,this.color));
		listabolas.get(listabolas.size()-1).setvx(this.vx);
		listabolas.get(listabolas.size()-1).setvy(-6*this.factor);
		listabolas.add(new Bolagrandemediana(listabolas.get(i).getx()+(int)(listabolas.get(i).getancho()/2),this.y,this.factor,this.color));
		listabolas.get(listabolas.size()-1).setvx(-this.vx);
		listabolas.get(listabolas.size()-1).setvy(-6*this.factor); 
		listabolas.remove(i);
		miventana.explosion();
	}
}
