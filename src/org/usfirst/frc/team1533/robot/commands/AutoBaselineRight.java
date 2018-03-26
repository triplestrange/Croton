package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaselineRight extends CommandGroup {

	public AutoBaselineRight() {
		// Drive right past line
		addSequential(new TimeControl());
		addSequential(new ArcProfile(100, 90, Constants.vCruise, 0, 0, 36, Constants.acc));
	}
}