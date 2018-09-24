package org.usfirst.frc.team1533.robot.commands.oldauto;
import org.usfirst.frc.team1533.robot.Robot;
import org.usfirst.frc.team1533.robot.subsystems.Gyro;

import edu.wpi.first.wpilibj.command.Command;

public class TimedSwerveRotate extends Command{
	double angle;
	double speed;
	double startTime;
	
	public TimedSwerveRotate(double speed, double angle) {
	this.angle = angle;
	this.speed = speed;
}

public void initialize() {
	startTime = System.currentTimeMillis() / 1e3;
}

public void execute() {
	Robot.swerve.driveNormal(0,0,speed);
}

protected boolean isFinished() {
    return Math.abs(Gyro.getAngle()) > angle;
}

public void end() {
	Robot.swerve.driveNormal(0, 0, 0);
}
}
