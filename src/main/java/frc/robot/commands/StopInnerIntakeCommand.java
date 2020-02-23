package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.InnerIntakeSubsystem;

public class StopInnerIntakeCommand extends InstantCommand {
    private final InnerIntakeSubsystem innerIntakeSubsystem = InnerIntakeSubsystem.getInstance();

    public StopInnerIntakeCommand() {
        addRequirements(innerIntakeSubsystem);
        setName("Stop Inner Intake Command");

    }

    @Override
    public void initialize() {
        innerIntakeSubsystem.stopInnerIntake();
    }
}
