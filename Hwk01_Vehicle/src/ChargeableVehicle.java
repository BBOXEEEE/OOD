
public class ChargeableVehicle extends Vehicle{

	@Override
	public void start() {
		System.out.println("Start!");
	}

	@Override
	public void stop() {
		System.out.println("Stop!");	
	}

	@Override
	public void accelerate() {
		System.out.println("Accelerate!");
	}

	@Override
	public void brake() {
		System.out.println("Brake!");	
	}

	@Override
	public void turnOn() {
		System.out.println("Turn On!");
	}
	@Override
	public void turnOff() {
		System.out.println("Turn Off!");
	}
	@Override
	public void charge() {
		System.out.println("Charge!");
	}

}
