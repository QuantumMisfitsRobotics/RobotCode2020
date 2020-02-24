package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OuterIntakeSubsystem;


public class StartOuterIntakeCommand extends CommandBase {
    private final OuterIntakeSubsystem outerIntakeSubsystem = OuterIntakeSubsystem.getInstance();

    public StartOuterIntakeCommand() {
        addRequirements(outerIntakeSubsystem);
        setName("Start Outer Intake Command");
    }

    @Override
    public void execute() {
        outerIntakeSubsystem.activateOuterIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        outerIntakeSubsystem.stop();
    }
}
