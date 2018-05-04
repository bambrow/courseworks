package speeding;

import cse131.ArgsProcessor;

public class SpeedLimit {

	public static void main(String[] args) {
        ArgsProcessor ap = new ArgsProcessor(args);
        int currentSpeed = ap.nextInt("Enter the speed:");
        int speedLimit = ap.nextInt("Enter the speed limit:");
        
        int fine = (currentSpeed > speedLimit) ? ((currentSpeed > (speedLimit + 10)) ? (50 + (currentSpeed - speedLimit - 10) * 10) : 50) : 0;
        System.out.println("You reported a speed of " + currentSpeed + " for a speed limit of " + speedLimit + " MPH.");
        System.out.println("You went " + (currentSpeed > speedLimit ? (currentSpeed - speedLimit) : 0) + " MPH over the speed limit. Your fine is $" + fine + ".");
	}

}
