import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Cuadro {
	int x;
	int y;
	int tipo;
	int cont;
	int esperamax;
	int espera;
	public Cuadro(){
		this.x=(int)(Math.random()*794);
		this.tipo=((int)(Math.random()*3))+1;
		this.esperamax=(int)(Math.random()*1000);
		this.espera=0;
		this.y=-10;
		this.cont=0;
		
	}
	public void pintar(Graphics g){
		switch (this.tipo){
		case 1: 
			g.setColor(Color.red);
			break;
		case 2:
			g.setColor(Color.green);
			break;
		case 3:
			g.setColor(Color.blue);
			break;
		}
		g.fillRect(this.x-5,this.y,10,10);
	}
	public void mover(int[] pintados, ArrayList<Cuadro> listacuadros){
		if (this.espera==this.esperamax){
			this.cont=this.cont+1;
			if (this.cont==3){
				this.y=this.y+1;
				this.detectacolision(pintados, listacuadros);
				if (this.y>=572){
					this.y=0;
				}
				this.cont=0;
			}
		}else{
			this.espera=this.espera+1;
		}
	}
	public void detectacolision(int[] pintados, ArrayList<Cuadro> listacuadros){
		if (this.y+10==300){
			if (pintados[this.x]==this.tipo){
				listacuadros.add(new Cuadro());
				listacuadros.add(new Cuadro());
				listacuadros.remove(this);
			}
		}
	}
}
