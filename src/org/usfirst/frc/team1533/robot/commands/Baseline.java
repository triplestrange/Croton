package org.usfirst.frc.team1533.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Tested on Svarog, functional
 */

public class Baseline extends CommandGroup {

	public Baseline() {
		
		// Orient Swerve Wheels to Forwards
		//addSequential(new DriveDiagonalTime(1, 0, 0));
		// Drive Forward for 2 seconds at 75% speed
		addParallel(new DriveForwardTime(2.75, .75));
		addSequential(new SwerveRotate(1, 180));
	}
}