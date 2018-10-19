package org.usfirst.frc.team1533.robot.commands.oldauto;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;
import org.usfirst.frc.team1533.robot.commands.PathCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;

public class AutoBaselineStraight extends CommandGroup {

	public AutoBaselineStraight() {
		addSequential(new PathCommand(96, 35, 45, 180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 150, Pathfinder.d2r(90), 00)
				));
	}
}