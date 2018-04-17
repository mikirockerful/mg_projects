import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Misil {
	int xobj;
	int yobj;
	int y;
	int ymov;
	int redibujados;
	Explosion miexplosion;
	public static int numero=0;
	public Misil(int xobj,int yobj){
		Misil.numero=Misil.numero+1;
		this.xobj=xobj;
		this.yobj=yobj;
		this.y=550;
		this.redibujados=0;
	}
	public void pintar(Graphics g){
		g.setColor(Color.yellow);
		g.fillRect(this.xobj, this.y, 1, 550-this.y);
		if (miexplosion!=null){
			miexplosion.pintar(g);
		}
	}
	public void mover(ArrayList<Misil> listamisiles, ArrayList<Asteroide> listaasteroides){
		if (this.y>this.yobj){
			this.ymov=this.y-5;
			if (this.ymov<this.yobj){
				this.ymov=this.yobj;
			}
			while(this.y>this.ymov){
				this.y=this.y-1;
				//Mirar colision
			}
		}
		if (this.y==this.yobj){
			if (this.redibujados==0){
			this.miexplosion=new Explosion(this.xobj,this.y);
			}
			miexplosion.mirarcolision(listaasteroides);
			if (this.redibujados==10){
				listamisiles.remove(this);
				Misil.numero=Misil.numero-1;
			}
			this.redibujados=this.redibujados+1;
		}
	}


}
