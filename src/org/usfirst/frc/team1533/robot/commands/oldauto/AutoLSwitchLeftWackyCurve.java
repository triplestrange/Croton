package org.usfirst.frc.team1533.robot.commands.oldauto;

import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1533.robot.commands.ArcProfile;
import org.usfirst.frc.team1533.robot.commands.ElevatorProfile;
import org.usfirst.frc.team1533.robot.commands.Intake;
import org.usfirst.frc.team1533.robot.commands.StraightProfile;
public class AutoLSwitchLeftWackyCurve extends CommandGroup {
	public AutoLSwitchLeftWackyCurve() {
		addSequential(new TimeControl());
		addParallel(new ElevatorProfile(15));
		addSequential(new ArcProfile(130, -82.5, Constants.vCruise, 0, 0, 36, Constants.acc));
		addParallel(new ArcProfile(48, -90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new Intake(0.5, 1));
		addSequential(new ArcProfile(80, -120, Constants.vCruise, 50, 36, 0, Constants.acc));
		addParallel(new ElevatorProfile(0));
		addParallel(new ArcProfile(24, 0, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new Intake(2, -1));
		addSequential(new ElevatorProfile(15));
	}
}