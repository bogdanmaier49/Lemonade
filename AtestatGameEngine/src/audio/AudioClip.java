package audio;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class AudioClip {
	
	private String path;
	private Audio audio = null;
	
	public float pitch = 1.0f;
	public float gain = 1.0f;
	public boolean loop = false;
	
	public AudioClip (String path){
		this.path = path;
		
		try {
			audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void PlayAsEffect (){
		audio.playAsSoundEffect(pitch, gain, loop);
	}
	
	public void PlayAsMusic (){
		audio.playAsMusic(pitch, gain, loop);
	}
	
	public void Stop (){
		audio.stop();
	}
	
	public boolean isPlaying (){
		return audio.isPlaying();
	}
	
	public String getPath (){
		return this.path;
	}
	
}
