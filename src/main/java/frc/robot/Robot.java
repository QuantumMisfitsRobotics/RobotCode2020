/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutonomousDriveForwardCommand;

public class Robot extends TimedRobot {
    private AutonomousDriveForwardCommand m_autoCommand;
    private RobotContainer robotContainer = new RobotContainer();
    public static int numBallsLoadedTower = 0;
    public static boolean towerFull = false;

    @Override
    public void robotInit() { }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        towerFull = numBallsLoadedTower == 4;
        SmartDashboard.putNumber("Number of Balls in Robot", numBallsLoadedTower);
        SmartDashboard.putBoolean("Ready to Shoot", towerFull);
        SmartDashboard.putNumber("Ball Count", numBallsLoadedTower);
        robotContainer.periodic();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void disabledPeriodic() { }

    @Override
    public void autonomousInit() {
        robotContainer.getPigeon().setFusedHeading(0); // Setup field oriented driving
        m_autoCommand = robotContainer.getAutoCommand();

        if (m_autoCommand != null) {
            m_autoCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopInit() {
        if (m_autoCommand != null) {
            m_autoCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() { }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }
}