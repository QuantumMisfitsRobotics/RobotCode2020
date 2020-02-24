package frc.robot.subsystems;


import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TowerConstants;

public class TowerSubsystem extends SubsystemBase {

    private final PWMTalonSRX m_leftTowerMotor = new PWMTalonSRX(TowerConstants.kLeftTowerMotorPort);
    private final PWMTalonSRX m_rightTowerMotor = new PWMTalonSRX(TowerConstants.kRightTowerMotorPort);
    private final SpeedControllerGroup m_towerGroup = new SpeedControllerGroup(m_leftTowerMotor, m_rightTowerMotor);

    private static final TowerSubsystem INSTANCE = new TowerSubsystem();

    /**
     * Creates a new TowerSubsystem.
     */
    private TowerSubsystem() {
        setName("Tower Subsystem");
        setSubsystem("Tower Subsystem");

        addChild("Left Tower Motor", m_leftTowerMotor);
        addChild("Right Tower Motor", m_rightTowerMotor);

        RunCommand defaultCommand = new RunCommand(this::stopBalls);
        defaultCommand.addRequirements(this);
        setDefaultCommand(defaultCommand);
    }

    public void raiseBalls() {
        m_towerGroup.set(1);
    }

    public void lowerBalls() {
        m_towerGroup.set(-1);

    }

    public void stopBalls() {
        m_towerGroup.stopMotor();
    }

    public static TowerSubsystem getInstance() {
        return INSTANCE;
    }
}