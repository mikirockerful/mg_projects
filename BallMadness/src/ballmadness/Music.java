package ballmadness;

import java.io.File;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Music {

	AudioInputStream audioInputStream;
	Clip clip;
	Clip fanfare;
	Clip champions;
	public Music(){
		try {
		    this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/MadeOfGlassb.wav").getAbsoluteFile());
		    this.clip = AudioSystem.getClip();
		    this.clip.open(audioInputStream);
		    this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/fanfare.wav").getAbsoluteFile());
		    this.fanfare = AudioSystem.getClip();
		    this.fanfare.open(audioInputStream);
		    this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/champions.wav").getAbsoluteFile());
		    this.champions = AudioSystem.getClip();
		    this.champions.open(audioInputStream);
		    } catch(Exception e) {
		    e.printStackTrace();
		    }

	}
	public void iniciar(){
		this.clip.setFramePosition(0);
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void pararvictoria(){
		this.champions.stop();
		this.champions.setFramePosition(0);
		
	}
	public void playfanfare(){
		this.clip.stop();
		this.fanfare.setFramePosition(0);
		this.fanfare.start();
	}
	public void parar(){
		this.clip.stop();
	}
	public void continuar(){
		this.clip.start();
	}
	public void champions(){
		this.champions.loop(73);
	}
}
