package frc.robot.subsystems;


import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TowerConstants;

public class TowerSubsystem extends SubsystemBase {
    private final PWMTalonSRX m_leftTowerMotor = new PWMTalonSRX(TowerConstants.kLeftTowerMotorPort);
    private final PWMTalonSRX m_rightTowerMotor = new PWMTalonSRX(TowerConstants.kRightTowerMotorPort);

    /**
     * Creates a new TowerSubsystem.
     */
    public TowerSubsystem() {
        setName("Tower Subsystem");
        setSubsystem("Tower Subsystem");

        addChild("Left Tower Motor", m_leftTowerMotor);
        addChild("Right Tower Motor", m_rightTowerMotor);
    }

    public void raiseBalls() {
        m_leftTowerMotor.setSpeed(1);
        m_rightTowerMotor.setSpeed(1);
    }

    public void lowerBalls() {
        m_leftTowerMotor.setSpeed(-1);
        m_rightTowerMotor.setSpeed(-1);
    }

    public void stopBalls() {
        m_leftTowerMotor.stopMotor();
        m_rightTowerMotor.stopMotor();
    }
}