package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/*
@TODO Comments... 
*/

public class TimedDriveForwardTime extends Command {
	double duration;
	double speed;
	double startTime;
	
	public TimedDriveForwardTime(double duration, double speed) {
		this.duration = duration;
		this.speed = speed;
	}
	
	public void initialize() {
		startTime = System.currentTimeMillis() / 1e3;
	}
	
	public void execute() {
		Robot.swerve.driveNormal(0, speed, 0);
	}
	
	protected boolean isFinished() {
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}
	
	public void end() {
		Robot.swerve.driveNormal(0, 0, 0);
	}

}