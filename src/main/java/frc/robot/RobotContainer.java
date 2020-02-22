/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ShootBallBackwardCommand;
import frc.robot.commands.ShootBallForwardCommand;

public class RobotContainer
{
    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

    private final ShootBallForwardCommand m_shootBallForwardCommand = new ShootBallForwardCommand();
    private final ShootBallBackwardCommand m_shootBallBackwardCommand = new ShootBallBackwardCommand();

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new JoystickButton(m_driverController, XboxController.Button.kBumperLeft.value)
                .whenPressed(m_shootBallForwardCommand);
    }
}