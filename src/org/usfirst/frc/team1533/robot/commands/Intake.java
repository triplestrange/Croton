package org.usfirst.frc.team1533.robot.commands;

import org.usfirst.frc.team1533.robot.subsystems.CubeMech;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {

	double duration;
	double startTime;

    public Intake(double duration) {
    	this.duration = duration;
    	
    }

    public void initialize() {
    	startTime = System.currentTimeMillis() / 1e3;
    }

    public void execute() {
    	CubeMech.IntakeMotorR.set(1);
    	CubeMech.IntakeMotorL.set(-1);
    }

    protected boolean isFinished() {
		return System.currentTimeMillis() / 1e3 - startTime >= duration;
	}
    
    public void end() {
		CubeMech.IntakeMotorR.set(0);
		CubeMech.IntakeMotorL.set(0);
    }
}
