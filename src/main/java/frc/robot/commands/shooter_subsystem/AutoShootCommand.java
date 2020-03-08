package frc.robot.commands.shooter_subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TowerSubsystem;

public class AutoShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final TowerSubsystem towerSubsystem;

    private Double startTime;

    public AutoShootCommand(ShooterSubsystem shooterSubsystem, TowerSubsystem towerSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.towerSubsystem = towerSubsystem;
        addRequirements(shooterSubsystem, towerSubsystem);
    }

    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
        shooterSubsystem.shootForward();
    }

    @Override
    public void execute() {
        double shooterRevTime = 1.0;
        if (startTime + shooterRevTime < Timer.getFPGATimestamp()) {
            towerSubsystem.raiseBalls();
        }
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stopBalls();
        shooterSubsystem.stopShooter();
        Robot.numBallsLoadedTower = 0;
    }
}