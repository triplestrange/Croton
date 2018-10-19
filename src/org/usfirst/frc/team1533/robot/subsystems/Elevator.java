package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;
import org.usfirst.frc.team1533.robot.TrapezoidProfile;
import org.usfirst.frc.team1533.robot.ProfileFollower;
import org.usfirst.frc.team1533.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem implements PIDOutput {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	WPI_VictorSPX cim1 = new WPI_VictorSPX(Constants.Elevator.cim1);
	WPI_VictorSPX cim2 = new WPI_VictorSPX(Constants.Elevator.cim2);
	WPI_VictorSPX cim3 = new WPI_VictorSPX(Constants.Elevator.cim3);
	public static Encoder encoder = new Encoder(Constants.Elevator.mag1, Constants.Elevator.mag2);
	Joystick joy1, joy2;
	public ProfileFollower elevMP = new ProfileFollower(0.033, 0.0, 0.5, 0, 0.025, encoder, this);
	double prevValue;
	public double acc = 60;
	public double vCruise = 30;
	double speed;

	public Elevator() {
		encoder.setDistancePerPulse(-(1.0 / 256) * (8.0 / 60) * (1.432 * Math.PI));
	}

	public void move(Joystick joy1, Joystick joy2) {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		this.joy1 = joy1;
		this.joy2 = joy2;
		double y = joy2.getY();
		cim1.configOpenloopRamp(0.25, 1);
		cim2.configOpenloopRamp(0.25, 1);
		cim3.configOpenloopRamp(0.25, 1);

		if (Robot.oneController == true) {
			if (joy1.getRawButton(Constants.Xinput.START)) {
				// resets the elevator so that the encoder is at 0 at the bottom
				elevMP.cancel();
				reset();
			}

			if (joy1.getRawButtonPressed(Constants.Xinput.Y)) {
				// creates a motion profile from the current position and velocity to 36 inches
				// at rest
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), 36, encoder.getRate(), 0, vCruise, acc));
			}

			if (joy1.getRawButtonPressed(Constants.Xinput.A)) {
				// creates a motion profile from the current position and velocity to 0 inches
				// at rest
				elevMP.startProfile(new TrapezoidProfile(encoder.getDistance(), 0, encoder.getRate(), 0, vCruise, acc));
			}

			if (joy1.getRawButtonPressed(Constants.Xinput.B)) {
				// creates a motion profile from the current position and velocity to 15 inches
				// at rest
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), 21, encoder.getRate(), 0, vCruise, acc));
			}
			if (joy1.getPOV() != -1) {
				elevMP.cancel();
//				if (joy1.getRawButton(3)) {
//					if (joy1.getPOV() == 0) {
//						cim1.set(ControlMode.PercentOutput, -100);
//						cim2.set(ControlMode.PercentOutput, -100);
//						cim3.set(ControlMode.PercentOutput, -100);
//					}
//					if (joy1.getPOV() == 180) {
//						cim1.set(ControlMode.PercentOutput, 100);
//						cim2.set(ControlMode.PercentOutput, 100);
//						cim3.set(ControlMode.PercentOutput, 100);
//					}
//				} else {
					if (joy1.getPOV() == 0) {
						cim1.set(ControlMode.PercentOutput, -50);
						cim2.set(ControlMode.PercentOutput, -50);
						cim3.set(ControlMode.PercentOutput, -50);
					}
					if (joy1.getPOV() == 180) {
						cim1.set(ControlMode.PercentOutput, 40);
						cim2.set(ControlMode.PercentOutput, 40);
						cim3.set(ControlMode.PercentOutput, 40);
					}
//				}
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), encoder.getDistance(), 0, 0, vCruise, acc));
			} else {
				elevMP.update();
			}
		}

		else {

			if (joy2.getRawButton(Constants.DirectInput.START)) {
				// resets the elevator so that the encoder is at 0 at the bottom
				elevMP.cancel();
				reset();
			}

			if (joy2.getRawButtonPressed(1)) {
				// creates a motion profile from the current position and velocity to 36 inches
				// at rest
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), 8.88, encoder.getRate(), 0, vCruise, acc));
			}

			if (joy2.getRawButtonPressed(2)) {
				// creates a motion profile from the current position and velocity to 0 inches
				// at rest
				elevMP.startProfile(new TrapezoidProfile(encoder.getDistance(), 0, encoder.getRate(), 0, vCruise, acc));
			}

			if (joy2.getRawButtonPressed(3)) {
				// creates a motion profile from the current position and velocity to 15 inches
				// at rest
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), 21, encoder.getRate(), 0, vCruise, acc));
			}

			if (joy2.getRawButtonPressed(4)) {
				// creates a motion profile from the current position and velocity to 36 inches
				// at rest
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), 36, encoder.getRate(), 0, vCruise, acc));
			}

			else if (Math.abs(y) > 0.1) {
				elevMP.cancel();
				if (joy2.getRawButton(Constants.DirectInput.RIGHT_TRIGGER)) {
					if (y < 0.1) {
						cim1.set(ControlMode.PercentOutput, y * (-1));
						cim2.set(ControlMode.PercentOutput, y * (-1));
						cim3.set(ControlMode.PercentOutput, y * (-1));
					} else {
						cim1.set(ControlMode.PercentOutput, y * (-1));
						cim2.set(ControlMode.PercentOutput, y * (-1));
						cim3.set(ControlMode.PercentOutput, y * (-1));
					}
				} else {
					if (y < 0.1) {
						cim1.set(ControlMode.PercentOutput, y * (-0.5));
						cim2.set(ControlMode.PercentOutput, y * (-0.5));
						cim3.set(ControlMode.PercentOutput, y * (-0.5));
					} else {
						cim1.set(ControlMode.PercentOutput, y * (-0.4));
						cim2.set(ControlMode.PercentOutput, y * (-0.4));
						cim3.set(ControlMode.PercentOutput, y * (-0.4));
					}
				}
				elevMP.startProfile(
						new TrapezoidProfile(encoder.getDistance(), encoder.getDistance(), 0, 0, vCruise, acc));
			} else {
				elevMP.update();
			}
		}
	}

	public void smartdash() {
		SmartDashboard.putNumber("Elevator Encoder", encoder.getDistance());
	}

	public void reset() {
		encoder.reset();
		elevMP.cancel();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		cim1.set(ControlMode.PercentOutput, output);
		cim2.set(ControlMode.PercentOutput, output);
		cim3.set(ControlMode.PercentOutput, output);

	}
}