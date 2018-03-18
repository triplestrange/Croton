package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Tested on Svarog, functional
 */

public class AutoBaseline extends CommandGroup {

	public AutoBaseline() {
		// Drive Forward past line
		addSequential(new ArcProfile(100, 0, Constants.vCruise, 0, 0, 36, Constants.acc));
	}
}