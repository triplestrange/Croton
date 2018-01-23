package org.usfirst.frc.team1533.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoBaseline extends CommandGroup {

    public autoBaseline() {
        addSequential(new DriveForwardTime(3, 0.25));
    }
}