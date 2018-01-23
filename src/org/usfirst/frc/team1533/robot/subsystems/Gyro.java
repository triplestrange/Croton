package org.usfirst.frc.team1533.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;

public class Gyro {
	public static ADXRS450_Gyro gyro;
	double currentangle;
	double offset = 0;
	
	public Gyro(){
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		gyro.calibrate();
	}

	public double getAngle() {
		return gyro.getAngle() + offset;
	}
	
	public double getRate() {
		return gyro.getRate();
	}
	
	public double angleCorrect(){
		return gyro.getAngle() * -.025;
	}
	public void reset(){
		gyro.reset();
		offset = 0;
	}
	public void set(double angle) {
		gyro.reset();
		offset = angle;
	}
	public double straight(boolean angle){
		if(angle){
			currentangle = gyro.getAngle();
			SwerveDrive.angle = false;
		}
		return	(gyro.getAngle()-currentangle)*.015;
	}

}