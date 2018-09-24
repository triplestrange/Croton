package org.usfirst.frc.team1533.robot.commands.oldauto;
import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1533.robot.commands.ArcProfile;
import org.usfirst.frc.team1533.robot.commands.ElevatorProfile;
import org.usfirst.frc.team1533.robot.commands.Intake;
import org.usfirst.frc.team1533.robot.commands.StraightProfile;
public class AutoRScaleLeft extends CommandGroup {
	public AutoRScaleLeft() {
		//addParallel(new ElevatorProfile(10));
		addSequential(new ArcProfile(200, -92.5, Constants.vCruise, 0, 0, 36, Constants.acc));
		addSequential(new ArcProfile(10, 180, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(200, 0, Constants.vCruise, 0, 36, 36, Constants.acc));
		addSequential(new ArcProfile(24, 0, Constants.vCruise, -90, 36, 0, Constants.acc));
		//addSequential(new ElevatorProfile(36));
	}
}