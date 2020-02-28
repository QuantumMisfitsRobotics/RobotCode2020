package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIntakeSubsystem;
import frc.robot.subsystems.OuterIntakeSubsystem;

public class ManualIntakeCommand extends CommandBase {
    private final OuterIntakeSubsystem outerIntakeSubsystem;
    private final InnerIntakeSubsystem innerIntakeSubsystem;

    public ManualIntakeCommand(OuterIntakeSubsystem outerIntakeSubsystem, InnerIntakeSubsystem innerIntakeSubsystem) {
        this.outerIntakeSubsystem = outerIntakeSubsystem;
        this.innerIntakeSubsystem = innerIntakeSubsystem;
        addRequirements(this.outerIntakeSubsystem, this.innerIntakeSubsystem);
    }


    @Override
    public void initialize() {
        outerIntakeSubsystem.startOuterIntake();
        innerIntakeSubsystem.startInnerIntake();
    }

    @Override
    public void end(boolean interrupted) {
        outerIntakeSubsystem.stopOuterIntake();
        innerIntakeSubsystem.stopInnerIntake();
    }
}
