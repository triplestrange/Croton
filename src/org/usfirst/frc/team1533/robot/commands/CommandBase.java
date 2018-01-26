package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.subsystems.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.*;

public abstract class CommandBase extends Command {
	public static Gyro gyro = new Gyro();
    static Joystick joy1 = new Joystick(0);
    static Joystick joy2 = new Joystick(1);
	static SwerveDrive swerve = new SwerveDrive(joy1, gyro);
	static Elevator elevator = new Elevator();
    static CubeMech cubemech = new CubeMech();


    public static void init() {

    }

}
