package org.usfirst.frc.team1533.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftSwitch extends CommandGroup {

	public LeftSwitch() {
		
		//Sets Swerve wheels to forwards orientation

		addSequential(new DriveDiagonalTime(.2,.3,90));
		
		//Moves Swerve at an angle towards right Switch Fastly, and then slowly
		addSequential(new DriveDiagonalTime(1.5,-.8,125));
		addSequential(new SwerveRotate(0, 1));
		addSequential(new DriveDiagonalTime(1.1,-.3, 90));
		
		//Acts as a 3 second pause while slowly moving backwards
		addSequential(new DriveForwardTime(3,-.2));
		
		//"Slides" robot towards the Fence
		addSequential(new DriveDiagonalTime(1.7,-.6,165));
		//Moves Forward toward the Null Zone
		
		//Rotates Swerve to face opposite fence
		addSequential(new SwerveRotate(-0.5, 155));
		addSequential(new DriveDiagonalTime(1.7,.9,90));
		addSequential(new SwerveRotate(-0.5, 170));
		addSequential(new DriveDiagonalTime(1, -.52, 180));		
	}
}