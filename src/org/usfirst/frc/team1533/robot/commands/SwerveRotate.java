package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwerveRotate extends Command {
	// Creates Variables used in this Command
	double speed;
	double duration;
	double startTime;

	public SwerveRotate(double duration, double speed) {
		// Reserves the Subsystem swerve
		requires(Robot.swerve);

		// Asigns the inputs to variables
		this.duration = duration;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	public void initialize() {
		// Stores the current time in
		startTime = System.currentTimeMillis() / 1e3;
	}

	public void execute() {
		// Calls the driveNormal command in the SwerveDrive Subsystem
		// Currently, the Robot will rotate exactly how you tol it to, and then turns
		// back
		// @TODO Find out what is wrong
		Robot.swerve.driveNormal(0, 0, speed);
	}

	protected boolean isFinished() {
		// Calls the isFinished command when duration is less than or equal to how long
		// the command has been running
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}

	protected void end() {
	}
}