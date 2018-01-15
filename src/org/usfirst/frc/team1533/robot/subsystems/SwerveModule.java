package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;
import org.usfirst.frc.team1533.robot.util.Vector;

import edu.wpi.first.wpilibj.*;

/**
 * A single swerve module with independent drive and steer.
 * 
 * @author Duncan Page
 */
public class SwerveModule {
    private PIDController steerPID;
    private SpeedController steerController, driveController;
    private AbsoluteEncoder steerEncoder;
    private Vector location;

    /**
     * Construct a swerve module given its two motor controllers, rotational encoder,
     *  and location on the robot.
     * 
     * @param driveController motor controller for drive motor
     * @param steerController motor controller for steer motor
     * @param steerEncoder absolute encoder on steering motor
     * @param locationX x coordinate of wheel relative to center of robot (inches)
     * @param locationY y coordinate of wheel relative to center of robot (inches)
     */
    public SwerveModule(SpeedController driveController, SpeedController steerController, AbsoluteEncoder steerEncoder,
            double locationX, double locationY) {
        this.steerController = steerController;
        this.driveController = driveController;
        this.steerEncoder = steerEncoder;

        location = new Vector(locationX, locationY);

        steerPID = new PIDController(Constants.Swerve.STEER_P, Constants.Swerve.STEER_I, Constants.Swerve.STEER_D,
                steerEncoder, steerController);
        steerPID.setInputRange(0, 2 * Math.PI);
        steerPID.setOutputRange(-Constants.Swerve.STEER_CAP, Constants.Swerve.STEER_CAP);
        steerPID.setContinuous();
        steerPID.enable();
    }

    /**
     * Set the module's target angle and target speed.
     * 
     * @param angle in radians
     * @param speed motor speed [-1 to 1]
     */
    public void set(double angle, double speed) {
        angle = normalizeAngle(angle);
        double diff = Math.abs(angle - steerEncoder.getAngle());

        // if we are more than 90 degrees from the target, flip 180 and drive in reverse
        if (diff > Math.PI / 2 && diff < 3 * Math.PI / 2) {
            angle = normalizeAngle(angle + Math.PI);
            speed *= -1;
        }

        steerPID.setSetpoint(angle);
        driveController.set(speed);
    }

    /**
     * Set the drive speed to zero, but don't change the steer angle.
     */
    public void stop() {
        driveController.set(0);
    }

    /**
     * Converts an angle to its equivalent between 0 and 2pi.
     * 
     * @param angle angle in radians
     * @return normalized angle between 0 and 2pi
     */
    private double normalizeAngle(double angle) {
        angle %= 2 * Math.PI;
        if (angle < 0) angle += 2 * Math.PI;
        return angle;
    }

    /**
     * Gets the module's location relative to the center of the robot
     * 
     * @return module location
     */
    public Vector getLocation() {
        return location.clone();
    }

    /**
     * Point the module forward and call this method to get the angleOffset for
     * the module's steer encoder.
     * 
     * @return calibration value for the steer encoder
     */
    public double getSteerCalibration() {
        return steerEncoder.getCalibration();
    }
}
