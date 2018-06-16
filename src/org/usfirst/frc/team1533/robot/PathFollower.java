package org.usfirst.frc.team1533.robot;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team1533.util.PIDSourceAdapter;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

public class PathFollower {
//	double kF, kP, kI, kD;
//	PIDSource encoder;
//	PIDOutput output;
//	double startTime;
//	MotionProfile currentProfile;
//	double error;
//	double prevError;
//	double prevTime;
	ProfileFollower xProfile;
	ProfileFollower yProfile;
	ProfileFollower zProfile;
	double xPower, yPower, zPower;
	Trajectory currentTrajectory;
	
	public PathFollower() {
//		this.kF = kF;
//		this.kP = kP;
//		this.kI = kI;
//		this.kD = kD;
//		this.encoder = encoder;
//		this.output = output;
		
		this.xProfile = new ProfileFollower(.008, 0.005, 0.1, 0, 0.005, new PIDSourceAdapter(() -> Robot.path.currentX), (x) -> xPower = x);
		this.yProfile = new ProfileFollower(.008, 0.005, 0.1, 0, 0.005, new PIDSourceAdapter(() -> Robot.path.currentY), (y) -> yPower = y);
		this.zProfile = new ProfileFollower(0, 0, 0, 0, 0, new PIDSourceAdapter(() -> Robot.path.currentZ), (z) -> zPower = z);
	}
	
	private double totalTime(Trajectory t) {
		return t.get(0).dt * (t.length()-1);
	}
	
	public Trajectory.Segment currentSegment(double time) {
		if (currentTrajectory == null) return null;
		try {
			return currentTrajectory.get((int) Math.round(time / totalTime(currentTrajectory) * currentTrajectory.length()));
		} catch (IndexOutOfBoundsException e) {
			return currentTrajectory.get(currentTrajectory.length()-1);
		}
	}
	
	public void startTrajectory(Trajectory t) {
		currentTrajectory = t;
		xProfile.startProfile(new MotionProfile() {
			public double currentP(double t) {
				return currentSegment(t).x;
			}
			public double currentV(double t) {
				Trajectory.Segment cs = currentSegment(t);
				return cs.velocity * Math.cos(cs.heading);
			}
			public double totalTime() {
				return PathFollower.this.totalTime(currentTrajectory);
			}
			public double currentA(double t) {
				Trajectory.Segment cs = currentSegment(t);
				return cs.acceleration * Math.cos(cs.heading);
			}
		});
		yProfile.startProfile(new MotionProfile() {
			public double currentP(double t) {
				return currentSegment(t).y;
			}
			public double currentV(double t) {
				Trajectory.Segment cs = currentSegment(t);
				return cs.velocity * Math.sin(cs.heading);
			}
			public double totalTime() {
				return PathFollower.this.totalTime(currentTrajectory);
			}
			public double currentA(double t) {
				Trajectory.Segment cs = currentSegment(t);
				return cs.acceleration * Math.sin(cs.heading);
			}
		});
	}
	
//	public void startProfile(MotionProfile mP){
//		prevError=0;
//		startTime = System.currentTimeMillis()/1000.0;
//		prevTime = startTime;
//		currentProfile = mP;
//		
//		
//	}
	
	public void update() {
		if(isFinished()) {
			Robot.swerve.driveField(0, 0, 0);
			return;
		}
//		else {
//		double t = System.currentTimeMillis()/1000.0-startTime;
//		double p = currentProfile.currentP(t);
//		double v = currentProfile.currentV(t);
//		error = p-encoder.pidGet();
//		if(t==prevTime) {
//			prevTime=t-.001;
//		}
//		output.pidWrite(kF*v+kP*(error)+kD*(error-prevError)/(t-prevTime));
//		prevError = error;
//		prevTime = t;
//		
//		SmartDashboard.putNumber("Motion Profile P", p);
//		SmartDashboard.putNumber("Motion Profile V", v);
//		}
		else {
			xProfile.update();
			yProfile.update();
			zProfile.update();
			Robot.swerve.driveField(xPower, yPower, zPower);
		}
	}
	
	public void cancel() {
		currentTrajectory = null;
		xProfile.cancel();
		yProfile.cancel();
		zProfile.cancel();
	}
	
	public boolean isFinished() {
		if(currentTrajectory == null) {
			return true;
		}
		else if(yProfile.isFinished() && xProfile.isFinished() && zProfile.isFinished()){
		return true;
		}
		else {
			return false;
		}
	}
}
