package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final WPI_VictorSPX m_ballShooterController = new WPI_VictorSPX(Constants.ShooterConstants.kShooterControllerPort);

    public ShooterSubsystem() {
        setName("Shooter Subsystem");
        setSubsystem(getName());
        m_ballShooterController.setInverted(true);
        addChild("Ball Shooter Controller", m_ballShooterController);
    }

    public void stopShooter() {
        m_ballShooterController.stopMotor();
    }

    public void shootForward() {
        m_ballShooterController.set(1);
    }

    public void shootBackward() {
        m_ballShooterController.set(-1);
    }

    public boolean getShooterRunning() {
        return m_ballShooterController.get() != 0;
    }
}

