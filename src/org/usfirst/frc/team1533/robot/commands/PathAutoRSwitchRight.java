package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoRSwitchRight extends CommandGroup{
	public PathAutoRSwitchRight() {
		addParallel(new PathCommand(96,40,45,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(60), 0),
				new SwerveWaypoint(42, 118, Pathfinder.d2r(90), 0)
				));
		addSequential(new ElevatorProfile(15));
		addSequential(new WaitCommand(2.25));
		addSequential(new Intake(0.5,1));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 0),
				new SwerveWaypoint(-28, -48, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-56, -12, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(1.5));
		addSequential(new ElevatorProfile(0));
		addSequential(new Intake(2,-1));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(0), 0),
				new SwerveWaypoint(18,-12, Pathfinder.d2r(15), 0),
				new SwerveWaypoint(45, 60, Pathfinder.d2r(90), 0)
				));
		addSequential(new ElevatorProfile(15));
		addSequential(new WaitCommand(2));
		addSequential(new Intake(1,1));
}
}
