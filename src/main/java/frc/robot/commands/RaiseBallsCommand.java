package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSubsystem;


public class RaiseBallsCommand extends CommandBase {
    private final TowerSubsystem towerSubsystem = TowerSubsystem.getInstance();

    public RaiseBallsCommand() {
        addRequirements(towerSubsystem);
        setName("Lower Balls Command");
    }

    @Override
    public void initialize() {
        System.out.println(getName() + " Initialized");
        towerSubsystem.raiseBalls();
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stopBalls();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
