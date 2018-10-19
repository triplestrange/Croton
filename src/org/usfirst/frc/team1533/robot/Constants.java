package org.usfirst.frc.team1533.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	//Acceleration and vCruise for the Swerve Motion Profiles
	public static double acc = 50;
	public static double vCruise = 60;
	public static double AutonDelay = SmartDashboard.getNumber("AutonDelay", 0);
	public static class SwerveDrive {
		// These numbers control the Swerve PID
		public static final double SWERVE_STEER_P = 2;
		public static final double SWERVE_STEER_I = 0;
		public static final double SWERVE_STEER_D = 0;
		public static final double SWERVE_STEER_CAP = 1;
		
		// Port which the drive motors are plugged into
		public static final int BR_DRIVE = 1;
		public static final int FR_DRIVE = 2;
		public static final int FL_DRIVE = 3;
		public static final int BL_DRIVE = 4;

		// Port which the steering motors are plugged into
		public static final int FL_STEER = 11;
		public static final int FR_STEER = 2;
		public static final int BL_STEER = 12;
		public static final int BR_STEER = 1;

		// Port which the steering encoders are plugged into
		public static final int FL_ENCODER = 2;
		public static final int FR_ENCODER = 1;
		public static final int BR_ENCODER = 0;
		public static final int BL_ENCODER = 3;

		// Offset of the encoders to correct the orientation of installation
		// Encoder offsets for Croton
		public final static double FL_ENC_OFFSET = 41;
		public final static double FR_ENC_OFFSET = 315;
		public final static double BL_ENC_OFFSET = 260;
		public final static double BR_ENC_OFFSET = 316;
		
		//Encoder offsets for Milo
//		public final static double FL_ENC_OFFSET = 73;
//		public final static double FR_ENC_OFFSET = 324;
//		public final static double BL_ENC_OFFSET = 190;
//		public final static double BR_ENC_OFFSET = 294.5;
		
		// Whether or not the encoders are reversed
		public static final boolean ENCODERS_REVERSED = false;

		//Cad Wheel Base information for Croton
		public static final double WHEEL_BASE_WIDTH = 21.25;
		public static final double WHEEL_BASE_LENGTH = 26.25;
	}

	public static class DirectInput {

		// This class assigns names to numbers which correspond to Controller inputs

		// Controller 1
		// square
		public final static int X = 1;
		// x
		public final static int A = 2;
		// circle
		public final static int B = 3;
		// triangle
		public final static int Y = 4;
		// Others are self-explanatory
		public final static int LEFT_BUMPER = 5;
		public final static int RIGHT_BUMPER = 6;
		public final static int LEFT_TRIGGER = 7;
		public final static int RIGHT_TRIGGER = 8;
		public final static int BACK = 9;
		public final static int START = 10;
		public final static int JOYL_PRESS = 11;
		public final static int JOYR_PRESS = 12;
	}
	
	public static class Xinput {

		// This class assigns names to numbers which correspond to Controller inputs

		// Controller 1
		// square
		public final static int A = 1;
		// x
		public final static int B = 2;
		// circle
		public final static int X = 3;
		// triangle
		public final static int Y = 4;
		// Others are self-explanatory
		public final static int LEFT_BUMPER = 5;
		public final static int RIGHT_BUMPER = 6;
		public final static int BACK = 7;
		public final static int START = 8;
		public final static int JOYL_PRESS = 9;
		public final static int JOYR_PRESS = 10;
		
		public final static int LX_AXIS = 0;
		public final static int LY_AXIS = 1;
		public final static int RX_AXIS = 4;
		public final static int RY_AXIS = 5;
		
		public final static int LEFT_TRIGGER = 2;
		public final static int RIGHT_TRIGGER = 3;
	}

	public static class Elevator {
		public static final int cim1 = 8;
		public static final int cim2 = 9;
		public static final int cim3 = 10;
		
		public static final int mag1 = 0;
		public static final int mag2 = 1;
	}
	public static class CubeMech {
		public static final int intakeL = 3;
		public static final int intakeR = 4;
	}
	public static class Pneumatics {
		public static final int sol1_1 = 0;
		public static final int sol1_2 = 1;
		public static final int sol2_1 = 2;
		public static final int sol2_2 = 3;
		public static final int sol3_1 = 6;
		public static final int sol3_2 = 7;
	}
}