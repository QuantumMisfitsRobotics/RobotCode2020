package frc.robot.subsystems;


import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    public enum ShooterMode {
        VictorSpx,
        TalonSrx,
    }

    private final ShooterMode shooterMode = ShooterMode.TalonSrx;
    private final PWMVictorSPX victorMotor;
    private final PWMTalonSRX talonMotor;


    public ShooterSubsystem(ShooterMode mode) {
        if (mode == ShooterMode.TalonSrx) {
            talonMotor = new PWMTalonSRX(Constants.ShooterConstants.kShooterControllerPort);
            victorMotor = null;
            addChild("Shoot Ball", talonMotor);
        } else {
            talonMotor = null;
            victorMotor = new PWMVictorSPX(Constants.ShooterConstants.kShooterControllerPort);
            addChild("Shoot Ball", victorMotor);
        }

        setName("Shooter Subsystem");
    }

    public void shoot() {
        if (talonMotor != null) talonMotor.set(1);
        else victorMotor.set(1);
    }

    public void stopShooter() {
        if (talonMotor != null) talonMotor.stopMotor();
        else victorMotor.stopMotor();
    }

}

