package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.MotionProfile;
import org.usfirst.frc.team1533.robot.Robot;
import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team1533.util.Vector2D;

import edu.wpi.first.wpilibj.command.Command;

public class ArcProfile extends Command {
	double distance;
	double speed;
	double direction;
	double turnAmount;
	double startV;
	double endV;
	double acc;
	MotionProfile[] moduleProfile = new MotionProfile[Robot.swerve.modules.length];
	double[] moduleAngle = new double[Robot.swerve.modules.length];
	double turnScalar = 215.2/212.2;
	
	public ArcProfile(double distance, double direction, double speed, double turnAmount, double startV, double endV, double acc) {
		this.direction = direction;
		this.distance = distance;
		this.speed = speed;
		this.turnAmount = turnAmount;
		this.startV = startV;
		this.endV = endV;
		this.acc = acc;
		SwerveDrive swerve = Robot.swerve;
		Vector2D[] moduleVect = new Vector2D[swerve.modules.length];
		double maxDist = 0;
		for(int i = 0; i < swerve.modules.length; i++) {
			moduleVect[i] = new Vector2D(distance*Math.sin(direction*Math.PI/180), distance*Math.cos(direction*Math.PI/180));	
			moduleVect[i].add(new Vector2D(swerve.modules[i].positionX, swerve.modules[i].positionY)
					.makePerpendicular().scale(turnScalar * turnAmount*Math.PI/180));
			maxDist = Math.max(maxDist, moduleVect[i].getMagnitude());
		}
		for(int i = 0; i < swerve.modules.length; i++) {
			moduleProfile[i] = new MotionProfile(0.0, maxDist, startV, endV, speed, acc);
			moduleProfile[i].setScalar(moduleVect[i].getMagnitude()/maxDist);
			moduleAngle[i] = moduleVect[i].getAngle();		
		}
		requires(Robot.swerve);
	}
	
	public void initialize() {
		
		for(int i = 0; i < Robot.swerve.modules.length; i++) {
			Robot.swerve.modules[i].runProfile(moduleAngle[i], moduleProfile[i]);
		}
	}
	
	public void execute() {
		for(int i = 0; i < Robot.swerve.modules.length; i++) {
			Robot.swerve.modules[i].swerveMP.update();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.swerve.modules[0].swerveMP.isFinished();
	}

	public void end() {
//		if(endV==0) {
			Robot.swerve.driveNormal(0, 0, 0);
//		}
	}
	
	public void cancel() {
		for(int i = 0; i < Robot.swerve.modules.length; i++) {
			Robot.swerve.modules[i].swerveMP.cancel();
		}
		Robot.swerve.driveNormal(0, 0, 0);
		super.cancel();
	}
}
