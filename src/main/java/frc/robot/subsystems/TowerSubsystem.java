package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TowerConstants;

public class TowerSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_leftTowerMotor = new WPI_TalonSRX(TowerConstants.kLeftTowerMotorPort);
    private final WPI_TalonSRX m_rightTowerMotor = new WPI_TalonSRX(TowerConstants.kRightTowerMotorPort);
    private final SpeedControllerGroup m_towerGroup = new SpeedControllerGroup(m_leftTowerMotor, m_rightTowerMotor);

    public TowerSubsystem() {
        setName("Tower Subsystem");
        setSubsystem("Tower Subsystem");
        addChild("Left Tower Motor", m_leftTowerMotor);
        addChild("Right Tower Motor", m_rightTowerMotor);
        addChild("Tower Controller Group", m_towerGroup);
    }

    public void raiseBalls() { m_towerGroup.set(.25); }

    public void intakeRaiseBalls() {
        m_towerGroup.set(0.2);
    }

    public void lowerBalls() {
        m_towerGroup.set(-.75);
    }

    public void stopBalls() {
        m_towerGroup.stopMotor();
    }

    public boolean towerIsRunning() {
        return m_towerGroup.get() != 0;
    }

    public void setTowerSpeed(double v) {
        m_towerGroup.set(v);
    }
}