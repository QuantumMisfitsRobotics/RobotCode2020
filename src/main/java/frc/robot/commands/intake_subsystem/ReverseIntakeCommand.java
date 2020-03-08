package frc.robot.commands.intake_subsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIntakeSubsystem;
import frc.robot.subsystems.OuterIntakeSubsystem;


public class ReverseIntakeCommand extends CommandBase {
    private OuterIntakeSubsystem outerIntakeSubsystem;

    public ReverseIntakeCommand(OuterIntakeSubsystem m_outerIntakeSubsystem) {
        outerIntakeSubsystem = m_outerIntakeSubsystem;
        addRequirements(outerIntakeSubsystem);
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
