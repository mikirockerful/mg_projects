package tron;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame{

	public Ventana(){
		add(new Dibujador());
		this.setSize(802, 632);
		this.setTitle("TRON");
		this.setResizable(true);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Ventana miventana=new Ventana();

	}

}
