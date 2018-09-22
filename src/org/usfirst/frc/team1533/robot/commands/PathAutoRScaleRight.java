package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoRScaleRight extends CommandGroup{
	public PathAutoRScaleRight() {
		addParallel(new ElevatorProfile(20));
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(12, 300, Pathfinder.d2r(90), 0)
				));
		addParallel(new ElevatorProfile(38));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-12, 0, Pathfinder.d2r(180), -90)
				));
		addSequential(new WaitCommand(2.5));
		addSequential(new Intake(1, 1));
		addSequential(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(180), -90),
				new SwerveWaypoint(36, 0, Pathfinder.d2r(180), -90)
				));
		addSequential(new ElevatorProfile(0));
		}
	
}
