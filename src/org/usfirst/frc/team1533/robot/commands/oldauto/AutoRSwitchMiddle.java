package org.usfirst.frc.team1533.robot.commands.oldauto;
import org.usfirst.frc.team1533.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1533.robot.commands.ArcProfile;
import org.usfirst.frc.team1533.robot.commands.ElevatorProfile;
import org.usfirst.frc.team1533.robot.commands.Intake;
import org.usfirst.frc.team1533.robot.commands.StraightProfile;
public class AutoRSwitchMiddle extends CommandGroup {

	public AutoRSwitchMiddle() {
		addParallel(new ElevatorProfile(10));
		addSequential(new ArcProfile(52.5, 28, Constants.vCruise+40, 0, 0, 36, Constants.acc));
		addParallel(new ElevatorProfile(15));
		addSequential(new ArcProfile(60, 25, Constants.vCruise+40, 0, 36, 0, Constants.acc));
		addSequential(new Intake(0.5, 1));
		addSequential(new StraightProfile(27, -160, Constants.vCruise, 0, 0, Constants.acc));
		addParallel(new ElevatorProfile(0));
		addSequential(new ArcProfile(0, 0, Constants.vCruise, -55, 0, 0, Constants.acc));
		addParallel(new StraightProfile(30, 0, Constants.vCruise, 0, 0, Constants.acc));
		addSequential(new Intake(3, -1));
		addSequential(new StraightProfile(36, 180, Constants.vCruise, 0, 0, Constants.acc));
		addSequential(new ArcProfile(0, 0, Constants.vCruise, 60, 0, 0, Constants.acc));
		addParallel(new ElevatorProfile(20));
		addSequential(new StraightProfile(30, 0, Constants.vCruise, 0, 0, Constants.acc));
		addParallel(new Intake(0.5, 1));
//		addSequential(new StraightProfile(60, 90, Constants.vCruise, 0, 0, Constants.acc));
		
	}
}