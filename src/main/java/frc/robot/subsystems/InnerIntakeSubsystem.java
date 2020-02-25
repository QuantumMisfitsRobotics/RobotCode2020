package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class InnerIntakeSubsystem extends SubsystemBase {

    private final WPI_VictorSPX m_innerIntakeMotor = new WPI_VictorSPX(Constants.InnerIntakeConstants.kInnerIntakeMotorId);

    public InnerIntakeSubsystem() {
        setName("Inner Intake Subsystem");
        setSubsystem(getName());
        addChild("Inner Intake Motor Controller", m_innerIntakeMotor);
    }


    public void stopInnerIntake() {
        m_innerIntakeMotor.stopMotor();
    }

    public void startInnerIntake() {
        m_innerIntakeMotor.set(0.75);
    }

    public void reverse() {
        m_innerIntakeMotor.set(-0.5);
    }

    public boolean isNotRunning() {
        return m_innerIntakeMotor.get() == 0;
    }
}

