import javax.swing.JFrame;


@SuppressWarnings("serial")
public class PintaPantalla extends JFrame {

	public PintaPantalla (){
		this.setSize(800,600);
		
		this.setTitle("PintaPantalla");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new Dibujador());
		this.setVisible(true);

	}
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		PintaPantalla ventana= new PintaPantalla();
		
	}

}
