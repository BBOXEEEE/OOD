
public class ElectricBicycle extends ChargeableVehicle{
	private int battery;
	private boolean isTurnOn;
	
	public ElectricBicycle() {
		this.battery = 100;
		this.isTurnOn = false;
	}
	
	@Override
	public void turnOn() {
		if(!isTurnOn) {
			System.out.println("ElectricBicycle: Turn On!");
			isTurnOn = true;
		} else {
			System.out.println("ElectricBicycle: Already Turn On!");
		}
	}

	@Override
	public void turnOff() {
		if(isTurnOn) {
			System.out.println("ElectricBicycle: Turn Off!");
			isTurnOn = true;
		} else {
			System.out.println("ElectricBicycle: Already Turn Off!");
		}
	}
	
	@Override
	public void start() {
		if(isTurnOn && battery > 0) {
			System.out.println("ElectricBicycle: start!");
		} else {
			System.out.println("ElectricBicycle: Can't start!");
		}
	}

	@Override
	public void stop() {
		if(isTurnOn && battery > 0) {
			System.out.println("ElectricBicycle: stop!");
		} else {
			System.out.println("ElectricBicycle: Can't stop!");
		}
	}

	@Override
	public void accelerate() {
		if(isTurnOn && battery > 0) {
			System.out.println("ElectricBicycle: accelerate!");
			--battery;
		} else {
			System.out.println("ElectricBicycle: Can't accelerate!");
		}
	}

	@Override
	public void brake() {
		if(isTurnOn && battery > 0) {
			System.out.println("ElectricBicycle: brake!");
			--battery;
		} else {
			System.out.println("ElectricBicycle: Can't brake!");
		}
	}

	@Override
	public void charge() {
		System.out.println("ElectricBicycle: charge to 100%!");
		this.battery = 100;
	}
}
