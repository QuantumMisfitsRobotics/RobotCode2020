package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.OuterIntakeConstants;

public class OuterIntakeSubsystem extends SubsystemBase {

    private static final OuterIntakeSubsystem INSTANCE = new OuterIntakeSubsystem();

    private final WPI_VictorSPX outerIntakeController = new WPI_VictorSPX(OuterIntakeConstants.kOuterIntakeControllerId);

    private OuterIntakeSubsystem() {
        setName("Outer Intake Subsystem");
        addChild("Outer Intake Controller", outerIntakeController);

        InstantCommand defaultCommand = new InstantCommand(this::stopOuterIntake);
        defaultCommand.addRequirements(this);
        setDefaultCommand(defaultCommand);
    }

    public void stopOuterIntake() {
        outerIntakeController.stopMotor();
    }

    public void startOuterIntake() {
        outerIntakeController.set(1);
    }

    public void reverseOuterIntake() {
        outerIntakeController.set(-1);
    }

    public static OuterIntakeSubsystem getInstance() {
        return INSTANCE;
    }
}

