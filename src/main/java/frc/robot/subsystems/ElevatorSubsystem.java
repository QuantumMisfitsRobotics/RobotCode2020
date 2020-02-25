package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

    private final WPI_VictorSPX elevatorController = new WPI_VictorSPX(Constants.ElevatorConstants.kElevatorControllerId);

    public ElevatorSubsystem() {

    }


    public void elevatorUp() {
        elevatorController.set(0.5);
    }

    public void elevatorDown() {
        elevatorController.set(-0.5);
    }

    public void stopElevator() {
        elevatorController.stopMotor();
    }
}

