package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIntakeSubsystem;

public class StopInnerIntakeCommand extends CommandBase {
    private final InnerIntakeSubsystem innerIntakeSubsystem = InnerIntakeSubsystem.getInstance();

    public StopInnerIntakeCommand() {
        addRequirements(innerIntakeSubsystem);
    }

    @Override
    public void execute() {
        innerIntakeSubsystem.stopInnerIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
