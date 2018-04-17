import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JPanel;




@SuppressWarnings("serial")
public class Dibujador extends JPanel {
	Timer timer;
	Jugador jugador;
	public static ArrayList<Integer> teclaspulsadas;
	public static float areapintada=0;
	ArrayList<Rectangulos> listarect;
	ArrayList<Enemigo> listaenemigos;
	Dibujador yo;
	public static int nivel=1;
	
	public Dibujador(){
		yo=this;
		teclaspulsadas=new ArrayList<Integer>();
		listarect=new ArrayList<Rectangulos>();
		listaenemigos=new ArrayList<Enemigo>();
		this.setBackground(new Color(0,0,0));
		jugador=new Jugador(this);
		
		listarect.add(new Rectangulos(10,10,775,1));
		listarect.get(listarect.size()-1).anadirarea();
		listarect.add(new Rectangulos(10,10,1,553));
		listarect.get(listarect.size()-1).anadirarea();
		listarect.add(new Rectangulos(785,10,1,553));
		listarect.get(listarect.size()-1).anadirarea();
		listarect.add(new Rectangulos(10,563,775,1));
		listarect.get(listarect.size()-1).anadirarea();
		
		for (int i=0;i<nivel;i++){
			listaenemigos.add(new Enemigo());
		}
		
		this.addKeyListener(new Teclado());
		setFocusable(true);
		this.timer=new Timer();
		this.timer.scheduleAtFixedRate(new reloj(),100,10);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		for (int i=0;i<listarect.size();i++){
			listarect.get(i).pintar(g);
		}
		for (int i=0;i<listaenemigos.size();i++){
			listaenemigos.get(i).pintar(g);
		}
		jugador.pintar(g);
	}
	public void move(){
		for (int i=0;i<listaenemigos.size();i++){
			listaenemigos.get(i).mover(listarect);
		}
		jugador.mover(this.listarect);
	}
	public void comprobar(){
		if (Dibujador.areapintada>230000){
			System.out.println("Has ganado!");
			Dibujador.nivel+=1;
			Dibujador.areapintada=0;
			this.reinicia();
		}
		jugador.miraenemigos(listaenemigos);
	}
	public void reinicia(){
		this.timer.cancel();
		listarect=new ArrayList<Rectangulos>();
		listaenemigos=new ArrayList<Enemigo>();
		this.setBackground(new Color(0,0,0));
		jugador=new Jugador(this);
		
		listarect.add(new Rectangulos(10,10,775,1));
		listarect.get(listarect.size()-1).anadirarea();
		listarect.add(new Rectangulos(10,10,1,553));
		listarect.get(listarect.size()-1).anadirarea();
		listarect.add(new Rectangulos(785,10,1,553));
		listarect.get(listarect.size()-1).anadirarea();
		listarect.add(new Rectangulos(10,563,775,1));
		listarect.get(listarect.size()-1).anadirarea();
		
		for (int i=0;i<nivel;i++){
			listaenemigos.add(new Enemigo());
		}
		
		this.addKeyListener(new Teclado());
		setFocusable(true);
		this.timer=new Timer();
		this.timer.scheduleAtFixedRate(new reloj(),100,10);
	}
	private class Teclado extends KeyAdapter{

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
			if ((Integer)arg0.getKeyCode()==(Integer)KeyEvent.VK_SPACE){
				jugador.sueltoespacio(listarect);
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class reloj extends TimerTask{

		@Override
		public void run() {
			Dibujador.this.move();
			Dibujador.this.repaint();
			Dibujador.this.comprobar();
			
		}
		
	}
}
