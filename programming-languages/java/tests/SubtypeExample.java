package tests;

class Vehicle {
	int color;
	int speed;
	int pos;
	
	public Vehicle(int color, int speed, int pos) {
		this.color = color;
		this.speed = speed;
		this.pos = pos;
	}
	
	public Vehicle() {
		this(0,0,0);
	}
	
	public void addPos() {
		this.pos++;
	}
}

class Airplane extends Vehicle {
	int altitude;
	
	public Airplane(int color, int speed, int pos, int altitude) {
		super(color, speed, pos);
		this.altitude = altitude;
	}
	
	public Airplane() {
		super();
		this.altitude = 0;
	}
	
	@Override
	public void addPos() {
		this.pos += 2;
	}
}

public class SubtypeExample {
	
	static void speedUp(Vehicle v) {
		v.speed += 20;
	}
	
	static void flyUp(Airplane a) {
		a.altitude += 100;
	}
	
	static void changePos(Vehicle v) {
		v.addPos();
	}

	public static void main(String[] args) {
		
		Airplane airplane = new Airplane();
		speedUp(airplane);
		System.out.println(airplane.speed); // 20
		Vehicle vehicle = new Vehicle();
		// flyUp(vehicle); 
		changePos(airplane);
		System.out.println(airplane.pos); // 2
		changePos(vehicle);
		System.out.println(vehicle.pos); // 1
		
		Vehicle vehicle2 = new Airplane();
		speedUp(vehicle2);
		// flyUp(vehicle2);
		changePos(vehicle2);
		System.out.println(vehicle2.pos); // 2
		// this still calls the Airplane version

	}

}
