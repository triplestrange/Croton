package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLSwitchLeft extends CommandGroup {
	
	public AutoLSwitchLeft() {
		addParallel(new ElevatorProfile(10));
		addSequential(new ArcProfile(130, -83, Constants.vCruise, 0, 0, 36, Constants.acc));
		addParallel(new ElevatorProfile(15));
		addParallel(new ArcProfile(48, -90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new Intake(0.5, 1));
	}
}