package org.usfirst.frc.team1533.robot;
import org.usfirst.frc.team1533.robot.commands.Baseline;
import org.usfirst.frc.team1533.robot.commands.MiddleSwitch;
import org.usfirst.frc.team1533.robot.subsystems.CubeMech;
import org.usfirst.frc.team1533.robot.subsystems.Elevator;
import org.usfirst.frc.team1533.robot.subsystems.Gyro;
import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static Gyro gyro;
	public static Joystick joy1;
    public static Joystick joy2;
	public static SwerveDrive swerve;
	public static Elevator elevator;
	public static CubeMech cubemech;
	//Defines autonomous selection tools
	Command autoCommand;
	SendableChooser<Command> autoChooser;
	
    public void robotInit() {
    	//Initializes Swerve Drive, Joysticks, Gyro, Elevator, and Cube Mechanism.
    	gyro = new Gyro();
        joy1 = new Joystick(0);
        joy2 = new Joystick(1);
    	swerve = new SwerveDrive(joy1, gyro);
    	elevator = new Elevator();
        cubemech = new CubeMech();
        
    	//Starts USB Camera feed at set resolution, FPS, and Brightness
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(1280, 720);
        
        //Setup Autonomous command selection within SmartDashboard
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Middle Switch", new MiddleSwitch());
		autoChooser.addObject("Baseline",  new Baseline());
    }

    public void disabledInit() {
		if (autoCommand != null) autoCommand.cancel();

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	if (autoCommand != null) autoCommand.cancel();
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
    	
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
