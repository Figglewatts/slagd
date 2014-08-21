package figglewatts.slagd.util.timing;

public class Cooldown {
	public float coolDown; // cooldown time (in seconds)
	public boolean isOnCoolDown; // are we cooling down?
	public long timeStartCountdown; // the time when the cooldown started (millis)
	
	public Cooldown(float cooldown) {
		this.coolDown = cooldown;	
	}
	
	public void startCoolDown(float cooldown) {
		this.coolDown = cooldown;
		this.isOnCoolDown = true;
		this.timeStartCountdown = System.currentTimeMillis();
	}
	
	public void update() {
		if (this.isOnCoolDown) {
			long diff = System.currentTimeMillis() - this.timeStartCountdown;
			// convert to millis
			if (diff >= (coolDown*1000)) {
				this.isOnCoolDown = false; // finished cooling down
			}
		}
	}
}

