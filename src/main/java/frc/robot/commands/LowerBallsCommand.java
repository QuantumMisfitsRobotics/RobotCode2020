package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSubsystem;

public class LowerBallsCommand extends CommandBase {
    private final TowerSubsystem towerSubsystem = TowerSubsystem.getInstance();

    public LowerBallsCommand() {
        addRequirements(towerSubsystem);
        setName("Lower Balls Command");
    }

    @Override
    public void initialize() {
        System.out.println(this.getName() + " Initialized");
        towerSubsystem.lowerBalls();
    }

    @Override
    public void execute() {

    }

//    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stopBalls();
    }
}
