package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class AutonomousDriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    private Double startTime;

    public AutonomousDriveCommand(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(this.driveSubsystem);
    }

    @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        driveSubsystem.drive(
                -1,
                0,
                0,
                false
        );
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() > startTime + 0.5;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stopMotors();
    }
}
