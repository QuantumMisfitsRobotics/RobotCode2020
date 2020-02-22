package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();
    private final WPI_VictorSPX ballShooterController = new WPI_VictorSPX(Constants.ShooterConstants.kShooterControllerPort);

    private ShooterSubsystem() {
        setName("Shooter Subsystem");
        setDefaultCommand(new RunCommand(this::stopShooter));
    }

    public void stopShooter() {
        ballShooterController.stopMotor();
    }

    public void shootForward() {
        ballShooterController.set(1);
    }

    public void shootBackward() {
        ballShooterController.set(-1);
    }

    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }
}

