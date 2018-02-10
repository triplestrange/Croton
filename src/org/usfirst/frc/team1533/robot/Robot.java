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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	// Defines all of the Robot subsytems
	public static Gyro gyro;
	public static Joystick joy1;
	public static Joystick joy2;
	public static SwerveDrive swerve;
	public static Elevator elevator;
	public static CubeMech cubemech;
	// Defines autonomous selection tools
	Command LeftAutoCommand;
	SendableChooser<Command> LeftAutoChooser;
	Command RightAutoCommand;
	SendableChooser<Command> RightAutoChooser;

	public void robotInit() {
		// Initializes Swerve Drive, Joysticks, Gyro, Elevator, and Cube Mechanism.
		gyro = new Gyro();
		joy1 = new Joystick(0);
		joy2 = new Joystick(1);
		swerve = new SwerveDrive(joy1, gyro);
		elevator = new Elevator();
		cubemech = new CubeMech();

		// Starts USB Camera feed at set resolution, FPS, and Brightness
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(400, 240);
		camera.setFPS(20);
		camera.setBrightness(15);

		// Setup Autonomous command selection within SmartDashboard
		//LeftAutoChooser = new SendableChooser<Command>();
		//LeftAutoChooser.addDefault("Switch+RightScale", new LeftSwitch());
		//LeftAutoChooser.addObject("Baseline", new Baseline());
		//SmartDashboard.putData("Autonomous Chooser", LeftAutoChooser);
		
		RightAutoChooser = new SendableChooser<Command>();
		RightAutoChooser.addDefault("Switch+RightScale", new RightSwitch());
		RightAutoChooser.addObject("Baseline", new Baseline());
		SmartDashboard.putData("Autonomous Chooser", RightAutoChooser);
	}

	public void disabledInit() {
		if (LeftAutoCommand != null)
			LeftAutoCommand.cancel();
		if (RightAutoCommand != null)
			RightAutoCommand.cancel();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		gyro.reset();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			
			if (gameData.charAt(0) == 'L') {
				//LeftAutoCommand = (Command) LeftAutoChooser.getSelected();
				//LeftAutoCommand.start();
			}
			
			else {
				RightAutoCommand = (Command) RightAutoChooser.getSelected();
				RightAutoCommand.start();
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
	}

}
