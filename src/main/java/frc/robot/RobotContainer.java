/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    public static Joystick m_controlBoard = new Joystick(OIConstants.kDriverControlBoard);

    private final DriveSubsystem m_robotDrive = new DriveSubsystem(m_driverController);
    private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
    private final InnerIntakeSubsystem m_innerIntakeSubsystem = new InnerIntakeSubsystem();
    private final OuterIntakeSubsystem m_outerIntakeSubsystem= new OuterIntakeSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final TowerSubsystem m_towerSubsystem = new TowerSubsystem();

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    public RobotContainer() {
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setFPS(15);
        camera.setResolution(320, 480);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        // Drive at half speed when the right bumper is held
        new JoystickButton(m_driverController, Button.kBumperRight.value)
                .whenPressed(() -> m_robotDrive.setMaxOutput(0.5)) // Set the robot to half speed
                .whenReleased(() -> m_robotDrive.setMaxOutput(1)); // Set the robot to full speed

        new JoystickButton(m_driverController, Button.kY.value).toggleWhenPressed(new AutoIntakeCommand(m_innerIntakeSubsystem, m_outerIntakeSubsystem, m_towerSubsystem, m_colorSensor));

        new JoystickButton(m_driverController, Button.kBumperLeft.value).whileHeld(new ShootBall(m_shooterSubsystem)); // Shoot the ball
        //new JoystickButton(m_driverController, Button.kY.value).toggleWhenPressed(new IntakeBall(m_innerIntakeSubsystem, m_outerIntakeSubsystem));
        new JoystickButton(m_driverController, Button.kX.value)         .whileHeld(new RaiseBall(m_towerSubsystem));
        new JoystickButton(m_driverController, Button.kB.value)         .whenPressed(new RunCommand(() -> {
            m_outerIntakeSubsystem.reverseOuterIntake();
            m_innerIntakeSubsystem.reverse();
        }, m_innerIntakeSubsystem, m_outerIntakeSubsystem)).whenReleased(() -> {
            m_outerIntakeSubsystem.stopOuterIntake();
            m_innerIntakeSubsystem.stopInnerIntake();
        }, m_innerIntakeSubsystem, m_outerIntakeSubsystem);

        new JoystickButton(m_controlBoard, 4).whenPressed(new StartElevator(m_elevatorSubsystem).withTimeout(0.2).andThen(new StopElevator(m_elevatorSubsystem)));
    }
}