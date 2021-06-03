package island;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music  implements Runnable  {
	Thread t;
	File audioFile ;
    AudioInputStream audioStream;
    Clip audioClip;
    String fn;
    String songName;
    
	public Music(String fileName, boolean loops, String songName) {
		this.songName = songName;
		fn = fileName;
		audioFile = new File(fileName);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format);
	        audioClip = (Clip) AudioSystem.getLine(info);
	        
	        if(loops) {
	        	audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
	        }	        
	        audioClip.open(audioStream);
	        //audioClip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		start3();
	}
	public void stop() {
		audioClip.stop();
	}
	public boolean isStopped() {
		return !audioClip.isActive();
	}
	public float getVolume() {
	    FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) (100*Math.pow(10f, gainControl.getValue() / 20f));
	}

	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
	public void start3() {
	     t = new Thread (this, fn);
	     start2();
	     t.start ();
	}
	public void start() {
	     t = new Thread (this, fn);
	     t.start ();
	}
	public void start2() {
		audioFile = new File(fn);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format);
	        audioClip = (Clip) AudioSystem.getLine(info);
	        audioClip.open(audioStream);
	        audioClip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loop() {
		audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
		audioClip.start();
	}
	
	public String getSongName() {
		return songName;
	}
	
	@Override
	public void run() {
		 audioClip.start();
	}
}