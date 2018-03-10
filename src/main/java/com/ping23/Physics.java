package main.java.com.ping23;

public class Physics {

	public static void main(String[] args) {

		double deltaS = 47.5;
		double deltaT = 13.12;

		double averageSpeed = getAverageSpeed(deltaS, deltaT);

		System.out.println("for distance = " + deltaS + " and time = " + deltaT + ": averageSpeed = " + averageSpeed);

	}

	/**
	 * get the average speed given deltaS and deltaT
	 */
	public static double getAverageSpeed(double deltaS, double deltaT) {

		double averageSpeed = 0.0;

		averageSpeed = deltaS / deltaT;

		return averageSpeed;

	}

}
