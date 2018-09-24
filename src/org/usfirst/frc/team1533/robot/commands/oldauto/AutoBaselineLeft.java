package org.usfirst.frc.team1533.robot.commands.oldauto;

import org.usfirst.frc.team1533.robot.Constants;
import org.usfirst.frc.team1533.robot.commands.ArcProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaselineLeft extends CommandGroup {

	public AutoBaselineLeft() {
		// Drive left past line
		addSequential(new TimeControl());
		addSequential(new ArcProfile(100, -90, Constants.vCruise, 0, 0, 36, Constants.acc));
	}
}