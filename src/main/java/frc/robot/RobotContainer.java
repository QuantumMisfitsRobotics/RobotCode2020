/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    // Inputs
    public XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    public Joystick m_controlBoard = new Joystick(OIConstants.kDriverControlBoard);

    // Subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem(m_driverController, m_controlBoard);
    private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
    private final InnerIntakeSubsystem m_innerIntakeSubsystem = new InnerIntakeSubsystem();
    private final OuterIntakeSubsystem m_outerIntakeSubsystem= new OuterIntakeSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final TowerSubsystem m_towerSubsystem = new TowerSubsystem();
    private final CameraSubsystem m_cameraSubsystem = new CameraSubsystem();

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    private final AutonomousDriveForwardCommand autoDrive = new AutonomousDriveForwardCommand(m_robotDrive);

    public RobotContainer() {
        configureButtonBindings();
        m_robotDrive.setDefaultCommand(new DriveCartesianCommand(m_robotDrive, m_driverController, m_controlBoard));
    }

    public void periodic() {
        m_robotDrive.checkDrive();
    }

    public PigeonIMU getPigeon() {
        return m_robotDrive.pigeonIMU;
    }

    private void configureButtonBindings() {

        // Raise the ball when the right bumper is pressed and stop raising when pressed again
        new JoystickButton(m_driverController, Button.kX.value).toggleWhenPressed(new ShooterBallCommand(m_shooterSubsystem)); // Shoot the ball
        new JoystickButton(m_driverController, Button.kBumperLeft.value).toggleWhenPressed(new AutoIntakeCommand(m_innerIntakeSubsystem, m_outerIntakeSubsystem, m_towerSubsystem, m_colorSensor));
        new JoystickButton(m_driverController, Button.kY.value).whileHeld(new RaiseBallCommand(m_towerSubsystem));
        new JoystickButton(m_driverController, Button.kBumperRight.value).toggleWhenPressed(new AutoShootCommand(m_shooterSubsystem, m_towerSubsystem));
        new JoystickButton(m_driverController, Button.kB.value).whileHeld(new ManualIntakeCommand(m_outerIntakeSubsystem, m_innerIntakeSubsystem));

        new JoystickButton(m_driverController, Button.kBack.value).whileHeld(new CoilWinch(m_elevatorSubsystem));
        new JoystickButton(m_driverController, Button.kStart.value).whileHeld(new UncoilWinchCommand(m_elevatorSubsystem));

//        new JoystickButton(m_controlBoard, 4).whileHeld(new RaiseElevatorCommand(m_elevatorSubsystem, m_controlBoard));
//        new JoystickButton(m_controlBoard, 5).whileHeld(new LowerElevatorCommand(m_elevatorSubsystem));
//        new JoystickButton(m_controlBoard, 6).whileHeld(new CoilWinch(m_elevatorSubsystem));
//        new JoystickButton(m_controlBoard, 7).whileHeld(new RunCommand(m_elevatorSubsystem::uncoilWinch, m_elevatorSubsystem));
        new JoystickButton(m_controlBoard, 1).whenPressed(new UseDriveCameraCommand(m_cameraSubsystem)).whenReleased(new UseShooterCameraCommand(m_cameraSubsystem));
    }

    public AutonomousDriveForwardCommand getAutoCommand() {
        return autoDrive;
    }
}