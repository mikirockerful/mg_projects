package ballmadness;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame{
	int factor;//Reescalado
	Music musica;
	Sound sonido;
	public Ventana(){
		this.factor=1;
		this.musica=new Music();
		this.sonido=new Sound();
		this.musica.iniciar();
		this.setTitle("Ball Madness");
		this.setSize(256*this.factor+96+6,192*this.factor+128+28);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.add(new Dibujador(this.factor,this));
		this.add(new Titulo(this.factor,this));
		this.setVisible(true);
		//System.out.println(this.getInsets());
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Ventana miventana=new Ventana();
	}
	public void escalar(int factor){
		this.factor=factor;
		this.setSize(256*this.factor+96+6,192*this.factor+128+28);
	}
	public void fanfare(){
		this.musica.playfanfare();
	}
	public void champions(){
		this.musica.champions();
	}
	public void pararvictoria(){
		this.musica.pararvictoria();
	}
	public void continuarmusica(){
		this.musica.continuar();
	}
	public void disparo(){
		this.sonido.disparo();
	}
	public void explosion(){
		this.sonido.explosion();
	}
	public void pararmusica(){
		this.musica.parar();
	}
	public void cambiarpanel(String panel){
		switch (panel){
		case "Dibujador":
			this.setContentPane(new Dibujador(this.factor,this));
			this.setFocusable(true);
			this.setVisible(true);
			break;
		case "Menu":
			this.setContentPane(new Menu(this.factor,this));
			this.setFocusable(true);
			this.setVisible(true);
			break;
		case "Opciones":
			this.setContentPane(new Opciones(this.factor,this));
			this.setFocusable(true);
			this.setVisible(true);
			break;
		case "Victoria":
			this.setContentPane(new Victoria(this.factor,this));
			this.setFocusable(true);
			this.setVisible(true);
			break;
		case "Titulo":
			this.musica.parar();
			this.musica.iniciar();
			this.setContentPane(new Titulo(this.factor,this));
			this.setFocusable(true);
			this.setVisible(true);
			break;
		}
	}

}
