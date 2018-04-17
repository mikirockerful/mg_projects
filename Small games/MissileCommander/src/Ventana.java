import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Ventana extends JFrame {

	public Ventana(){
		this.setSize(800,600);
		this.setResizable(false);
		this.setTitle("Missile Commander");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new Dibujador());
		this.setVisible(true);
	}
	@SuppressWarnings("unused")
	public static void main (String[] args){
		Ventana miventana=new Ventana();
	}
}
