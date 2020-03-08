package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheelSubsystem extends SubsystemBase {
    private final WPI_VictorSPX colorController = new WPI_VictorSPX(Constants.ColorWheelConstants.controllerId);

    public void stopColorWheel() {
        colorController.stopMotor();
    }

    public void turnColorWheel() {
        colorController.set(0.25);
    }
}

