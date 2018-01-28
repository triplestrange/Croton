package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

    public DriveCommand() {
        requires(Robot.swerve);
    }

    protected void initialize() {
    }

    protected void execute() {
        double x = Robot.joy1.getX();
        double y = -Robot.joy1.getY();
        double rotation = Robot.joy1.getZ();
        if (Math.abs(x) < 0.1) x = 0;
        if (Math.abs(y) < 0.1) y = 0;
        if (Math.abs(rotation) < 0.1) rotation = 0;
        
        Robot.swerve.driveNormal(x, y, rotation);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
