package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLScaleLeft extends CommandGroup {
	public AutoLScaleLeft() {
		addSequential(new ElevatorProfile(10));
		addSequential(new ArcProfile(84, -93, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new ArcProfile(224, -90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(24, 180, Constants.vCruise, 0, 36, 0, Constants.acc));
		addSequential(new ElevatorProfile(38));
		addSequential(new ArcProfile(20, 0, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new Intake(1, 1));
	}
}