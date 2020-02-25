package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class StopElevator extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;

    public StopElevator(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.stopElevator();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
