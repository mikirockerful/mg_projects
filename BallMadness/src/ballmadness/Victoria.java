package ballmadness;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Victoria extends JPanel {
	Ventana miventana;
	KeyListener keyboard;
	ImageIcon icono;
	Image imagenprev;
	Image imagen;
	Image fondo;
	Image titulo;
	int factor;
	Timer reloj;
	public Victoria(int factor, Ventana miventana){
		this.miventana=miventana;
		this.factor=factor;
		this.setBorder(BorderFactory.createMatteBorder(64, 48, 64, 48, Color.red));
		this.setBackground(Color.black);
		miventana.champions();
		this.titulo=obtensprite("resources/Victoria.png");
		this.fondo=obtensprite("resources/fondovictoria.png");
		this.keyboard=new Teclado();
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this.keyboard);
		reloj=new Timer();
		reloj.scheduleAtFixedRate(new Salta(), 100, 50);
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(this.fondo, 48, 64,null);
		g.drawImage(this.titulo, 48, 64,null);
	}
	public Image obtensprite(String nombre){
		icono=new ImageIcon(nombre);
		this.imagenprev=icono.getImage();
		this.imagen = imagenprev.getScaledInstance(this.imagenprev.getWidth(null)*this.factor, this.imagenprev.getHeight(null)*this.factor,  java.awt.Image.SCALE_SMOOTH);
		this.icono = new ImageIcon(this.imagen);
		this.imagen=icono.getImage();
		return this.imagen;
	}
	public void continuar(){
		reloj.cancel();
		miventana.pararvictoria();
		miventana.cambiarpanel("Titulo");
	}
	private class Teclado implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
				Victoria.this.removeKeyListener(Victoria.this.keyboard);
				Victoria.this.continuar();
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
			Victoria.this.requestFocus();
			repaint();
			
		}
		
	}

}
