package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoRScaleRight2 extends CommandGroup{
	public PathAutoRScaleRight2() {
		addParallel(new PathCommand(120, 96, 180, 180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 270, Pathfinder.d2r(90), -55)
				));
		addSequential(new WaitCommand(1.5));
		addSequential(new ElevatorProfile(36));
		addSequential(new WaitCommand(1.5));
		addSequential(new Intake(1, 1));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(270), -57.5),
				new SwerveWaypoint(-44, -76, Pathfinder.d2r(250), -180)
				));
		addSequential(new WaitCommand(.25));
		addSequential(new ElevatorProfile(0));
		addSequential(new Intake(1, -1));
		addParallel(new PathCommand(144,96,67.5,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(60), -180),
				new SwerveWaypoint(20, 52, Pathfinder.d2r(90), -30)
				));
		addSequential(new WaitCommand(.5));
		addSequential(new ElevatorProfile(36));
		addSequential(new WaitCommand(.5));
		addSequential(new Intake(0.75, 1));
		addParallel(new PathCommand(144,96,180,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(270), -25),
				new SwerveWaypoint(-70, -70, Pathfinder.d2r(290), -180)
				));
		addSequential(new WaitCommand(.25));
		addSequential(new ElevatorProfile(0));
		addSequential(new Intake(1, -1));
}
}
