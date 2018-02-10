package org.usfirst.frc.team1533.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/*
Under development 
*/
public class RightSwitch extends CommandGroup {

	public RightSwitch() {
		
		//Sets Swerve wheels to forwards orientation
		addSequential(new DriveDiagonalTime(0,0,0));
		
		//Moves Swerve at an angle towards right Switch Fastly, and then slowly
		addSequential(new DriveDiagonalTime(1.6,-.75,63));
		addSequential(new DriveDiagonalTime(1,-.25,63));
		
		//Acts as a 3 second pause while slowly moving backwards
		addSequential(new DriveForwardTime(3,-.2));
		
		//"Slides" robot towards the Fence
		addSequential(new DriveDiagonalTime(1.8,-.5,0));
		
		//Moves Forward toward the Null Zone
		addSequential(new DriveDiagonalTime(1.8,-.77,90));
		
		//Rotates Swerve to face opposite fence
		addSequential(new SwerveRotate(-0.5, 70));
		
		addSequential(new DriveDiagonalTime(0.8, -.5, 90));
		
		addSequential(new SwerveRotate(-0.5, 160));
		
	}
}