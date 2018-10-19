package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;
import org.usfirst.frc.team1533.robot.commands.PathCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathAutoLSwitchL extends CommandGroup {

	public PathAutoLSwitchL() {
		addParallel(new PathCommand(96, 35, 45, 180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 150, Pathfinder.d2r(90), 90)
				));
		addSequential(new WaitCommand(0.5));
		addSequential(new ElevatorProfile(20));
	}
}