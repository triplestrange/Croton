package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLSwitchMiddle extends CommandGroup {

	public AutoLSwitchMiddle() {

		addSequential(new TimeControl());
		addSequential(new ElevatorProfile(10));
		addSequential(new ArcProfile(52.5, -40, Constants.vCruise, 0, 0, 36, Constants.acc));
		addParallel(new ElevatorProfile(15));
		addSequential(new ArcProfile(70, -35, Constants.vCruise, 0, 36, 0, Constants.acc));
		addSequential(new Intake(0.5, 1));
	}
}