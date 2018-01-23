package org.usfirst.frc.team1533.robot.commands;

public class DriveCommand extends CommandBase {

    public DriveCommand() {
        requires(swerve);
    }

    protected void initialize() {
    }

    protected void execute() {
        double x = joy1.getX();
        double y = -joy1.getY();
        double rotation = joy1.getZ();
        if (Math.abs(x) < 0.1) x = 0;
        if (Math.abs(y) < 0.1) y = 0;
        if (Math.abs(rotation) < 0.1) rotation = 0;
        
        swerve.driveNormal(x, y, rotation);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
