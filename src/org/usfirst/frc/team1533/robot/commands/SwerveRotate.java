package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwerveRotate extends Command {
	double speed;
	double duration;
	double startTime;

	public SwerveRotate(double speed, double duration) {
		this.duration = duration;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	public void initialize() {
		startTime = System.currentTimeMillis() / 1e3;
	}

	public void execute() {
		Robot.swerve.driveNormal(0, 0, speed);
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}

	protected void end() {
		Robot.swerve.driveNormal(0, 0, 0);
	}
}