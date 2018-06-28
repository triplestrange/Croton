package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Robot;
import org.usfirst.frc.team1533.robot.SwerveTrajectory;
import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;

public class PathCommand extends Command{
	double speed = 96.0;
	double acc = 40.0;
	double rotVel = 45;
	SwerveWaypoint[] waypoints;
	SwerveTrajectory traj;
	
public PathCommand(SwerveWaypoint... waypoints) {
	this.waypoints = waypoints;
	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.05, speed, acc, 100.0);
	traj = SwerveTrajectory.generate(config, waypoints, rotVel);
}

public PathCommand(double speed, double acc, double rotVel, SwerveWaypoint... waypoints) {
	this.speed = speed;
	this.acc = acc;
	this.rotVel = rotVel;
	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.05, speed, acc, 100.0);
	traj = SwerveTrajectory.generate(config, waypoints, rotVel);
}
public void initialize() {
	Robot.path.reset();
	Robot.follower.startTrajectory(traj);
}

public void execute() {
	Robot.follower.update();
}

@Override
protected boolean isFinished() {
	return Robot.follower.isFinished();
}

public void end() {
	Robot.swerve.driveNormal(0, 0, 0);
}

public void cancel() {
	Robot.follower.cancel();
	Robot.swerve.driveNormal(0, 0, 0);
	super.cancel();
}
}
