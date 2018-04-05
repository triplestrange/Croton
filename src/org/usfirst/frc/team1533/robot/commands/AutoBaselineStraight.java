package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaselineStraight extends CommandGroup {

	public AutoBaselineStraight() {
		// Drive left past line
		addSequential(new StraightProfile(250, 0, Constants.vCruise, 0, 0, Constants.acc));
	}
}