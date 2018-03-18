package org.usfirst.frc.team1533.robot;
import org.usfirst.frc.team1533.robot.commands.*;
import org.usfirst.frc.team1533.robot.subsystems.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends IterativeRobot {
	// Defines all of the Robot subsytems
	public static Gyro gyro;
	public static Joystick joy1, joy2;
	public static SwerveDrive swerve;
	public static Elevator elevator;
	public static CubeMech cubemech;
	// Defines autonomous selection tools
	Command LLCommand, RRCommand, LRCommand, RLCommand;
	SendableChooser<Command> LLChooser, RRChooser, LRChooser, RLChooser;
	
	public void robotInit() {
		// Initializes Swerve Drive, Joysticks, Gyro, Elevator, and Cube Mechanism.
		gyro = new Gyro();
		joy1 = new Joystick(0);
		joy2 = new Joystick(1);
		swerve = new SwerveDrive(joy1, gyro);
		elevator = new Elevator();
		cubemech = new CubeMech();
		
		//Setup Autonomous command selection within SmartDashboard
		LLChooser = new SendableChooser<Command>();
		LLChooser.addDefault("1, LSwitch, Middle", new AutoLSwitchMiddle());
		SmartDashboard.putData("LLAutoChooser", LLChooser);
		
		LRChooser = new SendableChooser<Command>();
		LRChooser.addDefault("1, LSwitch, Middle", new AutoLSwitchMiddle());
		SmartDashboard.putData("LRAutoChooser", LRChooser);

		RRChooser = new SendableChooser<Command>();
		RRChooser.addDefault("1, RSwitch, Middle", new AutoRSwitchMiddle());
		SmartDashboard.putData("RRAutoChooser", RRChooser);

		RLChooser = new SendableChooser<Command>();
		RLChooser.addDefault("1, RSwitch, Middle", new AutoRSwitchMiddle());
		SmartDashboard.putData("RLAutoChooser", RLChooser);	
	}

	public void disabledInit() {
		if (LLCommand != null) LLCommand.cancel();
		if (RRCommand != null) RRCommand.cancel();
		if (LRCommand != null) LRCommand.cancel();
		if (RLCommand != null) RLCommand.cancel();
	}

	public void disabledPeriodic() {
		swerve.smartDash();
		elevator.smartdash();
		for (int i = 0; i < 4; i++)
			SmartDashboard.putNumber("swerve distance "+i, swerve.modules[i].getDistance());
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		gyro.reset();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		while (gameData.length() < 3 || gameData == null) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		LLCommand = (Command) LLChooser.getSelected();
		LRCommand = (Command) LRChooser.getSelected();
		RRCommand = (Command) RRChooser.getSelected();
		RLCommand = (Command) RLChooser.getSelected();
		
			if (gameData.charAt(0) == 'L') {
				if (gameData.charAt(1) == 'L') {
					LLCommand.start();
				}
				
				else {
					LRCommand.start();
				}
			}
			
			else {
				if (gameData.charAt(1) == 'R') {
					RRCommand.start();
				}
				
				else {
					RLCommand.start();
				}
			}
		}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		for (int i = 0; i < 4; i++)
			SmartDashboard.putNumber("swerve dist "+i, swerve.modules[i].getDistance());
		
	}

	public void teleopInit() {
		
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		swerve.move();
		elevator.move(joy2);
		cubemech.move(joy2);
	}
}