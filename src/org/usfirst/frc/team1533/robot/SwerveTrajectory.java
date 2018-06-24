package org.usfirst.frc.team1533.robot;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

public class SwerveTrajectory extends Trajectory {
	double[] waypointTime;
	SwerveWaypoint[] waypoints;
	double angularVelocity;
	
	public static SwerveTrajectory generate(Trajectory.Config config, SwerveWaypoint[] waypoints, double angularVelocity) {
		SwerveTrajectory trajectory;
		trajectory = new SwerveTrajectory(Pathfinder.generate(waypoints, config));
		trajectory.waypoints = waypoints;
		trajectory.angularVelocity = angularVelocity;
		
		for (int i = 0; i < waypoints.length; i++) {
			double shortdist = Math.hypot(trajectory.segments[0].x-waypoints[i].x, trajectory.segments[0].y-waypoints[i].y);
			
			for (int j =0; j < trajectory.segments.length; j++) {
				double dist = Math.hypot(trajectory.segments[j].x-waypoints[i].x, trajectory.segments[j].y-waypoints[i].y);
				if (dist <= shortdist) {
				shortdist = dist;
				trajectory.waypointTime[i] = j*trajectory.segments[j].dt;
				}
			}
		}
		
		return trajectory;
	}
	public SwerveTrajectory(Trajectory trajectory) {
		super(trajectory.segments);
		waypointTime = new double[trajectory.segments.length];
		// TODO Auto-generated constructor stub
	}
}