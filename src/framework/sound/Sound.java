package framework.sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    AudioInputStream in;
    AudioFormat decodedFormat;
    AudioInputStream din;
    AudioFormat baseFormat;
    SourceDataLine line;
    String filename;
    FloatControl volumeC = null;
    float levelOfSound;
    private boolean loop;
    private BufferedInputStream stream;
    
    public void reset() {
        try {
        	if(line.isOpen())
        		stream.reset();
    		in = AudioSystem.getAudioInputStream(new BufferedInputStream(ded.Snds.class.getResourceAsStream(filename)));
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            line = getLine(decodedFormat);
        } 
        catch (Exception e) { e.printStackTrace(); }
    }
    
    public void close() {
        try {
            line.close();
            din.close();
            in.close();
        } catch (IOException e) {}
    }

    Sound(String filename, boolean loop) {
    	levelOfSound = (float)ded.game.state.Settings.volume/10;
        this.filename = filename;
        this.loop = loop;
    }

    public Sound(String filename) {
    	levelOfSound = (float)ded.game.state.Settings.volume/10;
        this.loop = false;
        this.filename = filename;
        InputStream raw = ded.Snds.class.getResourceAsStream(filename);
        stream = new BufferedInputStream(raw);
            try {
				in = AudioSystem.getAudioInputStream(stream);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            din = null;

            if (in != null) {
                baseFormat = in.getFormat();
                decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
                din = AudioSystem.getAudioInputStream(decodedFormat, in);
                try {
					line = getLine(decodedFormat);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
            }
    }

    private SourceDataLine getLine(AudioFormat audioFormat)
            throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }
    
    public void setVolume() {
    	levelOfSound = (float)ded.game.state.Settings.volume/10;
    	if (levelOfSound<0) levelOfSound = 0.f;
    	if (levelOfSound>1) levelOfSound = 1.f;
    	float min = volumeC.getMinimum();
    	float max = volumeC.getMaximum();
    	volumeC.setValue((max-min) * levelOfSound+min);
    }
    
    public void play() {
        try {
            boolean firstTime = true;
            while (firstTime || loop) {
                firstTime = false;
                byte[] data = new byte[512];
                if (line != null) {
                	volumeC = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                	setVolume();
                    line.start();
                    int nBytesRead = 0;

                    while (nBytesRead != -1) {
                        nBytesRead = din.read(data, 0, data.length);
                        if (nBytesRead != -1)
                            line.write(data, 0, nBytesRead);
                    }
                    line.drain();
                    line.stop();
                    line.close();
                    reset();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}