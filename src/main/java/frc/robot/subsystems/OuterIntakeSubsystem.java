package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.OuterIntakeConstants;

public class OuterIntakeSubsystem extends SubsystemBase {

    private final WPI_VictorSPX m_outerIntakeController = new WPI_VictorSPX(OuterIntakeConstants.OuterIntakeControllerId);

    public OuterIntakeSubsystem() {
        setName("Outer Intake Subsystem");
        setSubsystem(getName());
        addChild("Outer Intake Controller", m_outerIntakeController);
    }

    public void startOuterIntake() {
        m_outerIntakeController.set(0.5);
    }

    public void stopOuterIntake() {
        m_outerIntakeController.stopMotor();
    }

    public void reverseOuterIntake() {
        m_outerIntakeController.set(-0.5);
    }
}

