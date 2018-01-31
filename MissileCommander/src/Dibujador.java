import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Dibujador extends JPanel{
	Timer timer;
	Jugador jugador;
	ArrayList<Integer> teclaspulsadas;
	ArrayList<Misil> listamisiles;
	ArrayList<Asteroide> listaasteroides;
	int nivel;
	public Dibujador(){
		this.nivel=1;
		this.setBackground(Color.cyan);
		this.jugador=new Jugador();
		this.teclaspulsadas=new ArrayList<Integer>();
		this.listamisiles=new ArrayList<Misil>();
		this.listaasteroides=new ArrayList<Asteroide>();
		for (int i=0;i<this.nivel;i++){
			listaasteroides.add(new Asteroide());
		}
		this.setFocusable(true);
		this.addKeyListener(new Teclado());
		timer=new Timer();
		timer.scheduleAtFixedRate(new Reloj(),50,30);
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 550, 800, 50);
		
		for (int i=0;i<listaasteroides.size();i++){
			listaasteroides.get(i).pintar(g);
		}
		int i=0;
		while (i<listamisiles.size()){
			listamisiles.get(i).pintar(g);
			i=i+1;
		}
		jugador.pintar(g);
	}
	public void move(){
		jugador.mover(teclaspulsadas);
		for (int i=0;i<listaasteroides.size();i++){
			listaasteroides.get(i).mover();
		}
		int i=0;
		while (i<listamisiles.size()){
			listamisiles.get(i).mover(listamisiles,listaasteroides);
			i=i+1;
		}
		if (listaasteroides.size()==0){
			this.nivel=this.nivel+1;
			for (int ii=0;ii<this.nivel;ii++){
				listaasteroides.add(new Asteroide());
			}
		}
	}
	private class Teclado implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (!teclaspulsadas.contains((Integer)arg0.getKeyCode())){
				teclaspulsadas.add((Integer)arg0.getKeyCode());
			}
			if (arg0.getKeyCode()==KeyEvent.VK_SPACE){
				if (Misil.numero<3){
					jugador.ponmisil(listamisiles);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (teclaspulsadas.contains((Integer)arg0.getKeyCode())){
				teclaspulsadas.remove((Integer)arg0.getKeyCode());
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class Reloj extends TimerTask{

		@Override
		public void run() {
			Dibujador.this.move();
			Dibujador.this.repaint();
			
		}
		
	}
}
