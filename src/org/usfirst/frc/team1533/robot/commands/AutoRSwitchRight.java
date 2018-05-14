package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRSwitchRight extends CommandGroup {

	public AutoRSwitchRight() {
		addParallel(new ElevatorProfile(10));
		addSequential(new ArcProfile(100, 85, Constants.vCruise, 0, 0, 36, Constants.acc));
		addParallel(new ElevatorProfile(20));
		addParallel(new ArcProfile(70, 90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new Intake(0.5, 1));
	}
}