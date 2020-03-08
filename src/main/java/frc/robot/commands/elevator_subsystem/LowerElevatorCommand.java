package frc.robot.commands.elevator_subsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;


public class LowerElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;

    public LowerElevatorCommand(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(this.elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.setElevatorSpeed(-0.3);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.stopElevator();
    }
}
