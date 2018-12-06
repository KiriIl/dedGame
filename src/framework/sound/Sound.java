package framework.sound;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * 
 * @author Kirill
 *
 */
public class Sound{
	private String track;
	private Clip clip = null;
	private FloatControl volumeC = null;
	private float levelOfSound; // levelOfSound mb?
	public Sound(String track, float levelOfSound) {
		this.track = track;
		this.levelOfSound = levelOfSound;
	}
	public void  snd()
	{
		File f = new File(this.track);
		AudioInputStream tr = null;
		try {
			tr = AudioSystem.getAudioInputStream(f);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
			clip.open(tr);
			volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			clip.setFramePosition(0);
			clip.start();
		} catch(LineUnavailableException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setVolume() {
		if (levelOfSound<0) levelOfSound = 0.f;
		if (levelOfSound>1) levelOfSound = 1.f;
		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		volumeC.setValue((max-min) * levelOfSound + min);
	}
}