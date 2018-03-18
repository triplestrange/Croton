package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLSwitchRight extends CommandGroup{
	public AutoLSwitchRight() {
		addParallel(new ElevatorProfile(15));
		addSequential(new ArcProfile(130, 85, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new ArcProfile(48, 90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(48, 90, 24, -75, 36, 0, Constants.acc));
		addSequential(new ElevatorProfile(0));
		//above is tested and good, below is untested
		addSequential(new ArcProfile(12, 0, Constants.vCruise, 0, 0, 0, Constants.acc));
		addSequential(new ArcProfile(24, 180, Constants.vCruise, 0, 0, 0, Constants.acc));
	}
}
