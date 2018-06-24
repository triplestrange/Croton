package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;

public class PathAutoLScaleRight extends CommandGroup {
	public PathAutoLScaleRight() {
		addSequential(new PathCommand(96, 35,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 194, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(-36, 230, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-150, 225, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-196, 225, Pathfinder.d2r(180), 30),
				new SwerveWaypoint(-222, 265, Pathfinder.d2r(90), 30)
				));
		//addSequential(new Intake(6, 0));
//		addSequential(new ElevatorProfile(36));
		addSequential(new ElevatorProfile(39));
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 30),
				new SwerveWaypoint(0, 24, Pathfinder.d2r(90), 30)
				));
		addSequential(new Intake(1, 1));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 30),
				new SwerveWaypoint(0, -36, Pathfinder.d2r(270), 30)
				));
		addSequential(new ElevatorProfile(35));
	}
	
}
