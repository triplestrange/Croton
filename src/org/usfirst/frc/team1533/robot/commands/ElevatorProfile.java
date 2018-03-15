package org.usfirst.frc.team1533.robot.commands;

import java.util.Arrays;

import org.usfirst.frc.team1533.robot.MotionProfile;
import org.usfirst.frc.team1533.robot.Robot;
import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team1533.util.Vector2D;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorProfile extends Command {
	double endPoint;

	

	public ElevatorProfile(double endPoint) {
		requires(Robot.elevator);
		this.endPoint = endPoint;
	}
	
	public void initialize() {
		MotionProfile elevMP = new MotionProfile(Robot.elevator.encoder.getDistance(), endPoint, Robot.elevator.encoder.getRate(), 0, Robot.elevator.vCruise, Robot.elevator.acc);
		Robot.elevator.elevMP.startProfile(elevMP);
	}
	
	public void execute() {
		Robot.elevator.elevMP.update();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.elevator.elevMP.isFinished();
	}

	public void end() {
		Robot.elevator.pidWrite(0);
	}
	
	public void cancel() {
		Robot.elevator.elevMP.cancel();
		Robot.elevator.pidWrite(0);
		super.cancel();
	}
}
