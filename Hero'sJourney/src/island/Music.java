package island;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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