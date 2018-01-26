package org.usfirst.frc.team1533.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class middleSwitch extends CommandGroup {

    public middleSwitch() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData.charAt(0) == 'R')
		{
			//Right Auto Code
	        addSequential(new DriveDiagonalTime(3, 0.25, 40));
	        
		} else {
			//Left Switch Auto Code
	        addSequential(new DriveDiagonalTime(3, 0.25, -45));
		}
    	
    }
}
