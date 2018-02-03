package org.usfirst.frc.team1533.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitch extends CommandGroup {

	public MiddleSwitch() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		
		//Sets Swerve wheels to forwards orientation
		addSequential(new DriveDiagonalTime(0, 0, 0));
		//Moves Swerve at an angle towards right Switch
		addSequential(new DriveDiagonalTime(1.72, -.75, 65));
		addSequential(new DriveDiagonalTime(3, 0, 0));
		addSequential(new DriveDiagonalTime(.1, .1, 90));
		addSequential(new DriveDiagonalTime(1.6, -.5, 0));
		addSequential(new DriveDiagonalTime(1.9, -.75, 90));
		//addSequential(new SwerveRotate(-1, .47));
		//addSequential(new DriveForwardTime(1,.5));
		//addSequential(new SwerveRotate(-1, .47));
		// addSequential(new DriveDiagonalTime(.5, -.75, 0));

		// To run multiple commands at the same time, use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member would
		// require.
		// e.g. if Command1 requires chassis, and Command2 requires arm, a CommandGroup
		// containing them would require both the chassis and the arm.
	}
}