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
	public static Pneumatics pneumatics;
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
		pneumatics = new Pneumatics();

		// set pneumatics to starting configuration
		pneumatics.defaultvalue();

		// Setup Autonomous command selection within SmartDashboard

		LLChooser = new SendableChooser<Command>();
		LLChooser.addDefault("2/LSwitch/Middle", new AutoLSwitchMiddle());
		LLChooser.addObject("1/LSwitch/Left", new AutoLSwitchLeft());
		LLChooser.addObject("1/LScale/Right", new AutoLScaleRight());
		LLChooser.addObject("1/LScale/Left", new AutoLScaleLeft());
		LLChooser.addObject("2/LScale/Left", new AutoLScale2Left());
		LLChooser.addObject("Baseline", new AutoBaselineStraight());
		LLChooser.addObject("Baseline/Left", new AutoBaselineLeft());
		LLChooser.addObject("Baseline/Right", new AutoBaselineRight());
		SmartDashboard.putData("LLAutoChooser", LLChooser);
           
		LRChooser = new SendableChooser<Command>();
		LRChooser.addDefault("2/LSwitch/Middle", new AutoLSwitchMiddle());
		LRChooser.addObject("1/LSwitch/Left", new AutoLSwitchLeft());
		LRChooser.addObject("1/RScale/Left", new AutoRScaleLeft());
		LRChooser.addObject("1/RScale/Right", new AutoRScaleRight());
		LRChooser.addObject("2/RScale/Right", new AutoRScale2Right());
		LRChooser.addObject("Baseline", new AutoBaselineStraight());
		LRChooser.addObject("Baseline/Left", new AutoBaselineLeft());
		LRChooser.addObject("Baseline/Right", new AutoBaselineRight());
		SmartDashboard.putData("LRAutoChooser", LRChooser);

		RRChooser = new SendableChooser<Command>();
		RRChooser.addDefault("2/RSwitch/Middle", new AutoRSwitchMiddle());
		RRChooser.addObject("1/RSwitch/Right", new AutoRSwitchRight());
		RRChooser.addObject("1/RScale/Left", new AutoRScaleLeft());
		RRChooser.addObject("1/RScale/Right", new AutoRScaleRight());
		RRChooser.addObject("2/RScale/Right", new AutoRScale2Right());
		RRChooser.addObject("Baseline", new AutoBaselineStraight());
		RRChooser.addObject("Baseline/Right", new AutoBaselineRight());
		RRChooser.addObject("Baseline/Left", new AutoBaselineLeft());
		SmartDashboard.putData("RRAutoChooser", RRChooser);

		RLChooser = new SendableChooser<Command>();
		RLChooser.addDefault("2/RSwitch/Middle", new AutoRSwitchMiddle());
		RLChooser.addObject("1/RSwitch/Right", new AutoRSwitchRight());
		RLChooser.addObject("1/LScale/Right", new AutoLScaleRight());
		RLChooser.addObject("1/LScale/Left", new AutoLScaleLeft());
		RLChooser.addObject("2/LScale/Left", new AutoLScale2Left());
		RLChooser.addObject("Baseline", new AutoBaselineStraight());
		RLChooser.addObject("Baseline/Left", new AutoBaselineLeft());
		RLChooser.addObject("Baseline/Right", new AutoBaselineRight());
		SmartDashboard.putData("RLAutoChooser", RLChooser);
	}

	public void disabledInit() {
		if (LLCommand != null)
			LLCommand.cancel();
		if (RRCommand != null)
			RRCommand.cancel();
		if (LRCommand != null)
			LRCommand.cancel();
		if (RLCommand != null)
			RLCommand.cancel();
	}

	public void disabledPeriodic() {
		swerve.smartDash();
		elevator.smartdash();
		joy2.getRawButtonPressed(1);
		joy2.getRawButtonPressed(2);
		joy2.getRawButtonPressed(3);
		joy2.getRawButtonPressed(4);
		for (int i = 0; i < 4; i++)
			SmartDashboard.putNumber("swerve distance " + i, swerve.modules[i].getDistance());
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
				if (LLCommand != null) {
					LLCommand.start();
				}
			}

			else {
				if (LRCommand != null) {
					LRCommand.start();
				}
			}
		}

		else {
			if (gameData.charAt(1) == 'R') {
				if (RRCommand != null) {
					RRCommand.start();
				}
			}

			else {
				if (RLCommand != null) {
					RLCommand.start();
				}
			}
		}
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		swerve.smartDash();
		elevator.smartdash();
		for (int i = 0; i < 4; i++)
			SmartDashboard.putNumber("swerve dist " + i, swerve.modules[i].getDistance());

	}

	public void teleopInit() {
		if (LLCommand != null)
			LLCommand.cancel();
		if (RRCommand != null)
			RRCommand.cancel();
		if (LRCommand != null)
			LRCommand.cancel();
		if (RLCommand != null)
			RLCommand.cancel();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		swerve.smartDash();
		elevator.smartdash();
		swerve.move();
		elevator.move(joy2);
		cubemech.move(joy2);
		pneumatics.move(joy1);
	}
}