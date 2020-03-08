package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.shooter_subsystem.AutoShootCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TowerSubsystem;


public class AutonomousShooterCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final TowerSubsystem towerSubsystem;

    private AutoShootCommand autoShootCommand;
    private Double startTime;
    private double startAngle;

    public AutonomousShooterCommand(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, TowerSubsystem towerSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.towerSubsystem = towerSubsystem;
        autoShootCommand = new AutoShootCommand(shooterSubsystem, towerSubsystem);
        addRequirements(this.driveSubsystem, this.shooterSubsystem, this.towerSubsystem);
    }

    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
        driveSubsystem.zeroHeading();
        startAngle = driveSubsystem.getRawHeading();
        var cmd = new RunCommand(shooterSubsystem::shootForward, shooterSubsystem);
        cmd.withTimeout(5).schedule();
    }

    @Override
    public void execute() {
        double currentTime = Timer.getFPGATimestamp();
        if (startTime + 3 < currentTime && startTime + 7 > currentTime) {
            towerSubsystem.raiseBalls();
        }

        if (startTime + 7 < currentTime) {
            driveSubsystem.drive(0.5, 0, 0, false);
        }

        if (startTime + 8 < currentTime) {
            end(false);
            cancel();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stopBalls();
        shooterSubsystem.stopShooter();
        driveSubsystem.stopMotors();
    }
}
