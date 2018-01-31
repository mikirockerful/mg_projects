import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Tunel extends JFrame{
	
	public Tunel(){
		this.setSize(800,600);
		this.setResizable(false);
		this.add(new Dibujador());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Tunel miventana=new Tunel();

	}

}
