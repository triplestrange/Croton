package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoLScaleLeft2 extends CommandGroup{
	public PathAutoLScaleLeft2() {
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 280, Pathfinder.d2r(90), 45)
				));
		addSequential(new WaitCommand(1.5));
		addSequential(new ElevatorProfile(36));
		addSequential(new WaitCommand(1.5));
		addSequential(new Intake(1, 1));
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 60),
				new SwerveWaypoint(54, -75, Pathfinder.d2r(300), 175)
				));
		addSequential(new WaitCommand(.25));
		addSequential(new ElevatorProfile(0));
		addSequential(new Intake(0.75, -1));
		addParallel(new PathCommand(144,12,90,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(300), 180),
				new SwerveWaypoint(-36, 60, Pathfinder.d2r(90), 85)
				));
		addSequential(new WaitCommand(.25));
		addSequential(new ElevatorProfile(36));
		addSequential(new WaitCommand(.5));
		addSequential(new Intake(0.75, 1));
	}
}
