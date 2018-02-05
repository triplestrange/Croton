package org.usfirst.frc.team1533.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Tested on Svarog, functional
 */

public class Baseline extends CommandGroup {

	public Baseline() {
		// Drive Forward for 2 seconds at 75% speed
		addSequential(new DriveForwardTime(2, .75));
	}
}