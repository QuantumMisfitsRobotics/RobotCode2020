package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootBall extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShootBall(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }


    @Override
    public void execute() {
        shooterSubsystem.shootForward();
        Robot.numBallsLoadedTower = 0;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopShooter();
    }
}
