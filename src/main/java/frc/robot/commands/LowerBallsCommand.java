package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSubsystem;


public class LowerBallsCommand extends CommandBase {
    private final TowerSubsystem towerSubsystem;

    public LowerBallsCommand(TowerSubsystem towerSubsystem) {
        this.towerSubsystem = towerSubsystem;
        addRequirements(towerSubsystem);
    }


    @Override
    public void initialize() {
        towerSubsystem.lowerBalls();
    }


    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stopBalls();
    }
}
