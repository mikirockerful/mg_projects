import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Jugador {
	int x;
	int tipo;
	int y=300;
	int[] pintados=new int[795];
	long[] edad=new long[795];
	int disponibles;
	long actual;
	int rellenos;
	int antiguo;
	int este;
	public Jugador(){
		for (int i=0;i<795;i++){
			this.pintados[i]=0;
			this.edad[i]=0;
		}
		this.disponibles=100;
		this.actual=1;
		this.antiguo=1;
		this.rellenos=0;
		this.tipo=1;
		this.x=100;
	}
	public void pintar(Graphics g){
		g.setColor(new Color(100,100,100));
		g.drawOval(this.x-5, this.y-5, 10, 10);
		g.drawOval(this.x-10, this.y-10, 20, 20);
		g.drawOval(this.x-20, this.y-20, 40, 40);
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
		g.fillRect(10,10,100,100);
		for (int i=0;i<795;i++){
			switch (pintados[i]){
			case 0:
				break;
			case 1: 
				g.setColor(Color.red);
				g.drawRect(i, 300, 1, 1);
				break;
			case 2:
				g.setColor(Color.green);
				g.drawRect(i, 300, 1, 1);
				break;
			case 3:
				g.setColor(Color.blue);
				g.drawRect(i, 300, 1, 1);
				break;
			}
		}
	}
	public void cambiarcolor(){
		this.tipo=this.tipo+1;
		if (this.tipo>=4){
			this.tipo=1;
		}
	}
	public int[] damepintados(){
		return pintados;
	}
	public void actualizardisponibles (int d){
		this.disponibles=d*100;
	}
	public void mover(ArrayList<Integer> listateclas){
		if (listateclas.contains((Integer)KeyEvent.VK_LEFT)){
			this.x=this.x-1;
		}
		if (listateclas.contains((Integer)KeyEvent.VK_RIGHT)){
			this.x=this.x+1;
		}
		if (this.x<0){
			this.x=794;
		}
		if (this.x>=795){
			this.x=0;
		}
		if (listateclas.contains((Integer)KeyEvent.VK_SPACE)){
			
			this.pintados[this.x]=this.tipo;
			this.rellenos=this.rellenos+1;
			this.edad[this.x]=this.actual;
			this.actual=this.actual+1;
			if (this.rellenos==this.disponibles){
				//Borrar de pintados el pixel que esté con el mismo índice que aquel de la lista edad de menor índice
				for (int i=0;i<795;i++){
					if (this.edad[i]==this.antiguo){
						this.este=i;
						break;
					}
				}
				this.pintados[this.este]=0;
				this.antiguo=this.antiguo+1;
				this.rellenos=this.rellenos-1;
			}
		}
	}
}
