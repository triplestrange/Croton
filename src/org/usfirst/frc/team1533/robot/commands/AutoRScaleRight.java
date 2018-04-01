package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRScaleRight extends CommandGroup {
	public AutoRScaleRight() {
		addSequential(new ElevatorProfile(10));
		addSequential(new ArcProfile(84, 92, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new ArcProfile(224, 90, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(24, 180, Constants.vCruise, 0, 10, 0, Constants.acc));
		addSequential(new ElevatorProfile(36));
		addSequential(new ElevatorProfile(38));
		addSequential(new ArcProfile(20, 0, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new Intake(1, 1));
	}
}