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
    public void initialize() {
        shooterSubsystem.shootForward();
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopShooter();
    }
}
