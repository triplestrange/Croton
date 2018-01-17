package org.usfirst.frc.team1533.robot;
import org.usfirst.frc.team1533.robot.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends IterativeRobot {

	Command autoCommand;
	SendableChooser<Command> autoChooser;
	
    public void robotInit() {
        CommandBase.init();
        
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(1280, 720);
        
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Middle Switch", middleSwitch());
		autoChooser.addObject("Baseline", autoBaseline());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }

    public void disabledInit() {

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			//Left Switch Auto Code
		} else {
			//Right Switch Auto Code
		}

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
