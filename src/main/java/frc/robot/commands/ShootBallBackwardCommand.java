package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootBallBackwardCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();

    public ShootBallBackwardCommand() {
        addRequirements(shooterSubsystem);
        setName("Shoot Ball Command");
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootBackward();
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopShooter();
    }
}
