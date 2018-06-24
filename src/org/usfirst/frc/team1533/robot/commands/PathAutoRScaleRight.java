package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;

public class PathAutoRScaleRight extends CommandGroup{
	public PathAutoRScaleRight() {
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(5, 320, Pathfinder.d2r(90), 0)
				));
		addSequential(new ElevatorProfile(39));
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 4, Pathfinder.d2r(90), -90)
				));
		addSequential(new Intake(1, 1));
//		addParallel(new PathCommand(
//				new SwerveWaypoint(0, 0, Pathfinder.d2r(0), -90),
//				new SwerveWaypoint(36, 0, Pathfinder.d2r(0), -90)
//				));
//		addSequential(new ElevatorProfile(35));
}
}
