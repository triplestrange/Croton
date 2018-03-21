package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Joystick joy2;
	DoubleSolenoid Solenoid1 = new DoubleSolenoid(1, 2);
	DoubleSolenoid Solenoid2 = new DoubleSolenoid(3, 4);
	DoubleSolenoid Solenoid3 = new DoubleSolenoid(5, 6);

	public void move(Joystick joy2) {
		if (joy2.getRawButtonPressed(Constants.Controller.BACK)) {
    	Solenoid1.set(DoubleSolenoid.Value.kReverse);
    	Solenoid2.set(DoubleSolenoid.Value.kReverse);
    	Solenoid3.set(DoubleSolenoid.Value.kReverse);
		}
		else {
	    	Solenoid1.set(DoubleSolenoid.Value.kForward);
	    	Solenoid2.set(DoubleSolenoid.Value.kForward);
	    	Solenoid3.set(DoubleSolenoid.Value.kForward);			
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

