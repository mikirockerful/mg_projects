package ballmadness;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Dibujador extends JPanel {
	Ventana miventana;
	Jugador jugador1;
	ArrayList<Integer> teclaspulsadas;
	ArrayList<Bala> listabalas;
	ArrayList<Bolagrande> listabolas;
	KeyListener keyboard;
	Timer reloj;
	int limitebalas;  
	int factor;
	int nivel;
	int tipobala;
	int previo=1;
	boolean ganado=false;
	boolean pasando=false;
	Image intro;
	Image imagen;
	Image imagenprev;
	ImageIcon icono;
	int pres;
	
	public Dibujador(int factor, Ventana miventana) {
		this.miventana=miventana;
		this.factor=factor;
		this.nivel=1;
		this.cargarimagen("resources/ready2.png");
		this.teclaspulsadas=new ArrayList<Integer>();
		this.listabalas=new ArrayList<Bala>();
		this.jugador1=new Jugador(this, this.factor);
		this.keyboard=new Teclado();
		this.setFocusable(true);
		this.addKeyListener(this.keyboard);
		reloj=new Timer();
		reloj.scheduleAtFixedRate(new Salta(), 1000, 50);
		this.iniciarnivel(this.nivel);
		 
	}
	public void paint(Graphics g){
	if (!ganado){
			super.paint(g);
			this.setBackground(Color.black);
			for (int i=0;i<listabalas.size();i++){
				listabalas.get(i).pintar(g);
			}
			for (int i=0;i<listabolas.size();i++){
				listabolas.get(i).pintar(g);
			}
			jugador1.pintar(g,this.teclaspulsadas);
			g.drawImage(this.intro, 48, 64, null);
		}
	else{
		g.drawImage(this.intro, 48, 64, null);
	}
	}
	public void move(){
		
		if(jugador1.mover(teclaspulsadas,listabolas,this)){
			
		for (int i=0;i<listabalas.size();i++){
				listabalas.get(i).mover(listabalas,i,listabolas,miventana);
		}

		for (int i=0;i<listabolas.size();i++){
			if (listabolas.get(i).mover(this.jugador1,this)){
				break;
			}
		}

	}
		
		
	}
	public void cargarimagen(String nombre){
		this.icono=new ImageIcon(nombre);//Reescalado de la imagen mediante el getScaledInstance y creación de un nuevo icono y su correspondiente imagen.
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(imagen);
		this.intro=icono.getImage();
	}
	public void intro(){
		cargarimagen("resources/ready"+this.nivel+".png");
		this.repaint();
	}
	public void iniciarnivel(int nivel) {
		switch (nivel){
		case 1: 
			reloj.cancel();
			this.setBorder(BorderFactory.createMatteBorder(64, 48, 64, 48, Color.red));
			this.tipobala=1;
			this.limitebalas=1;
			this.listabolas=new ArrayList<Bolagrande>();
			this.jugador1.centrar();
			listabolas.add(new Bolagrandemediana(62*this.factor+48,36*this.factor+64,this.factor,2));
			this.intro();
			reloj=new Timer();
			reloj.scheduleAtFixedRate(new Salta(), 1000, 50);
			break;
		case 2: 
			reloj.cancel();
			if (this.previo==1){
			this.jugador1.sumarvida();
			this.jugador1.sumarvida();
			this.previo=2;
			}
			this.setBorder(BorderFactory.createMatteBorder(64, 48, 64, 48, Color.blue));
			this.tipobala=2;
			this.limitebalas=1;
			this.listabolas=new ArrayList<Bolagrande>();
			this.jugador1.centrar();
			listabolas.add(new Bolagrandemediana(62*this.factor+48,36*this.factor+64,this.factor,3));
			this.intro();
			reloj=new Timer();
			reloj.scheduleAtFixedRate(new Salta(), 1000, 50);
			break;
		case 3: 
			reloj.cancel();
			if (this.previo==2){
			this.jugador1.sumarvida();
			this.jugador1.sumarvida();
			this.previo=3;
			}
			this.setBorder(BorderFactory.createMatteBorder(64, 48, 64, 48, Color.green));
			this.tipobala=3;
			this.limitebalas=3;
			this.listabolas=new ArrayList<Bolagrande>();
			this.jugador1.centrar();
			listabolas.add(new Bolagrande(62*this.factor+48,36*this.factor+64,this.factor,1));
			this.intro();
			reloj=new Timer();
			reloj.scheduleAtFixedRate(new Salta(), 1000, 50);
			break;
		case 4:
			reloj.cancel();
			if (this.previo==3){
			this.jugador1.sumarvida();
			this.jugador1.sumarvida();
			this.previo=4;
			}
			this.setBorder(BorderFactory.createMatteBorder(64, 48, 64, 48, Color.yellow));
			this.tipobala=2;
			this.limitebalas=3;
			this.listabolas=new ArrayList<Bolagrande>();
			this.jugador1.centrar();
			listabolas.add(new Bolamediana(10*this.factor+48,36*this.factor+64,this.factor,2));
			listabolas.get(listabolas.size()-1).setvx(9*factor);
			listabolas.add(new Bolamediana(214*this.factor+48,36*this.factor+64,this.factor,2));
			listabolas.get(listabolas.size()-1).setvx(-9*factor);
			
			
			this.intro();
			reloj=new Timer();
			reloj.scheduleAtFixedRate(new Salta(), 1000, 50);
			break;
		
		case 5:
			reloj.cancel();
			this.ganado=true;
			miventana.pararmusica();
			miventana.cambiarpanel("Victoria");
			break;
		}
	}
	public void resetlevel(){
		reloj.cancel();
		this.listabalas.clear();
		this.iniciarnivel(this.nivel);
	}
	public void resetgame(){
		reloj.cancel();
		Dibujador.this.removeKeyListener(this.keyboard);
		miventana.cambiarpanel("Titulo");
	}
	public void pasarnivel(){
		this.listabalas.clear();
		miventana.continuarmusica();
		this.nivel=this.nivel+1;
		this.iniciarnivel(this.nivel);

	}
	private class Teclado implements KeyListener{
	
		@Override
		public void keyPressed(KeyEvent arg0) {
			
			if (!teclaspulsadas.contains((Integer)arg0.getKeyCode())){
				teclaspulsadas.add((Integer)arg0.getKeyCode());
			}
			if((Integer)arg0.getKeyCode()==KeyEvent.VK_SPACE){
				if (listabalas.size()<limitebalas){
					if (Dibujador.this.tipobala==1){
				listabalas.add(new Bala(jugador1.getx(),Dibujador.this.factor,1));
					}else if (Dibujador.this.tipobala==2){
				listabalas.add(new Balatrampa(jugador1.getx(),Dibujador.this.factor,1));
					} else if (Dibujador.this.tipobala==3){
				listabalas.add(new Bala(jugador1.getx(),Dibujador.this.factor,2));
					}
				jugador1.suelto=true;
				if (!Dibujador.this.pasando){
				miventana.disparo();
				}
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
	private class Salta extends TimerTask{

		@Override
		public void run() {
			Dibujador.this.requestFocus();
			move();
			repaint();
			Dibujador.this.intro=null;
			if (listabolas.size()==0){
				try {
					miventana.fanfare();
					Dibujador.this.pasando=true;
					Thread.sleep(4000);
					Dibujador.this.pasando=false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Dibujador.this.pasarnivel();
			}
		}

		
	}

}
