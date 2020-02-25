package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;


public class DriveCartesian extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController controller;

    public DriveCartesian(DriveSubsystem driveSubsystem, XboxController controller) {
        this.driveSubsystem = driveSubsystem;
        this.controller = controller;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(
                controller.getY(GenericHID.Hand.kLeft) / 2,
                -controller.getX(GenericHID.Hand.kLeft) / 2,
                -controller.getX(GenericHID.Hand.kRight) / 2,
                false
        );
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stopMotors();
    }
}
