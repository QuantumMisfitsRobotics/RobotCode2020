package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class StartElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;

    public StartElevatorCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.elevatorUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
