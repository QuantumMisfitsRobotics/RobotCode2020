/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.LowerElevatorCommand;
import frc.robot.commands.LowerWinchCommand;
import frc.robot.commands.RaiseElevatorCommand;
import frc.robot.commands.RaiseWinchCommand;

public class RobotContainer
{
    public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

    private final RaiseElevatorCommand raiseElevatorCommand = new RaiseElevatorCommand();
    private final LowerElevatorCommand lowerElevatorCommand = new LowerElevatorCommand();
    private final RaiseWinchCommand raiseWinchCommand = new RaiseWinchCommand();
    private final LowerWinchCommand lowerWinchCommand = new LowerWinchCommand();

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        final int pov = m_driverController.getPOV();
        if (pov < 30 || pov > 330) {
            raiseElevatorCommand.schedule();
        } else if (pov > 60 && pov < 120) {
            raiseWinchCommand.schedule();
        }else if (pov > 150 && pov < 210) {
            lowerElevatorCommand.schedule();
        } else if (pov < 300 && pov > 240) {
            lowerWinchCommand.schedule();
        }
    }
}