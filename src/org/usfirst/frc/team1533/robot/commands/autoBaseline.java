package org.usfirst.frc.team1533.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoBaseline extends CommandGroup {

    public autoBaseline() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData.charAt(0) == 'L')
		{
			//Left Auto Code
	        addSequential(new DriveForwardTime(3, 0.25));
	        
		} else {
			//Right Switch Auto Code
	        addSequential(new DriveForwardTime(3, 0.25));
		}
    }
}