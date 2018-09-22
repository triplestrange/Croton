package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoLScaleRight extends CommandGroup {
	public PathAutoLScaleRight() {
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 194, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(-36, 229, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-150, 229, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-196, 229, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-260, 260, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(4.5));
		addSequential(new ElevatorProfile(37));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 24, Pathfinder.d2r(90), 60)
				));
		addSequential(new WaitCommand(1));
		addSequential(new Intake(0.75, 1));
		addParallel(new PathCommand(48, 96, 180, 180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 60),
				new SwerveWaypoint(44, -60.75, Pathfinder.d2r(300), 180),
				new SwerveWaypoint(56, -81, Pathfinder.d2r(270), 180)
				));
		addSequential(new WaitCommand(0.25));
		addSequential(new ElevatorProfile(0));
		addSequential(new Intake(1, -1));
		addParallel(new PathCommand(36, 96, 140, 180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(310), 135),
				new SwerveWaypoint(-24, 66, Pathfinder.d2r(90), 375)
				));
		addSequential(new WaitCommand(0.25));
		addSequential(new ElevatorProfile(38));
		addSequential(new Intake(0.75, 1));
	}
	
}
