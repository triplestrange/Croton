package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.Constants;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeControl extends Command {

	double duration = Constants.AutonDelay;
	double startTime;

	public TimeControl() {

	}

	public void initialize() {
		startTime = System.currentTimeMillis() / 1e3;
	}

	public void execute() {
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}

	public void end() {
	}
}
