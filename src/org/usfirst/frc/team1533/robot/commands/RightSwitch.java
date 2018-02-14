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
		addSequential(new DriveDiagonalTime(1.5,-.75,63));
		addSequential(new DriveDiagonalTime(1.2,-.25,63));
		
		//Acts as a 3 second pause while slowly moving backwards
		addSequential(new DriveForwardTime(3,-.2));
		
		//"Slides" robot towards the Fence
		addSequential(new DriveDiagonalTime(1.7,-.55,0));
		//Moves Forward toward the Null Zone
		
		//Rotates Swerve to face opposite fence
		addSequential(new SwerveRotate(-0.5, 170));
		addSequential(new DriveDiagonalTime(1.8,.72,90));
		
		addSequential(new DriveDiagonalTime(1, -.51, 0));
		
		//addSequential(new SwerveRotate(-0.5, 160));
		
	}
}