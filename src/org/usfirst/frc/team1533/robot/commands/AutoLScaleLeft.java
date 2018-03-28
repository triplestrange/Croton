package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLScaleLeft extends CommandGroup {
	public AutoLScaleLeft() {
		addParallel(new ElevatorProfile(36));
		addParallel(new ArcProfile(275, -95, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new Intake(0.5, 1));
	}
}