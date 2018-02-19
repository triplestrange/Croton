package org.usfirst.frc.team1533.robot;
import org.usfirst.frc.team1533.robot.commands.*;
import org.usfirst.frc.team1533.robot.subsystems.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends IterativeRobot {
	// Defines all of the Robot subsytems
	public static Gyro gyro;
	public static Joystick joy1;
	public static Joystick joy2;
	public static Joystick joy3;
	public static SwerveDrive swerve;
	public static Elevator elevator;
	public static CubeMech cubemech;
	public static Ramps ramps;
	// Defines autonomous selection tools
	Command LeftSwitchCommand, RightSwitchCommand, LeftScaleCommand, RightScaleCommand;
	SendableChooser<Command> LeftSwitchChooser, RightSwitchChooser, LeftScaleChooser, RightScaleChooser;

	public void robotInit() {
		// Initializes Swerve Drive, Joysticks, Gyro, Elevator, and Cube Mechanism.
		gyro = new Gyro();
		joy1 = new Joystick(0);
		joy2 = new Joystick(1);
		joy3 = new Joystick(2);
		swerve = new SwerveDrive(joy1, gyro);
		elevator = new Elevator();
		cubemech = new CubeMech();
		ramps = new Ramps();

		// Starts USB Camera feed at set resolution, FPS, and Brightness
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(400, 240);
		camera.setFPS(30);
		camera.setBrightness(15);

		//Setup Autonomous command selection within SmartDashboard
		LeftSwitchChooser = new SendableChooser<Command>();
		LeftSwitchChooser.addDefault("Switch+RightScale", new LeftSwitch());
		LeftSwitchChooser.addObject("Baseline", new Baseline());
		SmartDashboard.putData("Autonomous Chooser", LeftSwitchChooser);
		
		RightSwitchChooser = new SendableChooser<Command>();
		RightSwitchChooser.addDefault("Switch+RightScale", new RightSwitch());
		RightSwitchChooser.addObject("Baseline", new Baseline());
		SmartDashboard.putData("Autonomous Chooser", RightSwitchChooser);
		
		LeftScaleChooser = new SendableChooser<Command>();
		LeftScaleChooser.addDefault("Switch+RightScale", new LeftSwitch());
		LeftScaleChooser.addObject("Baseline", new Baseline());
		SmartDashboard.putData("Autonomous Chooser", LeftScaleChooser);
		
		RightScaleChooser = new SendableChooser<Command>();
		RightScaleChooser.addDefault("Switch+RightScale", new RightSwitch());
		RightScaleChooser.addObject("Baseline", new Baseline());
		SmartDashboard.putData("Autonomous Chooser", RightScaleChooser);
	}

	public void disabledInit() {
		
	}

	public void disabledPeriodic() {
		swerve.smartDash();
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		gyro.reset();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			
			if (gameData.charAt(0) == 'L') {
				LeftSwitchCommand = (Command) LeftSwitchChooser.getSelected();
				LeftSwitchCommand.start();
				if (gameData.charAt(1) == 'L') {
					LeftScaleCommand = (Command) LeftScaleChooser.getSelected();
					LeftScaleCommand.start();
				}
				
				else {
					RightScaleCommand = (Command) RightScaleChooser.getSelected();
					RightScaleCommand.start();
				}
			}
			
			else {
				RightSwitchCommand = (Command) RightSwitchChooser.getSelected();
				RightSwitchCommand.start();
				if (gameData.charAt(1) == 'L') {
					LeftScaleCommand = (Command) LeftScaleChooser.getSelected();
					LeftScaleCommand.start();
				}
				
				else {
					RightScaleCommand = (Command) RightScaleChooser.getSelected();
					RightScaleCommand.start();
				}
			}
		}
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		swerve.move();
		elevator.testing(joy2);
		ramps.testing(joy3);
		
	}
}