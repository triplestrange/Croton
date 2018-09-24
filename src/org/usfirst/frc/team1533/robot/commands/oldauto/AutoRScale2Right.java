package org.usfirst.frc.team1533.robot.commands.oldauto;
import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1533.robot.commands.ArcProfile;
import org.usfirst.frc.team1533.robot.commands.ElevatorProfile;
import org.usfirst.frc.team1533.robot.commands.Intake;
import org.usfirst.frc.team1533.robot.commands.StraightProfile;
public class AutoRScale2Right extends CommandGroup {
	public AutoRScale2Right() {
		addParallel(new ElevatorProfile(10));
		addSequential(new StraightProfile(240, 90, Constants.vCruise+40, 0, 0, Constants.acc));
		addSequential(new ElevatorProfile(38));
		addSequential(new ArcProfile(30, 0, Constants.vCruise, 45, 0, 0, Constants.acc));
		addSequential(new Intake(1, 1));
		addSequential(new ArcProfile(80, -90, Constants.vCruise, -90, 0, 0, Constants.acc));
		addSequential(new ElevatorProfile(0));
		addParallel(new ArcProfile(15, 0, Constants.vCruise, 0, 0, 0, Constants.acc));
		addSequential(new Intake(2, -1));
	}
}