package edu.allegheny.kinetic;

import java.lang.Math;

public class Kinetic {
    /**
     * computeVelocity - is the working method of the Kenetic class it computes the velocity based on the user inputed numbers
     *                   We found an error which was the eqution had a 3 multiplier instead of a 2.  Also negative value handling
     * @param kinetic- kenitic force, a non-negative non-zero number
     * @param mass- Mass of object, non-negative, non-zero number
     * @returns the toString with the answer appended to it, if failed, the toString will return "Undefined"
     */
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

}
