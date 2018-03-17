package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.MotionProfile;
import org.usfirst.frc.team1533.robot.ProfileFollower;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem implements PIDOutput{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_VictorSPX cimShifter1 = new WPI_VictorSPX(8);
	WPI_VictorSPX cimShifter2 = new WPI_VictorSPX(9);
	WPI_VictorSPX cimShifter3 = new WPI_VictorSPX(10);
	public Encoder encoder = new Encoder(0, 1);
	Joystick joy2;
	public ProfileFollower elevMP = new ProfileFollower(0.033, 0.5, 0, 0.025, encoder, this);
	double prevValue;
	public double acc = 60;
    public double vCruise = 30;
    

    public void move(Joystick joy2) {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	this.joy2 = joy2;
    	double y = joy2.getY();
    	double amp = Math.round(y*4)/4.0*.25;
    	if(prevValue!=amp) {
    		if(amp>0) {
    			elevMP.startProfile(new MotionProfile(encoder.getDistance(), 40, encoder.getRate(), 0, vCruise*amp, acc));
    		}
    		else if(amp<0) {
    			elevMP.startProfile(new MotionProfile(encoder.getDistance(), 0, encoder.getRate(), 0, -vCruise*amp, acc));
    		}
    		else {
    			elevMP.startProfile(new MotionProfile(encoder.getDistance(), encoder.getDistance(), encoder.getRate(), 0, vCruise, acc));
    		}
    		System.out.println("profile change " + System.currentTimeMillis());
    	}
    	prevValue=amp;
    	
    	
    		
    	//cimShifter1.set(ControlMode.PercentOutput, joy2.getY()*(-1));
    	//cimShifter2.set(ControlMode.PercentOutput, joy2.getY()*(-1));
    	//cimShifter3.set(ControlMode.PercentOutput, joy2.getY()*(-1));
    	
    	if(joy2.getRawButtonPressed(11)) {
    		//creates a motion profile from the current position and velocity to 10 inches at rest
    		elevMP.startProfile(new MotionProfile(encoder.getDistance(), 36, encoder.getRate(), 0, vCruise, acc));
    	}
    	if(joy2.getRawButtonPressed(10)) {
    		//creates a motion profile from the current position and velocity to 30 inches at rest
    		elevMP.startProfile(new MotionProfile(encoder.getDistance(), 0, encoder.getRate(), 0, vCruise, acc));
    	}
    	if(joy2.getRawButtonPressed(6)) {
    		//creates a motion profile from the current position and velocity to 30 inches at rest
    		elevMP.startProfile(new MotionProfile(encoder.getDistance(), 15, encoder.getRate(), 0, vCruise, acc));
    	}
    	if(joy2.getRawButton(7)) {
    		//resets the elevator so that the encoder is at 0 at the bottom
    		elevMP.cancel();
    		cimShifter1.set(ControlMode.PercentOutput, -.33);
    		cimShifter2.set(ControlMode.PercentOutput, -.33);
    		cimShifter3.set(ControlMode.PercentOutput, -.33);
    		encoder.reset();
    	}
    	else {
    		elevMP.update();
    	}
    }
    
    private double rdist(double v1, double v2) {
		return Math.abs(v1-v2)*(v1+v2)/(2*acc);
	}
    
    public Elevator() {
    	this.encoder.setDistancePerPulse(-(1.0/256)*(8.0/60)*(1.432*Math.PI));
    	
    }
    
    public void smartdash() {
    	double currentAmps1 = cimShifter1.getOutputCurrent();
    	double currentAmps2 = cimShifter2.getOutputCurrent();
    	double currentAmps3 = cimShifter3.getOutputCurrent();
    	
    	//double outputV = talon.getMotorOutputVoltage();
    	//double busV = talon.getBusVoltage();
    	//double outputPerc = talon.getMotorOutputPercent();
    	SmartDashboard.putNumber("Motor1", currentAmps1);
    	SmartDashboard.putNumber("Motor1", currentAmps2);
    	SmartDashboard.putNumber("Motor1", currentAmps3);
    	SmartDashboard.putNumber("Encoder Value", encoder.getDistance());
    	
    	//SmartDashboard.putNumber("Z-Axis", joy2.getZ());
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		cimShifter1.set(ControlMode.PercentOutput, output);
		cimShifter2.set(ControlMode.PercentOutput, output);
		cimShifter3.set(ControlMode.PercentOutput, output);
		
		
	}
}

