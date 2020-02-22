/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.StartInnerIntakeCommand;
import frc.robot.commands.StopInnerIntakeCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

    StartInnerIntakeCommand startInnerIntakeCommand = new StartInnerIntakeCommand();
    StopInnerIntakeCommand stopInnerIntakeCommand = new StopInnerIntakeCommand();

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new JoystickButton(m_driverController, XboxController.Button.kBumperLeft.value).whenPressed(startInnerIntakeCommand);
    }
}