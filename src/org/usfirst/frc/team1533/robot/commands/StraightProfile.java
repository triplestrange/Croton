package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.MotionProfile;
import org.usfirst.frc.team1533.robot.Robot;
import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team1533.util.Vector2D;

import edu.wpi.first.wpilibj.command.Command;

public class StraightProfile extends Command {
	double distance;
	double speed;
	double direction;
	double startV;
	double endV;
	double acc;
	MotionProfile moduleProfile;
	
	public StraightProfile(double distance, double direction, double speed, double startV, double endV, double acc) {
		this.direction = direction;
		this.distance = distance;
		this.speed = speed;
		this.startV = startV;
		this.endV = endV;
		this.acc = acc;
		moduleProfile = new MotionProfile(0.0, distance, startV, endV, speed, acc);
		requires(Robot.swerve);
	}
	
	public void initialize() {
			Robot.swerve.runProfile(direction, moduleProfile);
	}
	
	public void execute() {
			Robot.swerve.swerveMP.update();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.swerve.swerveMP.isFinished();
	}

	public void end() {
//		if(endV==0) {
			Robot.swerve.driveNormal(0, 0, 0);
//		}
	}
	
	public void cancel() {
			Robot.swerve.swerveMP.cancel();
		Robot.swerve.driveNormal(0, 0, 0);
		super.cancel();
	}
}
