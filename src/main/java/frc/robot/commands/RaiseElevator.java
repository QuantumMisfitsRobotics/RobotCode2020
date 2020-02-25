package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class RaiseElevator extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;

    public RaiseElevator(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.elevatorUp();
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopElevator();
    }
}
