package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;

public class PathAutoRScaleRight extends CommandGroup{
	public PathAutoRScaleRight() {
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(6, 150, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(12, 324, Pathfinder.d2r(90), 0)
				));
		addSequential(new ElevatorProfile(39));
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 24, Pathfinder.d2r(90), -95)
				));
		addSequential(new Intake(1, 1));
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(0), 0),
				new SwerveWaypoint(24, 0, Pathfinder.d2r(0), 0)
				));
		addSequential(new ElevatorProfile(0));
}
}
