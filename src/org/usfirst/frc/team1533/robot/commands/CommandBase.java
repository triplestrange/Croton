package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.subsystems.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.*;

public abstract class CommandBase extends Command {
    static SwerveDrive swerve = new SwerveDrive();
    static Elevator elevator = new Elevator();
    static CubeMech cubemech = new CubeMech();
    static Joystick joy1 = new Joystick(0);
    static Joystick joy2 = new Joystick(1);

    public static void init() {

    }

}
