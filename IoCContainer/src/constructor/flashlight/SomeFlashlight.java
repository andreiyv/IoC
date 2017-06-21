package constructor.flashlight;

import constructor.battery.Battery;

public class SomeFlashlight implements Flashlight {

	private Battery battery;
	private boolean swithOn;
	
	public SomeFlashlight(Battery battery) {
		this.battery = battery;
		this.swithOn = false;
	}

	@Override
	public boolean isShines() {
		return (battery != null) && swithOn;
	}

	@Override
	public void swithOn() {
		if (!swithOn && battery != null) {
			swithOn = battery.getVoltage();			 
		}			
	}

	@Override
	public void swithOff() {
		swithOn = false;
	}

}
