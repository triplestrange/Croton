package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLScaleLeft extends CommandGroup {
	public AutoLScaleLeft() {
//		addParallel(new ElevatorProfile(10));
		addSequential(new ArcProfile(84.21, -90, Constants.vCruise, 0, 0, 36, Constants.acc));
//		addParallel(new ElevatorProfile(36));
		addSequential(new ArcProfile(200, -90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(30, 180, Constants.vCruise, 0, 36, 0, Constants.acc));
		addSequential(new ArcProfile(24, 0, Constants.vCruise, 0, 0, 36, Constants.acc));
//		addParallel(new Intake(0.5, 1));
		addSequential(new ArcProfile(65, 45, Constants.vCruise, 60, 36, 0, Constants.acc));
	}
}