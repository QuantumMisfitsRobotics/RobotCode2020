package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TowerSubsystem;


public class AutoShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final TowerSubsystem towerSubsystem;

    public AutoShootCommand(ShooterSubsystem shooterSubsystem, TowerSubsystem towerSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.towerSubsystem = towerSubsystem;
        addRequirements(shooterSubsystem, towerSubsystem);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
