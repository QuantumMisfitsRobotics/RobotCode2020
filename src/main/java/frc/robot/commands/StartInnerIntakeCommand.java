package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIntakeSubsystem;

public class StartInnerIntakeCommand extends CommandBase {
    private final InnerIntakeSubsystem innerIntakeSubsystem = InnerIntakeSubsystem.getInstance();

    public StartInnerIntakeCommand() {
        addRequirements(innerIntakeSubsystem);
        setName("Start Inner Intake Command");
    }

    @Override
    public void initialize() {
        innerIntakeSubsystem.forward();
    }


    @Override
    public void end(boolean interrupted) {
        innerIntakeSubsystem.stopInnerIntake();
    }
}
