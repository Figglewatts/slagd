package figglewatts.slagd.util.timing;

import com.badlogic.gdx.utils.Array;

public class TimerManager {
	private TimerManager() { }
	
	public static Array<Cooldown> timers = new Array<Cooldown>();
	
	public static boolean cooldownDone(int index) {
		try {
			return !timers.get(index).isOnCoolDown;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
}
