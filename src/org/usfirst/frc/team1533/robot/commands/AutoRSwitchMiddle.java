package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
Under development 
*/
public class AutoRSwitchMiddle extends CommandGroup {

	public AutoRSwitchMiddle() {

		addSequential(new TimeControl());
		addSequential(new ElevatorProfile(10));
		addSequential(new ArcProfile(52.5, 28, Constants.vCruise, 0, 0, 36, Constants.acc));
		addParallel(new ElevatorProfile(15));
		addSequential(new ArcProfile(60, 25, Constants.vCruise, 0, 36, 0, Constants.acc));
		addSequential(new Intake(0.5, 1));
		
	}
}