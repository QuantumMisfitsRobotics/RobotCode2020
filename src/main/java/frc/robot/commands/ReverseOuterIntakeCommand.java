package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OuterIntakeSubsystem;


public class ReverseOuterIntakeCommand extends CommandBase {
    private final OuterIntakeSubsystem outerIntakeSubsystem = OuterIntakeSubsystem.getInstance();

    public ReverseOuterIntakeCommand() {
        addRequirements(outerIntakeSubsystem);
        setName("Reverse Outer Intake Command");
    }

    @Override
    public void initialize() {
        outerIntakeSubsystem.reverseOuterIntake();
    }

    @Override
    public void end(boolean interrupted) {
        outerIntakeSubsystem.stopOuterIntake();
    }
}
