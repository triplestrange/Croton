package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoRSwitchRight extends CommandGroup{
	public PathAutoRSwitchRight() {
		addParallel(new PathCommand(
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(48, 108, Pathfinder.d2r(90), 0)
				));
		addSequential(new ElevatorProfile(15));
		addSequential(new WaitCommand(2));
		addSequential(new Intake(0.5,1));
		addParallel(new PathCommand(48,36,180,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 0),
				new SwerveWaypoint(-28, -52, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-57, -18, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(0.5));
		addSequential(new ElevatorProfile(0));
		addSequential(new WaitCommand(1));
		addSequential(new Intake(1,-1));
		addParallel(new PathCommand(48,36,180,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(0), 0),
				new SwerveWaypoint(18,-12, Pathfinder.d2r(0), 0),
				new SwerveWaypoint(57, 60, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(0.5));
		addSequential(new ElevatorProfile(15));
		addSequential(new WaitCommand(1.25));
		addSequential(new Intake(0.5,1));
		addParallel(new PathCommand(36,24,180,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(210), 0),
				new SwerveWaypoint(-24, -30, Pathfinder.d2r(180), -45),
				new SwerveWaypoint(-48, 0, Pathfinder.d2r(120), -45)
				));
		addSequential(new WaitCommand(0.25));
		addSequential(new ElevatorProfile(5.5));
		addParallel(new Intake(3,-1));
		addSequential(new WaitCommand(1.5));
		addParallel(new PathCommand(48,36,180,180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(0), -15),
				new SwerveWaypoint(24,-6, Pathfinder.d2r(0), -15),
				new SwerveWaypoint(40, 24, Pathfinder.d2r(90), 0)
				));
		addSequential(new WaitCommand(1));
		addSequential(new ElevatorProfile(15));
		addSequential(new WaitCommand(0.5));
		addSequential(new Intake(0.5,1));
	}
}
