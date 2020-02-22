package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class RaiseElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem = ElevatorSubsystem.getInstance();

    public RaiseElevatorCommand() {
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        elevatorSubsystem.raiseElevator();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopElevator();
    }
}
