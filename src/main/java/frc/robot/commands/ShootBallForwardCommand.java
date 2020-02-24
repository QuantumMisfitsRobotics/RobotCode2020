package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootBallForwardCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();

    public ShootBallForwardCommand() {
        addRequirements(shooterSubsystem);
        setName("Shoot Ball Command");
    }

    @Override
    public void execute() {
        shooterSubsystem.shootForward();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopShooter();
    }
}
