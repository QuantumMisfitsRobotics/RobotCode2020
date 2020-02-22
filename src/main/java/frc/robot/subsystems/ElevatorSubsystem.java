package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

    private final static ElevatorSubsystem INSTANCE = new ElevatorSubsystem();

    private final WPI_VictorSPX elevatorMotorController = new WPI_VictorSPX(Constants.ElevatorConstants.kElevatorControllerId);
    private final WPI_VictorSPX elevatorWinch = new WPI_VictorSPX(Constants.ElevatorConstants.kElevatorWinchId);

    private ElevatorSubsystem() {
        addChild("Elevator Motor Controller", elevatorMotorController);
        addChild("Elevator Winch", elevatorWinch);
        setName("Elevator Subsystem");

        setDefaultCommand(new RunCommand(this::stopElevator));
    }

    public void raiseElevator() {
        elevatorMotorController.set(1);
    }

    public void lowerElevator() {
        elevatorMotorController.set(-1);
    }

    public void raiseWinch() {
        elevatorWinch.set(1);
    }

    public void lowerWinch() {
        elevatorWinch.set(-1);
    }

    public void stopElevator() {
        elevatorMotorController.stopMotor();
    }

    public void stopWinch() {
        elevatorWinch.stopMotor();
    }

    public void stopControllers() {
        stopElevator();
        stopWinch();
    }

    public static ElevatorSubsystem getInstance() {
        return INSTANCE;
    }
}

