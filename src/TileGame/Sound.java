package TileGame;


import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public void playMusic(String musicLocation) {
		InputStream music;
		try {
			File musicPath = new File(musicLocation);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				if (musicLocation == "BGM.wav") {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}