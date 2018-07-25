package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.SwerveWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import jaci.pathfinder.Pathfinder;

public class PathExperimentalAutoLScaleRight extends CommandGroup{
	public PathExperimentalAutoLScaleRight() {
		addSequential(new PathCommand(96, 35, 45, 180,
				new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(0, 194, Pathfinder.d2r(90), 0),
				new SwerveWaypoint(-36, 230, Pathfinder.d2r(180), 0),
				new SwerveWaypoint(-120, 225, Pathfinder.d2r(180), 0)
				));
		
		
//		addSequential(new PathCommand(72, 50, 45, 45,
//			new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
//			new SwerveWaypoint(0, 194, Pathfinder.d2r(90), -90),
//			new SwerveWaypoint(-36, 230, Pathfinder.d2r(180), -90),
//			new SwerveWaypoint(-72, 230, Pathfinder.d2r(180), -90),
//			new SwerveWaypoint(-134, 320, Pathfinder.d2r(110), -90)
////			new SwerveWaypoint(-196, 225, Pathfinder.d2r(180), 30),
////			new SwerveWaypoint(-222, 260, Pathfinder.d2r(90), 30)
//			));
//	addSequential(new ElevatorProfile(36));
//	addSequential(new ElevatorProfile(39));
//	addSequential(new PathCommand(
//			new SwerveWaypoint(0, 0, Pathfinder.d2r(180), -90),
//			new SwerveWaypoint(-24, 0, Pathfinder.d2r(180), -60)
//			));
//	addSequential(new WaitCommand(1));
//	addSequential(new Intake(1, .35));
////	addParallel(new PathCommand(
////			new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 30),
////			new SwerveWaypoint(0, -36, Pathfinder.d2r(270), 30)
////			));
////	addSequential(new ElevatorProfile(35));
}
}
