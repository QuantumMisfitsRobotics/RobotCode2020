/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.RaiseBallsCommand;
import frc.robot.commands.ShootBallForwardCommand;
import frc.robot.commands.StartInnerIntakeCommand;
import frc.robot.commands.StartOuterIntakeCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

    private final DriveSubsystem m_robotDrive = new DriveSubsystem();


    public RobotContainer() {
        configureButtonBindings();   
    }

    private void configureButtonBindings() {
        // Drive at half speed when the right bumper is held
        new JoystickButton(m_driverController, Button.kBumperRight.value)
                .whenPressed(() -> m_robotDrive.setMaxOutput(0.5))
                .whenReleased(() -> m_robotDrive.setMaxOutput(1));

        new JoystickButton(m_driverController, Button.kBumperLeft.value).whileHeld(new ShootBallForwardCommand());
        new JoystickButton(m_driverController, Button.kY.value).whileHeld(new ParallelCommandGroup(
                new StartOuterIntakeCommand(),
                new StartInnerIntakeCommand()
        ));

        new JoystickButton(m_driverController, Button.kX.value).whileHeld(new RaiseBallsCommand());


    }
}