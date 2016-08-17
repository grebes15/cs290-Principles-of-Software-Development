package edu.allegheny.kinetic;

import java.lang.Math;

public class Kinetic {

	public static String computeVelocity(int kinetic, int mass) {

		int velocity_squared = 0;
		int velocity = 0;
		StringBuffer final_velocity = new StringBuffer();

		if (mass > 0 && kinetic >= 0) {
			velocity_squared = 2 * (kinetic / mass);
			velocity = (int) Math.sqrt(velocity_squared);
			final_velocity.append(velocity);
		}

		else {
			final_velocity.append("Undefined");
		}

		return final_velocity.toString();
	}
    
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        int kinetic = 0;
        int mass = 0;
        try {
            kinetic = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.exit(-1);
        }
        try {
            mass = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.exit(-1);
        }
        System.err.printf("The velocity is: %s%n", computeVelocity(kinetic, mass)); 
    }
}
