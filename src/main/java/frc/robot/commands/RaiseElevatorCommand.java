package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class RaiseElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;
    private final Joystick controlBoard;

    public RaiseElevatorCommand(ElevatorSubsystem elevatorSubsystem, Joystick controlBoard) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.controlBoard = controlBoard;
    }

    @Override
    public void execute() {
        elevatorSubsystem.elevatorUp();
        if (controlBoard.getRawButton(3)) {
            elevatorSubsystem.setWinchSpeed(-1);
        } else elevatorSubsystem.stopWinch();
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopElevator();
        elevatorSubsystem.stopWinch();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}