package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDiagonalTime extends Command {
	double duration;
	double speed;
	double startTime;
	double angle;
	double sign;
	public DriveDiagonalTime(double duration, double speed, double angle) {
		this.duration = duration;
		this.speed = speed;
		this.angle = angle;
	}
	
	public void initialize() {
		startTime = System.currentTimeMillis() / 1e3;
		sign = DriverStation.getInstance().getAlliance().equals(Alliance.Red) ? 1 : -1;
	}
	
	public void execute() {
		Robot.swerve.driveNormal(-speed * Math.cos(angle*Math.PI/180)*sign, -speed * Math.sin(angle*Math.PI/180), -Robot.gyro2.getAngle() * 0.01);
	}
	
	protected boolean isFinished() {
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}
	
	public void end() {
		Robot.swerve.driveNormal(0, 0, 0);
	}

}
