package frc.robot.subsystems;


import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final PWMVictorSPX m_ballShooterCommand = new PWMVictorSPX(Constants.ShooterConstants.kShooterControllerPort);


    public ShooterSubsystem() {


        setName("Shooter Subsystem");
    }

    public void shoot() {
        m_ballShooterCommand.set(1);
    }

    public void stopShooter() {
        m_ballShooterCommand.stopMotor();
    }

}

