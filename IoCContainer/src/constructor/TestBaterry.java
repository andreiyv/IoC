package constructor;

import static org.junit.Assert.*;
import constructor.battery.Battery;
import constructor.battery.ChinaBattery;
import constructor.flashlight.Flashlight;
import constructor.flashlight.SomeFlashlight;

import org.junit.Test;

public class TestBaterry {
		
	class DisposableBattery implements Battery{

		private boolean full = true;
		
		@Override
		public boolean getVoltage() {			
			if (full) {
				full = false;
				return true;
			}
			return false;
		}
	}
	
	@Test
	public void testDischargeNewBattery() {						
		Battery battery = new DisposableBattery();
		
		Flashlight flashlight = new SomeFlashlight(battery);		
		assertFalse(flashlight.isShines());
		
		flashlight.swithOn();		
		assertTrue(flashlight.isShines());
		
		flashlight.swithOff();		
		assertFalse(flashlight.isShines());
		
		flashlight.swithOn();		
		assertFalse(flashlight.isShines());		
	}
	
	@Test
	public void testBadBattery() {						
		Battery battery = new Battery(){
			@Override
			public boolean getVoltage() {			
				return false;
			}
		};
		
		Flashlight flashlight = new SomeFlashlight(battery);		
		assertFalse(flashlight.isShines());
		
		flashlight.swithOn();		
		assertFalse(flashlight.isShines());			
	}
	
	@Test
	public void testNoGetPowerIfDoubleSwithOn() {						
		Battery battery = new DisposableBattery();
		
		Flashlight flashlight = new SomeFlashlight(battery);		
		assertFalse(flashlight.isShines());
		
		flashlight.swithOn();		
		assertTrue(flashlight.isShines());
		
		flashlight.swithOn();		
		assertTrue(flashlight.isShines());			
	}
		
	@Test 
	public void testNoBatteryNoLight() {		
		Flashlight flashlight = new SomeFlashlight(null);
		
		assertFalse(flashlight.isShines());
		
		flashlight.swithOn();
		
		assertFalse(flashlight.isShines());	
	}
	
	@Test
	public void integrationTestGetPowerFormNewChinaBattery() {				
		Battery battery = new ChinaBattery();
		
		Flashlight flashlight = new SomeFlashlight(battery);
		
		assertFalse(flashlight.isShines());
		
		flashlight.swithOn();
		
		assertTrue(flashlight.isShines());	
	}
	
}
