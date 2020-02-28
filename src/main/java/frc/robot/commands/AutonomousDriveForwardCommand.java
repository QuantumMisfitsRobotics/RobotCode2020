package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class AutonomousDriveForwardCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    private Double startTime;

    public AutonomousDriveForwardCommand(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
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
        return Timer.getFPGATimestamp() > startTime + 1.5;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stopMotors();
    }
}
