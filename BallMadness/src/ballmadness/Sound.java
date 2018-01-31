package ballmadness;


import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	AudioInputStream audioInputStream;
	Clip disparo;
	Clip disparo2;
	Clip disparo3;
	Clip disparo4;
	Clip disparo5;
	Clip explosion;
	Clip explosion2;
	Clip explosion3;
	Clip explosion4;
	Clip explosion5;
	
	int elige;
	int elige2;
	
	public Sound(){
		this.elige=1;
		this.elige2=1;
	    try {
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/disparo.wav").getAbsoluteFile());
		this.disparo = AudioSystem.getClip();
		this.disparo.open(audioInputStream);
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/disparo.wav").getAbsoluteFile());
		this.disparo2 = AudioSystem.getClip();
		this.disparo2.open(audioInputStream);
		
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/disparo.wav").getAbsoluteFile());
		this.disparo3 = AudioSystem.getClip();
		this.disparo3.open(audioInputStream);
		
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/disparo.wav").getAbsoluteFile());
		this.disparo4 = AudioSystem.getClip();
		this.disparo4.open(audioInputStream);
		
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/disparo.wav").getAbsoluteFile());
		this.disparo5 = AudioSystem.getClip();
		this.disparo5.open(audioInputStream);
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosion.wav").getAbsoluteFile());
		this.explosion = AudioSystem.getClip();
		this.explosion.open(audioInputStream);
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosion.wav").getAbsoluteFile());
		this.explosion2 = AudioSystem.getClip();
		this.explosion2.open(audioInputStream);
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosion.wav").getAbsoluteFile());
		this.explosion3 = AudioSystem.getClip();
		this.explosion3.open(audioInputStream);
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosion.wav").getAbsoluteFile());
		this.explosion4 = AudioSystem.getClip();
		this.explosion4.open(audioInputStream);
		
		this.audioInputStream = AudioSystem.getAudioInputStream(new File("resources/explosion.wav").getAbsoluteFile());
		this.explosion5 = AudioSystem.getClip();
		this.explosion5.open(audioInputStream);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disparo(){
		switch (this.elige){
		case 1:
		this.disparo.setFramePosition(0);
		this.disparo.start();
		break;
		case 2:
		this.disparo2.setFramePosition(0);
		this.disparo2.start();
		break;
		case 3:
		this.disparo3.setFramePosition(0);
		this.disparo3.start();
		break;
		case 4:
		this.disparo4.setFramePosition(0);
		this.disparo4.start();
		break;
		case 5:
		this.disparo5.setFramePosition(0);
		this.disparo5.start();
		break;
		}
		this.elige=this.elige+1;
		if (this.elige==6){
			this.elige=1;
		}
	}
	public void explosion(){
		switch (this.elige2){
		case 1:
		this.explosion.setFramePosition(0);
		this.explosion.start();
		break;
		case 2:
		this.explosion2.setFramePosition(0);
		this.explosion2.start();
		break;
		case 3:
		this.explosion3.setFramePosition(0);
		this.explosion3.start();
		break;
		case 4:
		this.explosion4.setFramePosition(0);
		this.explosion4.start();
		break;
		case 5:
		this.explosion5.setFramePosition(0);
		this.explosion5.start();
		break;
		}
		this.elige2=this.elige2+1;
		if (this.elige2==6){
			this.elige2=1;
		}
	}
}
