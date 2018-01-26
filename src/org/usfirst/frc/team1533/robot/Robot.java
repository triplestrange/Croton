package org.usfirst.frc.team1533.robot;
import org.usfirst.frc.team1533.robot.subsystems.*;
import org.usfirst.frc.team1533.robot.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends IterativeRobot {
	public static Joystick joy1, joy2;
	public static SwerveDrive swerve;
	public static Gyro gyro;
	
	//Defines autonomous selection tools
	Command autoCommand;
	SendableChooser<Command> autoChooser;
	
    public void robotInit() {
    	gyro = new Gyro();
    	joy1 = new Joystick(0);
        joy2 = new Joystick(1);
    	swerve = new SwerveDrive(joy1, gyro);
        //Initializes CommandBase
    	CommandBase.init();
        
    	//Starts USB Camera feed at set resolution
        //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        //camera.setResolution(1280, 720);
        
        //Sets up autonomous code selector
		autoChooser = new SendableChooser<Command>();
		autoChooser.addObject("Middle Switch", new middleSwitch());
		autoChooser.addDefault("Baseline", new autoBaseline());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }

    public void disabledInit() {

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
    }

}
