package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;
import org.usfirst.frc.team1533.robot.commands.DriveCommand;
import org.usfirst.frc.team1533.robot.util.Vector;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Duncan Page
 */
public class SwerveDrive extends Subsystem {
    private Vector centerOfRotation = new Vector(0, 0);
    private SwerveModule[] modules;

    public SwerveDrive() {
        // initialize array of modules
        // array can be any size, as long as the position of each module is
        // specified in its constructor
        modules = new SwerveModule[] {
                // front left
                new SwerveModule(new Talon(Constants.Swerve.FL_DRIVE), 
                        new Talon(Constants.Swerve.FL_STEER),
                        new AbsoluteEncoder(Constants.Swerve.FL_ENCODER, Constants.Swerve.FL_ENCODER_OFFSET, 
                                Constants.Swerve.ENCODERS_REVERSED),
                        -Constants.Swerve.BASE_WIDTH / 2, 
                        Constants.Swerve.BASE_LENGTH / 2),
                // front right
                new SwerveModule(new Talon(Constants.Swerve.FR_DRIVE), 
                        new Talon(Constants.Swerve.FR_STEER),
                        new AbsoluteEncoder(Constants.Swerve.FR_ENCODER, Constants.Swerve.FR_ENCODER_OFFSET, 
                                Constants.Swerve.ENCODERS_REVERSED),
                        Constants.Swerve.BASE_WIDTH / 2, 
                        Constants.Swerve.BASE_LENGTH / 2),
                // back left
                new SwerveModule(new Talon(Constants.Swerve.BL_DRIVE), 
                        new Talon(Constants.Swerve.BL_STEER),
                        new AbsoluteEncoder(Constants.Swerve.BL_ENCODER, Constants.Swerve.BL_ENCODER_OFFSET, 
                                Constants.Swerve.ENCODERS_REVERSED),
                        -Constants.Swerve.BASE_WIDTH / 2, 
                        -Constants.Swerve.BASE_LENGTH / 2),
                // back right
                new SwerveModule(new Talon(Constants.Swerve.BR_DRIVE), 
                        new Talon(Constants.Swerve.BR_STEER),
                        new AbsoluteEncoder(Constants.Swerve.BR_ENCODER, Constants.Swerve.BR_ENCODER_OFFSET,
                                Constants.Swerve.ENCODERS_REVERSED),
                        Constants.Swerve.BASE_WIDTH / 2, 
                        -Constants.Swerve.BASE_LENGTH / 2) };
    }

    /**
     * Sets the robot's center of rotation relative to the physical center of
     * the robot. For example, if set to (0, 30), the robot will turn around the
     * point 30 inches in front of the robot's physical center.
     */
    public void setCenterOfRotation(double x, double y) {
        centerOfRotation.x = x;
        centerOfRotation.y = y;
    }

    /**
     * Private method to set angles and speeds of the modules. Should not be
     * used directly.
     * 
     * @param xSpeed speed in left/right direction (-1 to 1)
     * @param ySpeed speed in forward/reverse direction (-1 to 1)
     * @param rotationSpeed rotation speed (-1 to 1, positive is clockwise)
     * @param headingOffset offset in heading in degrees, positive is a
     *        counter-clockwise offset
     */
    private void drive(double xSpeed, double ySpeed, double rotationSpeed, double headingOffset) {
        Vector[] vects = new Vector[modules.length];
        Vector transVect = new Vector(xSpeed, ySpeed);

        // apply heading offset
        transVect.rotate(Math.toRadians(headingOffset));

        double farthestDist = 0;
        for (int i = 0; i < modules.length; i++) {
            // calculate module's position relative to pivot point
            vects[i] = modules[i].getLocation().subtract(centerOfRotation);
            // find farthest distance from pivot
            farthestDist = Math.max(farthestDist, vects[i].getMagnitude());
        }

        double maxPower = 1;
        for (int i = 0; i < modules.length; i++) {
            // rotation motion created by driving each module perpendicular to
            // the vector from the pivot point
            vects[i].rotate(-Math.PI / 2);
            // scale by relative rate and normalize to the farthest module
            // i.e. the farthest module drives with power equal to 'rotation'
            // variable
            vects[i].multiply(rotationSpeed / farthestDist);
            vects[i].add(transVect);
            // calculate largest power assigned to modules
            // if any exceed 100%, all must be scale down
            maxPower = Math.max(maxPower, vects[i].getMagnitude());
        }

        for (int i = 0; i < modules.length; i++) {
            // scale down by the largest power that exceeds 100%
            double power = vects[i].getMagnitude() / maxPower;
            if (power > 0.05) {
                modules[i].set(vects[i].getAngle() - Math.PI / 2, power);
            } else {
                modules[i].stop();
            }
        }
    }

    /**
     * Points all of the modules toward the center, restricting movement of the
     * robot.
     */
    public void setBrakeMode() {
        for (SwerveModule module : modules)
            module.set(module.getLocation().getAngle(), 0);
    }

    /**
     * Drive robot oriented.
     * 
     * @param x x direction translation speed (-1 to 1)
     * @param y y direction translation speed (-1 to 1)
     * @param rotation rotation speed (-1 to 1)
     */
    public void driveRobotOriented(double x, double y, double rotation) {
        drive(x, y, rotation, 0);
    }

    /**
     * Drive field oriented.
     * 
     * @param x x direction translation speed (-1 to 1)
     * @param y y direction translation speed (-1 to 1)
     * @param rotation rotation speed (-1 to 1)
     * @param gyroAngle current gyro angle in degrees
     */
    public void driveFieldOriented(double x, double y, double rotation, double gyroAngle) {
        drive(x, y, rotation, gyroAngle);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
}
