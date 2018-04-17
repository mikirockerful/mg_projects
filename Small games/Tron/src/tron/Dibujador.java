package tron;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Dibujador extends JPanel {
	
	public static int[][] matriz;
	Timer temporizador;
	Jugador jugador1;
	Jugador2 jugador2;
	
	public Dibujador (){
		matriz=new int[593][785];
		for (int i=0;i<592;i++){
			for (int j=0;j<784;j++){
				matriz[i][j]=0;
			}
		}
		
		this.jugador1=new Jugador();
		this.jugador2=new Jugador2();
		addKeyListener(new Teclado());
		setFocusable(true);
		temporizador=new Timer();
		temporizador.scheduleAtFixedRate(new Repintado(), 100, 17);

		
	}
	public void paint(Graphics g){
		
		//super.paint(g);
		jugador1.pintar(g);
		jugador2.pintar(g);
	}
	public void move(){
		jugador1.mover();
		jugador2.mover();
	}
	private class Repintado extends TimerTask{
		
		@Override
		public void run(){
			Dibujador.this.move();
			Dibujador.this.repaint();
			
		}
	}
	private class Teclado implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			jugador1.teclapulsada(arg0.getKeyCode());
			jugador2.teclapulsada(arg0.getKeyCode());
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
