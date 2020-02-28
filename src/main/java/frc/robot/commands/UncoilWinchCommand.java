package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class UncoilWinchCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;

    public UncoilWinchCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.setWinchSpeed(-0.5);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopWinch();
    }
}
