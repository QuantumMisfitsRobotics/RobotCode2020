package frc.robot.commands;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.InnerIntakeSubsystem;
import frc.robot.subsystems.OuterIntakeSubsystem;
import frc.robot.subsystems.TowerSubsystem;


public class AutoIntakeCommand extends CommandBase {
    private final InnerIntakeSubsystem innerIntakeSubsystem;
    private final OuterIntakeSubsystem outerIntakeSubsystem;
    private final TowerSubsystem towerSubsystem;
    private final ColorSensorV3 colorSensorV3;
    private int proximity;
    private boolean incrementedBallCounter = false;

    public AutoIntakeCommand(InnerIntakeSubsystem innerIntakeSubsystem, OuterIntakeSubsystem outerIntakeSubsystem, TowerSubsystem towerSubsystem, ColorSensorV3 colorSensorV3) {
        this.innerIntakeSubsystem = innerIntakeSubsystem;
        this.outerIntakeSubsystem = outerIntakeSubsystem;
        this.towerSubsystem = towerSubsystem;
        this.colorSensorV3 = colorSensorV3;
        addRequirements(innerIntakeSubsystem, outerIntakeSubsystem, towerSubsystem);
    }

    @Override
    public void initialize() {
        outerIntakeSubsystem.startOuterIntake();
        innerIntakeSubsystem.startInnerIntake();
    }

    @Override
    public void execute() {
        proximity = colorSensorV3.getProximity();
        if (proximity >= 110 && Robot.numBallsLoadedTower < 3) {
            towerSubsystem.raiseBalls();
            if (!incrementedBallCounter) {
                Robot.numBallsLoadedTower++;
                incrementedBallCounter = true;
            }
        } else {
            towerSubsystem.stopBalls();
            incrementedBallCounter = false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        innerIntakeSubsystem.stopInnerIntake();
        outerIntakeSubsystem.stopOuterIntake();
        towerSubsystem.stopBalls();
    }
}