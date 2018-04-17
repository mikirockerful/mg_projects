

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Dibujador extends JPanel{
	Timer timer;
	ArrayList<Integer> listateclas;
	ArrayList<Cuadro> listacuadros;
	Jugador jugador;
	public Dibujador(){
		jugador=new Jugador();
		listateclas=new ArrayList<Integer>();
		listacuadros=new ArrayList<Cuadro>();
		listacuadros.add(new Cuadro());
		this.setFocusable(true);
		this.addKeyListener(new Teclado());
		this.timer=(new Timer());
		timer.scheduleAtFixedRate(new Reloj(), 100, 10);
	}
	public void paint(Graphics g){
		super.paint(g);
		jugador.pintar(g);
		for (int i=0;i<listacuadros.size();i++){
			listacuadros.get(i).pintar(g);
		}
	}
	public void move(){
		jugador.mover(listateclas);
		for (int i=0;i<listacuadros.size();i++){
			listacuadros.get(i).mover(jugador.damepintados(),listacuadros);
		}
		jugador.actualizardisponibles(listacuadros.size());
		
	}
	private class Teclado extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (!listateclas.contains((Integer)arg0.getKeyCode())){
				listateclas.add((Integer)arg0.getKeyCode());
			}
			if (arg0.getKeyCode()==KeyEvent.VK_CONTROL){
				jugador.cambiarcolor();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (listateclas.contains((Integer)arg0.getKeyCode())){
				listateclas.remove((Integer)arg0.getKeyCode());
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {

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
