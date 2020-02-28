package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;


public class DriveCartesianCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController controller;
    private final Joystick controlBoard;

    public DriveCartesianCommand(DriveSubsystem driveSubsystem, XboxController controller, Joystick controlBoard) {
        this.driveSubsystem = driveSubsystem;
        this.controller = controller;
        this.controlBoard = controlBoard;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(
                controller.getY(GenericHID.Hand.kLeft) / 2,
                -controller.getX(GenericHID.Hand.kLeft) / 2,
                -controller.getX(GenericHID.Hand.kRight) / 2,
                controlBoard.getRawButton(2)
        );
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stopMotors();
    }
}
