package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIntakeSubsystem;


public class StartIntakeCommand extends CommandBase {
    private final InnerIntakeSubsystem innerIntakeSubsystem = InnerIntakeSubsystem.getInstance();

    public StartIntakeCommand() {
        addRequirements(innerIntakeSubsystem);
    }

    @Override
    public void execute() {
        innerIntakeSubsystem.forward();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        innerIntakeSubsystem.stopInnerIntake();
    }
}
