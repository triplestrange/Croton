package org.usfirst.frc.team1533.robot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.usfirst.frc.team1533.robot.subsystems.Gyro;
import org.usfirst.frc.team1533.robot.subsystems.SwerveDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PathTracking{
	
	double currentX, currentY, currentZ;
	public double[] drive;
	double timeIter;
	SwerveDrive swerveDrive;
	Gyro gyro;
	PrintWriter writer;
	
	public PathTracking(SwerveDrive modules) {
		this.swerveDrive = modules;
		 try {
			writer = new PrintWriter("robot.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset() {
		
	}
	
	public void update() {
		double xAvg = 0;
		double yAvg = 0;
		for(int i=0; i<swerveDrive.modules.length; i++) {
			double dEncoder = swerveDrive.modules[i].getDistance();
			double sEncoder = swerveDrive.modules[i].getAngle();
			double dx = dEncoder*Math.sin(sEncoder) - currentY*(Math.toRadians(gyro.getAngle() - currentZ));
			double dy = dEncoder*Math.cos(sEncoder) + currentX*(Math.toRadians(gyro.getAngle() - currentZ));
			xAvg = xAvg + dx/swerveDrive.modules.length;
			yAvg = yAvg + dy/swerveDrive.modules.length; 
		}
		currentX = currentX + xAvg;
		currentY = currentY + yAvg;
		writer.println(currentX+' '+currentY);
		SmartDashboard.putNumber("CurrentX", currentX);
		SmartDashboard.putNumber("CurrentY", currentY);
	}
//	public void close() {
//		writer.close();
//	}
}