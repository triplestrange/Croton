package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Tested on Svarog, functional
 */

public class TimedDriveCommand extends Command {

	public TimedDriveCommand() {
		//Reserves subsystem(s)
		requires(Robot.swerve);
	}

	protected void initialize() {
	}

	protected void execute() {
		//execute() is called every 20ms
		
		//Gets the "X" axis position of the left joystick and asigns it to a variable
		double x = Robot.joy1.getX();
		
		//Gets the "Y" axis position of the left joystick and asigns it to a variable
		double y = -Robot.joy1.getY();
		
		//Gets the "X" axis position of the right joystick and asigns it to a variable
		double rotation = Robot.joy1.getZ();
		
		//If the Absolute Value of the joysticks postitions is less than .1, then the value is assigned to 0
		//This acounts for slight issues with joystick calibrations causing the resting positions to not equal 0 
		if (Math.abs(x) < 0.1)
			x = 0;
		if (Math.abs(y) < 0.1)
			y = 0;
		if (Math.abs(rotation) < 0.1)
			rotation = 0;
		
		//Calls the "driveNormal()" command in Swerve
		//@TODO Need to implement driveNormal as a Command
		Robot.swerve.driveNormal(x, y, rotation);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
