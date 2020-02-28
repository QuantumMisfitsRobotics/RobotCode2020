package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CameraSubsystem;


public class UseShooterCameraCommand extends CommandBase {
    private final CameraSubsystem cameraSubsystem;

    public UseShooterCameraCommand(CameraSubsystem cameraSubsystem) {
        this.cameraSubsystem = cameraSubsystem;
        addRequirements(this.cameraSubsystem);
    }

    @Override
    public void initialize() {
        cameraSubsystem.useShooterCamera();
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}