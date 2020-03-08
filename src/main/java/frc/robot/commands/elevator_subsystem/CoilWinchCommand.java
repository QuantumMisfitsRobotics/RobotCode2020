package frc.robot.commands.elevator_subsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class CoilWinchCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;

    public CoilWinchCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.setWinchSpeed(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopWinch();
    }
}
