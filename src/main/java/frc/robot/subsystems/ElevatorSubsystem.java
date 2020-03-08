package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

@Deprecated
public class ElevatorSubsystem extends SubsystemBase {

    private final WPI_VictorSPX elevatorController = new WPI_VictorSPX(Constants.ElevatorConstants.kElevatorControllerId);
    private final WPI_VictorSPX winchController = new WPI_VictorSPX(Constants.ElevatorConstants.kElevatorWinchId);

    public ElevatorSubsystem() {
        winchController.setInverted(true);
    }

    public void winchUp() {
        winchController.set(1);
    }

    public void setWinchSpeed(double speed) {
        winchController.set(speed);
    }

    public void uncoilWinch() {
        winchController.set(-1.0);
    }

    public void coilWinch() {
        winchController.set(1);
    }

    public void stopWinch() {
        winchController.stopMotor();
    }


    public void elevatorUp() {
        elevatorController.set(0.5);
    }

    public void setElevatorSpeed(double value) {
        elevatorController.set(value);
    }

    public void stopElevator() {
        elevatorController.stopMotor();
    }
}

