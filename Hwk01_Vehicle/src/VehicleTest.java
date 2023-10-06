
public class VehicleTest {

	public static void main(String[] args) {
		//Bicycle
		Vehicle bicycle = new Bicycle();
		bicycle.start();
		bicycle.accelerate();
		bicycle.brake();
		bicycle.stop();
		
		//ElectricBicycle
		Vehicle electricBicycle = new ElectricBicycle();
		electricBicycle.turnOn();
		electricBicycle.start();
		electricBicycle.accelerate();
		electricBicycle.brake();
		electricBicycle.stop();
		electricBicycle.charge();
		electricBicycle.turnOff();
		
		//MotorCycle
		Vehicle motorcycle = new Motorcycle();
		motorcycle.turnOn();
		motorcycle.start();
		motorcycle.accelerate();
		motorcycle.brake();
		motorcycle.stop();
		motorcycle.charge();
		motorcycle.turnOff();
	}

}
