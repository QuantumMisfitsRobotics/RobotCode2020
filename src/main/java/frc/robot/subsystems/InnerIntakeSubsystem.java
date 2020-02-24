package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class InnerIntakeSubsystem extends SubsystemBase {

    private final static InnerIntakeSubsystem INSTANCE = new InnerIntakeSubsystem();
    private final PWMVictorSPX innerIntakeMotor = new PWMVictorSPX(Constants.InnerIntakeConstants.kInnerIntakeMotorId);

    private InnerIntakeSubsystem() {
        setName("Inner Intake Subsystem");
        addChild("Inner Intake Motor Controller", innerIntakeMotor);

        RunCommand defaultCommand = new RunCommand(this::stopInnerIntake);
        defaultCommand.addRequirements(this);

        setDefaultCommand(defaultCommand);
    }


    public void stopInnerIntake() {
        innerIntakeMotor.stopMotor();
    }

    public void forward() {
        innerIntakeMotor.set(1);
    }

    public void reverse() {
        innerIntakeMotor.set(-1);
    }

    public static InnerIntakeSubsystem getInstance() {
        return INSTANCE;
    }

}

