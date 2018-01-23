package org.usfirst.frc.team1533.robot;

public class Constants {

    public static class SwerveDrive {
        public static final double SWERVE_STEER_P = 0;
        public static final double SWERVE_STEER_I = 0;
        public static final double SWERVE_STEER_D = 0;
        public static final double SWERVE_STEER_CAP = 1;
        
//Note to self: These numbers for swerve are only for use with Svarog
        public static final int FR_DRIVE = 0;
        public static final int BR_DRIVE = 2;
        public static final int FL_DRIVE = 3;
        public static final int BL_DRIVE = 1;

        public static final int FR_STEER = 6;
        public static final int BR_STEER = 8;
        public static final int FL_STEER = 9;
        public static final int BL_STEER = 7;

        public static final int FR_ENCODER = 0;
        public static final int BR_ENCODER = 1;
        public static final int FL_ENCODER = 2;
        public static final int BL_ENCODER = 3;
        
        public final static double FL_ENC_OFFSET = -187+360-287;
    	public final static double FR_ENC_OFFSET = -248+360;
    	public final static double BL_ENC_OFFSET = -229+360;
    	public final static double BR_ENC_OFFSET = -250+360;
        public static final boolean ENCODERS_REVERSED = false;

        public static final double WHEEL_BASE_WIDTH = 22.5;
        public static final double WHEEL_BASE_LENGTH = 26.5;
        //End Svarog Numbers
        
        //public static final double WHEEL_BASE_WIDTH = 21.25;
        //public static final double WHEEL_BASE_LENGTH = 26.25;
    }
    public static class Joystick {
    	
    	//ButtonMap
    	//square
    	public final static int X = 1;
    	//ecks
    	public final static int A = 2;
    	//circle
    	public final static int B = 3;
    	//triangle
    	public final static int Y = 4;
    	public final static int LEFT_BUMPER = 5;
    	public final static int RIGHT_BUMPER = 6;
    	public final static int LEFT_TRIGGER = 7;
    	public final static int RIGHT_TRIGGER = 8;
    	public final static int BACK = 9;
    	public final static int START = 10;
    	
    	
    	public final static int X2 = 1;
    	//ecks
    	public final static int A2 = 2;
    	//circle
    	public final static int B2 = 3;
    	//triangle
    	public final static int Y2 = 4;
    	public final static int LEFT_BUMPER2 = 5;
    	public final static int RIGHT_BUMPER2 = 6;
    	public final static int LEFT_TRIGGER2 = 7;
    	public final static int RIGHT_TRIGGER2 = 8;
    }

}