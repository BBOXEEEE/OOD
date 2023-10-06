
public class NonChargeableVehicle extends Vehicle{

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
	public void turnOn() {}
	@Override
	public void turnOff() {}
	@Override
	public void charge() {}

}
