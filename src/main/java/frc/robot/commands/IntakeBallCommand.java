package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIntakeSubsystem;
import frc.robot.subsystems.OuterIntakeSubsystem;


public class IntakeBallCommand extends CommandBase {
    private final InnerIntakeSubsystem innerIntakeSubsystem;
    private final OuterIntakeSubsystem outerIntakeSubsystem;
    private double m_timeout;

    public IntakeBallCommand(InnerIntakeSubsystem innerIntakeSubsystem, OuterIntakeSubsystem outerIntakeSubsystem) {
        this.innerIntakeSubsystem = innerIntakeSubsystem;
        this.outerIntakeSubsystem = outerIntakeSubsystem;
        addRequirements(innerIntakeSubsystem, outerIntakeSubsystem);
    }

    @Override
    public void execute() {
        innerIntakeSubsystem.startInnerIntake();
        outerIntakeSubsystem.startOuterIntake();
    }

    @Override
    public void end(boolean interrupted) {
        innerIntakeSubsystem.stopInnerIntake();
        outerIntakeSubsystem.stopOuterIntake();
    }
}
