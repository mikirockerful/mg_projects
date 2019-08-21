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
public class Opciones extends JPanel{
	Ventana miventana;
	ArrayList<Integer> teclaspulsadas;
	ArrayList<Bolagrande> listabolas;
	Timer reloj;
	int factor;
	KeyListener keyboard;
	ImageIcon icono;
	Image imagenprev;
	Image imagen;
	Image fondo;
	Image option256;
	Image option512;
	Image option768;
	Image option1024;
	int selected;
	public Opciones(int factor, Ventana miventana){
		this.miventana=miventana;
		this.factor=factor;
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createMatteBorder(64, 48, 64, 48, new Color(255,255,0)));
		this.option256=this.obtensprite("resources/opciones256.png");
		this.option512=this.obtensprite("resources/opciones512.png");
		this.option768=this.obtensprite("resources/opciones768.png");
		this.option1024=this.obtensprite("resources/opciones1024.png");
		this.listabolas=new ArrayList<Bolagrande>();
		this.ponerbolas();
		this.selected=1;
		this.keyboard=new Teclado();
		this.setFocusable(true);
		this.addKeyListener(this.keyboard);
		reloj=new Timer();
		reloj.scheduleAtFixedRate(new Salta(), 100, 50);
	}
	public void ponerbolas(){
		this.listabolas.add(new Bolagrande(48*this.factor+48,6*this.factor+64,this.factor,1));
		this.listabolas.add(new Bolagrandemediana(202*this.factor+48,36*this.factor+64,this.factor,3));
		this.listabolas.get(1).setvx(-5*this.factor);
		this.listabolas.add(new Bolamedianapequena(135*this.factor+48,100*this.factor+64,this.factor,2));
		this.listabolas.add(new Bolamedianapequena(185*this.factor+48,160*this.factor+64,this.factor,1));
		this.listabolas.get(3).setvx(-5*this.factor);
		this.listabolas.add(new Bolagrande(130*this.factor+48,100*this.factor+64,this.factor,3));
		this.listabolas.get(4).setvx(-8*this.factor);
		this.listabolas.add(new Bolagrandemediana(0*this.factor+48,84*this.factor+64,this.factor,1));
		this.listabolas.get(5).setvx(8*this.factor);
		
		this.listabolas.add(new Bolagrande(200*this.factor+48,139*this.factor+64,this.factor,2));
		this.listabolas.add(new Bolagrandemediana(202*this.factor+48,96*this.factor+64,this.factor,1));
		this.listabolas.get(6).setvx(-2*this.factor);
		this.listabolas.add(new Bolamedianapequena(125*this.factor+48,20*this.factor+64,this.factor,2));
		this.listabolas.add(new Bolamedianapequena(20*this.factor+48,180*this.factor+64,this.factor,3));
		this.listabolas.get(7).setvx(-12*this.factor);
		this.listabolas.get(8).setvx(-4*this.factor);
		this.listabolas.add(new Bolagrandemediana(100*this.factor+48,104*this.factor+64,this.factor,3));
		this.listabolas.get(9).setvx(6*this.factor);
	}
	public void paint(Graphics g){
		super.paint(g);		
		
		
		
		for (int i=0;i<listabolas.size();i++){
			listabolas.get(i).pintar(g);
		}
		
		switch (this.selected){
		case 1: this.imagen=this.option256;
		break;
		case 2: this.imagen=this.option512;
		break;
		case 3: this.imagen=this.option768;
		break;
		case 4: this.imagen=this.option1024;
		break;
		}
		
		g.drawImage(this.imagen,48,64,null);
	}
	public void move(){
		for (int i=0;i<listabolas.size();i++){
			if (listabolas.get(i).mover(null,null)){
				break;
			}
		}

	}
	public Image obtensprite(String nombre){
		icono=new ImageIcon(nombre);
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(this.imagen);
		this.imagen=icono.getImage();
		return this.imagen;
	}
	private class Teclado implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {

				
				if((Integer)arg0.getKeyCode()==KeyEvent.VK_DOWN){
						Opciones.this.selected=Opciones.this.selected+1;
						if (Opciones.this.selected==5){
							Opciones.this.selected=1;
						}
				}
				if((Integer)arg0.getKeyCode()==KeyEvent.VK_UP){
					Opciones.this.selected=Opciones.this.selected-1;
					if (Opciones.this.selected==0){
						Opciones.this.selected=4;
					}
				}
				if((Integer)arg0.getKeyCode()==KeyEvent.VK_ENTER || (Integer)arg0.getKeyCode()==KeyEvent.VK_SPACE){
					if (Opciones.this.selected==1){
						reloj.cancel();
						Opciones.this.removeKeyListener(Opciones.this.keyboard);
						miventana.escalar(1);
						miventana.cambiarpanel("Menu");
					}
					if (Opciones.this.selected==2){
						reloj.cancel();
						Opciones.this.removeKeyListener(Opciones.this.keyboard);
						miventana.escalar(2);
						miventana.cambiarpanel("Menu");
					}
					if (Opciones.this.selected==3){
						reloj.cancel();
						Opciones.this.removeKeyListener(Opciones.this.keyboard);
						miventana.escalar(3);
						miventana.cambiarpanel("Menu");
					}
					if (Opciones.this.selected==4){
						reloj.cancel();
						Opciones.this.removeKeyListener(Opciones.this.keyboard);
						miventana.escalar(4);
						miventana.cambiarpanel("Menu");
					}
				}
				
				}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class Salta extends TimerTask{

		@Override
		public void run() {
			Opciones.this.requestFocus();
			move();
			repaint();
			
		}
		
	}
}
