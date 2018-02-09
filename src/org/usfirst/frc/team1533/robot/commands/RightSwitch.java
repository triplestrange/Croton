package org.usfirst.frc.team1533.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/*
Under development 
*/
public class RightSwitch extends CommandGroup {

	public RightSwitch() {
		
		//Resets Gyro orientation
		addSequential(new GyroReset());
		
		//Sets Swerve wheels to forwards orientation
		addSequential(new DriveDiagonalTime(0,0,0));
		
		//Moves Swerve at an angle towards right Switch Fastly, and then slowly
		addSequential(new DriveDiagonalTime(1.7,-.75,65));
		addSequential(new DriveDiagonalTime(.7,-.25,65));
		
		//Acts as a 3 second pause while slowly moving backwards
		addSequential(new DriveForwardTime(3,-.2));
		
		//"Slides" robot towards the Fence
		addSequential(new DriveDiagonalTime(1.8,-.5,0));
		
		//Moves Forward toward the Null Zone
		addSequential(new DriveDiagonalTime(2,-.75,90));
		
		//Rotates Swerve to face opposite fence
		addSequential(new SwerveRotate(.45, 180));
		
		//Swerve Rotate is currently having issues, view the SwerveRotate Command for further details
	}
}