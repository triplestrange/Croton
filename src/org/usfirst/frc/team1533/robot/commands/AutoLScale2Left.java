package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLScale2Left extends CommandGroup {
	public AutoLScale2Left() {
		addParallel(new ElevatorProfile(10));
		addSequential(new StraightProfile(230, -90, Constants.vCruise+40, 0, 0, Constants.acc));
		addSequential(new ElevatorProfile(38));
		addSequential(new ArcProfile(30, 0, Constants.vCruise, -45, 0, 0, Constants.acc));
		addSequential(new Intake(1, 1));
		addSequential(new ArcProfile(40, 90, Constants.vCruise, 105, 0, 0, Constants.acc));
		addSequential(new ElevatorProfile(0));
		addParallel(new ArcProfile(15, 0, Constants.vCruise, 0, 0, 0, Constants.acc));
		addSequential(new Intake(2, -1));
	}
}