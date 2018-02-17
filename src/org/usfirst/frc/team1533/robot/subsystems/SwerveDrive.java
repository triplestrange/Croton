package org.usfirst.frc.team1533.robot.subsystems;

import org.usfirst.frc.team1533.robot.Constants;
import org.usfirst.frc.team1533.robot.subsystems.Gyro;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Duncan Wizardman Page
 */
public class SwerveDrive extends Subsystem {
	public double pivotX, pivotY, lastpressed, transAngle, pivotspeed;
	public static double angleRotation, startangle;
	boolean lockwheels, drivingField, ypressed;
	public static boolean rotating, angle;
	public SwerveModule[] modules;
	boolean fieldOrientation = false;
	Vector tankVector;
	Joystick joy1, joy2;
	Gyro gyro;
	//PWMSpeedController flDrive, frDrive, blDrive, brDrive, flsteer, frsteer, blsteer, brsteer;
	PIDController pid;
	double lastAngle;

	/**
	 * Custom constructor for current robot.
	 */
	public SwerveDrive(Joystick joy1,Gyro gyro) {
		this.joy1 = joy1;
		this.gyro = gyro;
		
		//Initializes and defines Swerve Modules.  The Array can be of any size, so long as they are all defined
		modules = new SwerveModule[] {
				//front left swerve module
				new SwerveModule(new WPI_TalonSRX(Constants.SwerveDrive.FL_DRIVE),
						new WPI_VictorSPX(Constants.SwerveDrive.FL_STEER),
						new AbsoluteEncoder(Constants.SwerveDrive.FL_ENCODER, Constants.SwerveDrive.FL_ENC_OFFSET),
						-Constants.SwerveDrive.WHEEL_BASE_WIDTH/2,
						Constants.SwerveDrive.WHEEL_BASE_LENGTH/2
						),
				//front right swerve module
				new SwerveModule(new WPI_TalonSRX(Constants.SwerveDrive.FR_DRIVE), 
						new WPI_VictorSPX(Constants.SwerveDrive.FR_STEER),
						new AbsoluteEncoder(Constants.SwerveDrive.FR_ENCODER, Constants.SwerveDrive.FR_ENC_OFFSET),
						Constants.SwerveDrive.WHEEL_BASE_WIDTH/2,
						Constants.SwerveDrive.WHEEL_BASE_LENGTH/2
						),
				//back left swerve module
				new SwerveModule(new WPI_TalonSRX(Constants.SwerveDrive.BL_DRIVE),
						new WPI_VictorSPX(Constants.SwerveDrive.BL_STEER),
						new AbsoluteEncoder(Constants.SwerveDrive.BL_ENCODER, Constants.SwerveDrive.BL_ENC_OFFSET),
						-Constants.SwerveDrive.WHEEL_BASE_WIDTH/2,
						-Constants.SwerveDrive.WHEEL_BASE_LENGTH/2
						),
				//back right swerve module
				new SwerveModule(new WPI_TalonSRX(Constants.SwerveDrive.BR_DRIVE), 
						new WPI_VictorSPX(Constants.SwerveDrive.BR_STEER),
						new AbsoluteEncoder(Constants.SwerveDrive.BR_ENCODER, Constants.SwerveDrive.BR_ENC_OFFSET),
						Constants.SwerveDrive.WHEEL_BASE_WIDTH/2,
						-Constants.SwerveDrive.WHEEL_BASE_LENGTH/2
						)
		};
		enable();
	}

	/**
	 * @param pivotX x coordinate in inches of pivot point relative to center of robot
	 * @param pivotY y coordinate in inches of pivot point relative to center of robot
	 */
	public void setPivot(double pivotX, double pivotY) {
		this.pivotX = pivotX;
		this.pivotY = pivotY;
	}

	public void debugMode(){

	}
	/**
	 * Drive with field oriented capability
	 * @param translationX relative speed in left/right direction (-1 to 1)
	 * @param translationY relative speed in forward/reverse direction (-1 to 1)
	 * @param rotation relative rate of rotation around pivot point (-1 to 1) positive is clockwise
	 * @param heading offset in heading in radians (used for field oriented control)
	 */
	private void driveWithOrient(double translationX, double translationY, double rotation, boolean fieldOrientation) {
		Vector correctOrientation = correctOrientationVector(translationX, translationY);
		translationX = fieldOrientation ? correctOrientation.x: translationX;
		translationY = fieldOrientation ? correctOrientation.y : translationY;
		Vector[] vects = new Vector[modules.length];
		Vector transVect = new Vector(translationX, translationY),
				pivotVect = new Vector(pivotX, pivotY);
		setTrans(transVect);


		//if there is only one module ignore rotation
		if (modules.length < 2)
			for (SwerveModule module : modules) 
				module.set(transVect.getAngle(), Math.min(1, transVect.getMagnitude())); //cap magnitude at 1

		double maxDist = 0;
		for (int i = 0; i < modules.length; i++) {
			vects[i] = new Vector(modules[i].positionX, modules[i].positionY);
			vects[i].subtract(pivotVect); //calculate module's position relative to pivot point
			maxDist = Math.max(maxDist, vects[i].getMagnitude()); //find farthest distance from pivot
		}

		double maxPower = 1;
		for (int i = 0; i < modules.length; i++) {
			//rotation motion created by driving each module perpendicular to
			//the vector from the pivot point
			vects[i].makePerpendicular();
			//scale by relative rate and normalize to the farthest module
			//i.e. the farthest module drives with power equal to 'rotation' variable
			vects[i].scale(rotation / maxDist);
			vects[i].add(transVect);
			//calculate largest power assigned to modules
			//if any exceed 100%, all must be scale down
			maxPower = Math.max(maxPower, vects[i].getMagnitude());
		}


		double power;
		for (int i = 0; i < modules.length; i++) {
			power = vects[i].getMagnitude() / maxPower; //scale down by the largest power that exceeds 100%
			if (power > .05) {
				setTrans(vects[i]);
				modules[i].set(vects[i].getAngle()-Math.PI/2, power);
			} else {
				modules[i].rest();
			}
		}
	}
	
	public void setTrans(Vector vector){
		this.tankVector = vector;
	}
	public void setTransAngle(Vector vector){
		this.transAngle = vector.getAngle();
	}
	public double getTransAngle(){
		return transAngle;
	}
	public Vector getTrans(){
		return tankVector;
	}

	/**
	 * Regular robot oriented control.
	 * @param translationX relative speed in left/right direction (-1 to 1)
	 * @param translationY relative speed in forward/reverse direction (-1 to 1)
	 * @param rotation relative rate of rotation around pivot point (-1 to 1) positive is clockwise
	 */

	private Vector correctOrientationVector(double x, double y) {
		double angle = Gyro.getAngle() * Math.PI / 180;
		return new Vector (x*Math.cos(angle) - y*Math.sin(angle), x*Math.sin(angle) + y*Math.cos(angle));
	}
	public void driveNormal(double translationX, double translationY, double rotation) {
		driveWithOrient(translationX, translationY, rotation, false);
	}
	public void driveField(double translationX, double translationY, double rotation){
		driveWithOrient(translationX, translationY, rotation, true);
	}

	public void enable() {
		for (SwerveModule module : modules) module.enable();
	}

	public void disable() {
		for (SwerveModule module : modules) module.disable();
	}
	public void autonomous(double x, double y, double z){
		driveNormal(x, y, z);
	}


	public void move(){
		if(joy1.getRawButton(Constants.Controller.RIGHT_TRIGGER)){	
			if (!ypressed) drivingField = !drivingField;
			ypressed = true;
			SmartDashboard.putBoolean("field?", drivingField);
		}else{
			ypressed = false;
		}
		if(joy1.getRawButton(Constants.Controller.LEFT_TRIGGER)){
			gyro.reset();
		}
		
		if (joy1.getRawButton(Constants.Controller.B)) lockWheels();
		double speed = 100;
//		if (joy1.getRawButton(Constants.RIGHT_TRIGGER)) speed = 100;
		double turnRate = 80;
		
		if (joy1.getRawButton(Constants.Controller.LEFT_BUMPER)) turnRate = 100;
		double x = joy1.getX();
		double y = joy1.getY();
		double z = joy1.getZ();
		
		if((Math.abs(x) > .1 || Math.abs(y)>.1 || Math.abs(z) > .1) && !drivingField){		
			if(Math.abs(z)<.1){
				driveNormal((x*speed)/100, (-y*speed)/100, 0);//(-gyro.getAngle()+lastAngle)*.015);
			}else{
				driveNormal((x*speed)/100, (-y*speed)/100, (z*turnRate/100));
				lastAngle = Gyro.getAngle();
			}
		}else if((Math.abs(x) > .1 || Math.abs(y)>.1 || Math.abs(z)>.1) && drivingField){
			if(Math.abs(z)<.1){
				driveField((x*speed)/100, (-y*speed)/100, 0);//(-gyro.getAngle()+lastAngle)*.015);
			}else{
				driveField((x*speed)/100, (-y*speed)/100, (z*turnRate/100));
				lastAngle = Gyro.getAngle();
			}
		}
		else{
			driveNormal(0,0,0);
			lastAngle = Gyro.getAngle();
		}
	}

	public void stop(int module){
		modules[module].driveController.set(0);
		modules[module].steerController.set(0);
	}

	public void lockWheels(){
		modules[0].set(45, 0);
		modules[1].set(-45, 0);
		modules[2].set(-45, 0);
		modules[3].set(45, 0);
	}

	/**
	 * 2D Mathematical Vector
	 */
	class Vector {
		double x = 0, y = 0;

		public Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getAngle() {
			return Math.atan2(y, x);
		}

		public double getMagnitude() {
			return Math.hypot(x, y);
		}

		public void scale(double scalar) {
			x *= scalar;
			y *= scalar;
		}

		public void add(Vector v) {
			x += v.x;
			y += v.y;
		}

		public void subtract(Vector v) {
			x -= v.x;
			y -= v.y;
		}

		public void makePerpendicular() {
			double temp = x;
			x = y;
			y = -temp;
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	public void smartDash(){
		SmartDashboard.putNumber("FL", modules[0].getAngle()*360/(2*Math.PI));
		SmartDashboard.putNumber("FR", modules[1].getAngle()*360/(2*Math.PI));
		SmartDashboard.putNumber("BL", modules[2].getAngle()*360/(2*Math.PI));
		SmartDashboard.putNumber("BR", modules[3].getAngle()*360/(2*Math.PI));

	}
}