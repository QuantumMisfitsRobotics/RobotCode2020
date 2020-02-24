package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OuterIntakeSubsystem;


public class ReverseOuterIntakeCommand extends CommandBase {

    private OuterIntakeSubsystem outerIntakeSubsystem = OuterIntakeSubsystem.getInstance();

    public ReverseOuterIntakeCommand() {
        addRequirements(outerIntakeSubsystem);
        setName("Reverse Outer Intake Command");
    }

    @Override
    public void execute() {
        outerIntakeSubsystem.reverseOuterIntake();
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
