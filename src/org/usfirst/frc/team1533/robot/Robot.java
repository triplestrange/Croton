package org.usfirst.frc.team1533.robot;
import org.usfirst.frc.team1533.robot.commands.Baseline;
import org.usfirst.frc.team1533.robot.commands.DriveCommand;
import org.usfirst.frc.team1533.robot.commands.MiddleSwitch;
import org.usfirst.frc.team1533.robot.subsystems.CubeMech;
import org.usfirst.frc.team1533.robot.subsystems.Elevator;
import org.usfirst.frc.team1533.robot.subsystems.Gyro;
import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	//Defines all of the Robot subsytems
	public static Gyro gyro;
	public static Joystick joy1;
	public static Joystick joy2;
	public static SwerveDrive swerve;
	public static Elevator elevator;
	public static CubeMech cubemech;
	public static DriveCommand drive;
	//Defines autonomous selection tools
	Command AutoCommand;
	SendableChooser<Command> AutoChooser;
	
	public void robotInit() {
		//Initializes Swerve Drive, Joysticks, Gyro, Elevator, and Cube Mechanism.
		gyro = new Gyro();
		joy1 = new Joystick(0);
		joy2 = new Joystick(1);
		swerve = new SwerveDrive(joy1, gyro);
		elevator = new Elevator();
		cubemech = new CubeMech();
		drive = new DriveCommand();

		//Starts USB Camera feed at set resolution, FPS, and Brightness
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(400, 240);
		camera.setFPS(25);

		//Setup Autonomous command selection within SmartDashboard
		AutoChooser = new SendableChooser<Command>();
		AutoChooser.addDefault("Middle Switch", new MiddleSwitch());
		AutoChooser.addObject("Baseline",  new Baseline());
		SmartDashboard.putData("Autonomous Chooser", AutoChooser);
	}

	public void disabledInit() {
		if (AutoCommand != null) AutoCommand.cancel();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		AutoCommand = (Command) AutoChooser.getSelected();
		AutoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		//Starts Drive Command for Swerve
		drive.start();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

}
