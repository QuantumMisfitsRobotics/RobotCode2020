package frc.robot.commands.color_wheel_subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class RotateControlPanel extends CommandBase {
    private final ColorWheelSubsystem cs;

    public RotateControlPanel(ColorWheelSubsystem m_colorWheelSubsystem) {
        this.cs = m_colorWheelSubsystem;
        addRequirements(cs);
    }

    @Override
    public void initialize() {
        cs.turnColorWheel();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        cs.stopColorWheel();
    }
}
