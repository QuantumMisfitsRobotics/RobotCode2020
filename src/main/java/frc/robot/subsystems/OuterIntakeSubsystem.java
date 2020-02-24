package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OuterIntakeSubsystem extends SubsystemBase {
    private final static OuterIntakeSubsystem INSTANCE = new OuterIntakeSubsystem();

    private final WPI_VictorSPX m_outerIntakeController = new WPI_VictorSPX(Constants.OuterIntakeConstants.OuterIntakeControllerId);

    private OuterIntakeSubsystem() {
        setName("Outer Intake Subsystem");

        RunCommand defaultCommand = new RunCommand(this::stop);
        defaultCommand.addRequirements(this);
        setDefaultCommand(defaultCommand);
    }

    public void activateOuterIntake() {
        m_outerIntakeController.set(1);
    }

    public void stop() {
        m_outerIntakeController.stopMotor();
    }

    public static OuterIntakeSubsystem getInstance() { return INSTANCE; }

    public void reverseOuterIntake() {
        m_outerIntakeController.set(-1);
    }
}

