package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.MotionProfile;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeMech extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_VictorSPX IntakeMotorR = new WPI_VictorSPX(4);
	WPI_VictorSPX IntakeMotorL = new WPI_VictorSPX(3);
	Joystick joy2;
	
	public void testing(Joystick joy2) {
		this.joy2 = joy2;
		
		if(joy2.getRawButton(3)) {
			IntakeMotorR.set(.66);
			IntakeMotorL.set(-.66);
		}
		else if(joy2.getRawButton(2)) {
			IntakeMotorR.set(-1);
			IntakeMotorL.set(1);
		}
		else {
			IntakeMotorR.set(0);
			IntakeMotorL.set(0);
		}
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

