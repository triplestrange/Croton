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
	SwerveTrajectory currentTrajectory;
	long startTime;
	double kP = 0.0007;
	double kD = 0.00005;
	
	public PathFollower() {
//		this.kF = kF;
//		this.kP = kP;
//		this.kI = kI;
//		this.kD = kD;
//		this.encoder = encoder;
//		this.output = output;
		
		this.xProfile = new ProfileFollower(.008, 0.005, 0, 0, 0, new PIDSourceAdapter(() -> Robot.path.currentX), (x) -> xPower = x);
		this.yProfile = new ProfileFollower(.008, 0.005, 0, 0, 0, new PIDSourceAdapter(() -> Robot.path.currentY), (y) -> yPower = y);
		this.zProfile = new ProfileFollower(.003, 0, 0.01, 0, 0.0025, new PIDSourceAdapter(() -> Robot.path.currentZ), (z) -> zPower = z);
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
	
	public void startTrajectory(SwerveTrajectory t) {
		currentTrajectory = t;
		startTime = System.nanoTime();
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
		zProfile.startProfile(new MotionProfile() {
			int currentWaypoint = 1;
			double lastTarget = currentTrajectory.waypoints[0].rotation;
			public double currentP(double t) {
				double angle;
				double target = currentTrajectory.waypoints[currentWaypoint].rotation;
				double lastTime = currentTrajectory.waypointTime[currentWaypoint-1];
				if (target > lastTarget) {
					angle = lastTarget + (t-lastTime) * currentTrajectory.angularVelocity;
					if (angle > target) angle = target;
				} else if (target < lastTarget) {
					angle = lastTarget - (t-lastTime) * currentTrajectory.angularVelocity;
					if (angle < target) angle = target;
				} else angle = target;
				if (t >= currentTrajectory.waypointTime[currentWaypoint] && currentWaypoint < currentTrajectory.waypoints.length-1) {
					currentWaypoint++;
					lastTarget = angle;
				}
				SmartDashboard.putNumber("zProfileTargetP", angle);
				return angle;
			}
			public double currentV(double t) {
				return currentTrajectory.angularVelocity * Math.signum(currentTrajectory.waypoints[currentWaypoint].rotation - currentP(t));
			}
			public double totalTime() {
				return PathFollower.this.totalTime(currentTrajectory);
			}
			public double currentA(double t) {
				return 0;
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
			double currentTime = (System.nanoTime()-startTime)/1e9;
			xProfile.kP = yProfile.kP = kP * currentSegment(currentTime).velocity;
			xProfile.kD = yProfile.kD = kD * currentSegment(currentTime).velocity;
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
