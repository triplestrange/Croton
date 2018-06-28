package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoRScaleRight2 extends CommandGroup{
	public PathAutoRScaleRight2() {
		addParallel(new PathCommand(84, 40, 45,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 200, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(-36, 275, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(3));
		addSequential(new ElevatorProfile(39));
		addSequential(new WaitCommand(.5));
		addSequential(new Intake(1, 0.5));
		addParallel(new PathCommand(18, 40, 90,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, -60, Pathfinder.d2r(90), 180)
				));
		addSequential(new WaitCommand(.25));
		addSequential(new ElevatorProfile(0));
		addSequential(new Intake(2, -1));
		addParallel(new PathCommand(18, 40, 90,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 180),
				new SwerveWaypoint(0, 60, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(.25));
		addSequential(new ElevatorProfile(39));
		addSequential(new WaitCommand(.5));
		addSequential(new Intake(1, 0.5));
		addSequential(new ElevatorProfile(35));
//		addSequential(new PathCommand(
//				new SwerveWaypoint(0, 0, Pathfinder.d2r(0), 0),
//				new SwerveWaypoint(24, 0, Pathfinder.d2r(0), 0)
//				));
//		addSequential(new ElevatorProfile(0));
}
}