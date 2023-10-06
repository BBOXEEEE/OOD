
public class Motorcycle extends ChargeableVehicle{
	private int fuel;
	private boolean isTurnOn;
	
	public Motorcycle() {
		this.fuel = 100;
		this.isTurnOn = false;
	}
	
	@Override
	public void turnOn() {
		if(!isTurnOn && fuel > 0) {
			System.out.println("Motorcycle: Turn On!");
			isTurnOn = true;
		} else {
			System.out.println("Motorcycle: Already Turn On!");
		}
	}

	@Override
	public void turnOff() {
		if(isTurnOn) {
			System.out.println("Motorcycle: Turn Off!");
			isTurnOn = true;
		} else {
			System.out.println("Motorcycle: Already Turn Off!");
		}
	}
	
	@Override
	public void start() {
		if(isTurnOn && fuel > 0) {
			System.out.println("Motorcycle: start!");
		} else {
			System.out.println("Motorcycle: Can't start!");
		}
	}

	@Override
	public void stop() {
		if(isTurnOn && fuel > 0) {
			System.out.println("Motorcycle: stop!");
		} else {
			System.out.println("Motorcycle: Can't stop!");
		}
	}

	@Override
	public void accelerate() {
		if(isTurnOn && fuel > 0) {
			System.out.println("Motorcycle: accelerate!");
			--fuel;
		} else {
			System.out.println("Motorcycle: Can't accelerate!");
		}
	}

	@Override
	public void brake() {
		if(isTurnOn && fuel > 0) {
			System.out.println("Motorcycle: brake!");
			--fuel;
		} else {
			System.out.println("Motorcycle: Can't brake!");
		}
	}

	@Override
	public void charge() {
		System.out.println("Motorcycle: charge to 100%!");
		this.fuel = 100;
	}
}
