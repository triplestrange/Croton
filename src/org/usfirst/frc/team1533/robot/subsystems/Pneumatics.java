package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author DJ Dierking
 */
public class Pneumatics extends Subsystem {

	// Utilize the first controller
	Joystick joy1;

	// Back Ramp Solenoids
	DoubleSolenoid sol1 = new DoubleSolenoid(Constants.Pneumatics.sol1_1, Constants.Pneumatics.sol1_2);
	DoubleSolenoid sol2 = new DoubleSolenoid(Constants.Pneumatics.sol2_1, Constants.Pneumatics.sol2_2);

	// Gearbox Solenoid
	DoubleSolenoid sol3 = new DoubleSolenoid(Constants.Pneumatics.sol3_1, Constants.Pneumatics.sol3_2);

	public void move(Joystick joy1) {

		// If "X" is pressed then shift the gearbox to low and drop the ramp
		if (joy1.getRawButtonPressed(Constants.Controller.X)) {
			sol1.set(DoubleSolenoid.Value.kReverse);
			sol2.set(DoubleSolenoid.Value.kForward);
			sol3.set(DoubleSolenoid.Value.kReverse);
		}

		// If "Y" is pressed then shift the gearbox to low
		if (joy1.getRawButtonPressed(Constants.Controller.Y)) {
			sol3.set(DoubleSolenoid.Value.kReverse);
		}

		// If the "Start" button is pressed, set everything to it's default position
		if (joy1.getRawButtonPressed(Constants.Controller.START)) {
			this.defaultvalue();
		}
	}

	public void defaultvalue() {
		// Default position
		sol1.set(DoubleSolenoid.Value.kForward);
		sol2.set(DoubleSolenoid.Value.kReverse);
		sol3.set(DoubleSolenoid.Value.kForward);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
