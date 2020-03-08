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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.autonomous.AutonomousDriveCommand;
import frc.robot.commands.autonomous.AutonomousShooterCommand;
import frc.robot.commands.camera_subsystem.UseDriveCameraCommand;
import frc.robot.commands.camera_subsystem.UseShooterCameraCommand;
import frc.robot.commands.color_wheel_subsystem.RotateControlPanel;
import frc.robot.commands.drive_subsystem.DriveCartesianCommand;
import frc.robot.commands.intake_subsystem.AutomatedIntakeCommand;
import frc.robot.commands.intake_subsystem.ForwardIntakeCommand;
import frc.robot.commands.intake_subsystem.ReverseIntakeCommand;
import frc.robot.commands.shooter_subsystem.AutoShootCommand;
import frc.robot.commands.shooter_subsystem.ShootBallCommand;
import frc.robot.commands.tower_subsystem.RaiseBallCommand;
import frc.robot.subsystems.*;

public class RobotContainer {

    // Inputs
    public XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    public Joystick m_controlBoard = new Joystick(OIConstants.kDriverControlBoard);

    // Subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem(m_driverController, m_controlBoard);
    private final ColorWheelSubsystem m_colorWheelSubsystem = new ColorWheelSubsystem();
    private final InnerIntakeSubsystem m_innerIntakeSubsystem = new InnerIntakeSubsystem();
    private final OuterIntakeSubsystem m_outerIntakeSubsystem= new OuterIntakeSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final TowerSubsystem m_towerSubsystem = new TowerSubsystem();
    private final CameraSubsystem m_cameraSubsystem = new CameraSubsystem();

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    private final Command autonomousCommand = new AutonomousShooterCommand(m_robotDrive, m_shooterSubsystem, m_towerSubsystem);

    public RobotContainer() {
        configureButtonBindings();
        m_robotDrive.setDefaultCommand(new DriveCartesianCommand(m_robotDrive, m_driverController, m_controlBoard));
    }

    public void periodic() {
        m_robotDrive.checkDrive();
    }

    /**
     * <h1>Return the pigeon used on the robot</h1>
     * @return Instance of PigeonIMU
     */
    public PigeonIMU getPigeon() {
        return m_robotDrive.pigeonIMU;
    }

    /**
     * Configure button bindings on the robot
     */
    private void configureButtonBindings() {

        // Driver's Controller
        new JoystickButton(m_driverController, Button.kBumperLeft.value).toggleWhenPressed(new AutomatedIntakeCommand(m_innerIntakeSubsystem, m_outerIntakeSubsystem, m_towerSubsystem, m_colorSensor)); // Intake Balls
        new JoystickButton(m_driverController, Button.kBumperRight.value).toggleWhenPressed(new AutoShootCommand(m_shooterSubsystem, m_towerSubsystem)); // Shoot Balls
        new JoystickButton(m_driverController, Button.kY.value).whileHeld(new RaiseBallCommand(m_towerSubsystem)); // Raise Balls up the tower manually
        new JoystickButton(m_driverController, Button.kX.value).toggleWhenPressed(new ShootBallCommand(m_shooterSubsystem)); // Manually Rev the Ball Shooter
        new JoystickButton(m_driverController, Button.kB.value).whileHeld(new ForwardIntakeCommand(m_outerIntakeSubsystem, m_innerIntakeSubsystem)); // Manually Intake balls
        new JoystickButton(m_driverController, Button.kA.value).whileHeld(new ReverseIntakeCommand(m_outerIntakeSubsystem)); // Reverse the intake in the case that a ball gets caught
        new JoystickButton(m_driverController, Button.kStart.value).whileHeld(new RotateControlPanel(m_colorWheelSubsystem)); // Rotate the control panel

        // Control Board
        new JoystickButton(m_controlBoard, 1).whenPressed(new UseDriveCameraCommand(m_cameraSubsystem)).whenReleased(new UseShooterCameraCommand(m_cameraSubsystem)); // Switch Cameras on the Dashboard
    }

    /**
     * Returns the selected autonomous command;
     * @return command to run autonomously
     */
    public Command getAutoCommand() {
        return new AutonomousShooterCommand(m_robotDrive, m_shooterSubsystem, m_towerSubsystem);
//        return new AutonomousDriveCommand(m_robotDrive);
    }
}