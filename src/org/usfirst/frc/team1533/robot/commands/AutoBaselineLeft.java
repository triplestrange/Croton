package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaselineLeft extends CommandGroup {

	public AutoBaselineLeft() {
		// Drive left past line
		addSequential(new TimeControl());
		addSequential(new ArcProfile(100, -90, Constants.vCruise, 0, 0, 36, Constants.acc));
	}
}