import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Dibujador extends JPanel {
	Jugador jugador;
	Carretera carretera;
	public static ArrayList<Integer> teclaspulsadas=new ArrayList<Integer>();
	Timer timer;
	public Dibujador(){
		this.setBackground(new Color(0,0,0));
		this.jugador=new Jugador();
		this.carretera=new Carretera();
		this.setFocusable(true);
		this.addKeyListener(new Teclado());
		this.timer=new Timer();
		timer.scheduleAtFixedRate(new Reloj(), 100, 15);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		carretera.pintar(g);
		jugador.pintar(g);
		
		
	}
	public void mover(){
		jugador.mover(carretera);
		carretera.mover(jugador);
	}
	private class Reloj extends TimerTask{

		@Override
		public void run() {
			Dibujador.this.mover();
			Dibujador.this.repaint();
			
		}
		
	}
	private class Teclado implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (!teclaspulsadas.contains((Integer)arg0.getKeyCode())){
				teclaspulsadas.add((Integer)arg0.getKeyCode());
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
}
