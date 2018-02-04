package org.usfirst.frc.team1533.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitch extends CommandGroup {

	public MiddleSwitch() {
		
		//Sets Swerve wheels to forwards orientation
		addSequential(new DriveDiagonalTime(0,0,0));
		//Moves Swerve at an angle towards right Switch
		addSequential(new DriveDiagonalTime(1.72,-.75,65));
		//Acts as a 3 second pause while reorinetating the swerve wheels to 0 degrees
		addSequential(new DriveDiagonalTime(3,0,0));
		//Reverses Robot towards Driver Station
		addSequential(new DriveDiagonalTime(.3,.1,90));
		//"Slides" robot towards the Fence
		addSequential(new DriveDiagonalTime(1.6,-.5,0));
		//Moves Forward toward the Null Zone
		addSequential(new DriveDiagonalTime(1.9,-.75,90));
		//Rotates Swerve to face opposite fence
		addSequential(new SwerveRotate(-1,.47));
		//Moves Swerve towards mid-field
		addSequential(new DriveDiagonalTime(1,-.5,0));
		//Rotates Swerve to face a Power Cube
		addSequential(new SwerveRotate(-1,.47));
		//Moves Robot towards Power Cube
		 addSequential(new DriveDiagonalTime(.5,-.75,0));
	}
}