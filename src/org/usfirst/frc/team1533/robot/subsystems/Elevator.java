package org.usfirst.frc.team1533.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_VictorSPX cimShifter1 = new WPI_VictorSPX(8);
	WPI_VictorSPX cimShifter2 = new WPI_VictorSPX(9);
	WPI_VictorSPX cimShifter3 = new WPI_VictorSPX(10);
	Joystick joy2;

    public void testing(Joystick joy2) {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	this.joy2 = joy2;
    	cimShifter1.set(ControlMode.PercentOutput, joy2.getY()*(-1));
    	cimShifter2.set(ControlMode.PercentOutput, joy2.getY()*(-1));
    	cimShifter3.set(ControlMode.PercentOutput, joy2.getY()*(-1));
    	
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

