package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * @author DJ Dierking
 */
public class CubeMech extends Subsystem {

	// Utilize the second controller
	Joystick joy2;

	// Left and Right Intake motors
	public static WPI_VictorSPX IntakeMotorL = new WPI_VictorSPX(Constants.CubeMech.intakeL);
	public static WPI_VictorSPX IntakeMotorR = new WPI_VictorSPX(Constants.CubeMech.intakeR);

	public void move(Joystick joy2) {
		this.joy2 = joy2;

		// If the right bumper is pressed, shoot the cube 100% speed
		if (joy2.getRawButton(Constants.Controller.RIGHT_BUMPER)) {
			IntakeMotorL.set(-1);
			IntakeMotorR.set(1);
		}

		// If the left trigger is pressed, shoot the cube at 35% speed
		else if (joy2.getRawButton(Constants.Controller.LEFT_TRIGGER)) {
			IntakeMotorL.set(-.35);
			IntakeMotorR.set(0.35);
		}

		// If the left bumper is pressed, intake the cube
		else if (joy2.getRawButton(Constants.Controller.LEFT_BUMPER)) {
			IntakeMotorL.set(1);
			IntakeMotorR.set(-1);
		}

		// If nothing is being pressed then frickin do nothing
		else {
			IntakeMotorL.set(0);
			IntakeMotorR.set(0);
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
