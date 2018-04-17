import java.awt.Graphics;
import java.util.ArrayList;


public class Carretera {
	ArrayList<Tramo> tramos;
	int xfinal;
	int x;
	int y;
	int ymax;
	int ymin;
	int ancho;
	int alto;
	public Carretera(){
		tramos=new ArrayList<Tramo>();
		xfinal=0;
		x=0;
		y=200;
		tramos.add(new Tramo(x,y,400,200));
		this.generotramo();
		
	}
	public void pintar(Graphics g){
		for (int i=0;i<tramos.size();i++){
			tramos.get(i).pintar(g);
		}
	}
	public void mover(Jugador jugador){
		for (int i=0;i<tramos.size();i++){
			tramos.get(i).mover(jugador);
		}
		xfinal=tramos.get(tramos.size()-1).getx();
		if (xfinal+tramos.get(tramos.size()-1).getancho()<800){
			generotramo();
		}
	}
	public ArrayList<Tramo> gettramos(){
		return tramos;
	}
	public void generotramo(){
		xfinal=xfinal+tramos.get(tramos.size()-1).getancho();
		while(xfinal<800){
			ancho=(int)(Math.random()*200)+100;
			alto=(int)(Math.random()*50)+50;
			ymin=(tramos.get(tramos.size()-1).gety())-alto+12;
			ymax=(tramos.get(tramos.size()-1).gety())+(tramos.get(tramos.size()-1).getalto())-12;
			y=(int)(Math.random()*(ymax-ymin))+ymin;
			tramos.add(new Tramo(xfinal,y,ancho,alto));
			xfinal=xfinal+tramos.get(tramos.size()-1).getancho();
		}
	}
}
