package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.*;

public abstract class CommandBase extends Command {
    static SwerveDrive swerve = new SwerveDrive();
    static Joystick joy1 = new Joystick(0);

    public static void init() {

    }

}
