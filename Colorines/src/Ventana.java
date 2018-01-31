import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Ventana extends JFrame{
	public Ventana(){
		this.setSize(800,600);
		this.setResizable(false);
		this.setTitle("Colorines");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new Dibujador());

		this.setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Ventana miventana=new Ventana();

	}

}
