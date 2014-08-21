package figglewatts.slagd.util.sound;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;

public class SoundUtil {
	private SoundUtil() { }
	
	private static Random rand = new Random();
	
	public static void playRandomSound(Sound[] sounds) {
		sounds[rand.nextInt(sounds.length)].play(1F);
	}
	
	public static void playSoundRandomPitch(Sound sound, float volume, float pan) {
		sound.play(volume, 0.5F + rand.nextFloat()*2, pan);
	}
}
