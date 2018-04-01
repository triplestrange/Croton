package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/*
@TODO Comments...
*/

public class TimedDriveDiagonalTime extends Command {
	double duration;
	double speed;
	double startTime;
	double angle;

	public TimedDriveDiagonalTime(double duration, double speed, double angle) {
		this.duration = duration;
		this.speed = speed;
		this.angle = angle;
	}

	public void initialize() {
		startTime = System.currentTimeMillis() / 1e3;
	}

	public void execute() {
		Robot.swerve.driveNormal(-speed * Math.cos(angle * Math.PI / 180), -speed * Math.sin(angle * Math.PI / 180), 0);
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}

	public void end() {
		Robot.swerve.driveNormal(0, 0, 0);
	}

}
