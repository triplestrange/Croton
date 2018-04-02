package org.usfirst.frc.team1533.robot.commands;
import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRScale2Right extends CommandGroup {
	public AutoRScale2Right() {
//		addSequential(new ElevatorProfile(10));
		addSequential(new ArcProfile(250, 90, Constants.vCruise+40, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(24, 80, Constants.vCruise+40, 45, 18, 0, Constants.acc));
		addSequential(new Intake(2, 0));
		addSequential(new ArcProfile(48, -80, Constants.vCruise+40, -90, 18, 0, Constants.acc));
//		addSequential(new ElevatorProfile(36));
//		addSequential(new ElevatorProfile(38));
//		addSequential(new ArcProfile(20, 0, Constants.vCruise+40, 0, 0, 36, Constants.acc));
//		addSequential(new Intake(1, 1));
	}
}