/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private Command m_autoCommand;
    private RobotContainer robotContainer = new RobotContainer();
    public static int numBallsLoadedTower = 0;
    public static boolean towerFull = false;

    @Override
    public void robotInit() {
        robotContainer.getPigeon().setFusedHeading(0);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        towerFull = numBallsLoadedTower == 4;
        SmartDashboard.putNumber("Number of Balls in Robot", numBallsLoadedTower);
        SmartDashboard.putBoolean("Ready to Shoot", towerFull);
        SmartDashboard.putNumber("Ball Count", numBallsLoadedTower);

        SmartDashboard.putNumber("Right.X", robotContainer.m_driverController.getX(GenericHID.Hand.kRight));
        SmartDashboard.putNumber("Right.Y", robotContainer.m_driverController.getY(GenericHID.Hand.kRight));
        SmartDashboard.putNumber("Left.X", robotContainer.m_driverController.getX(GenericHID.Hand.kLeft));
        SmartDashboard.putNumber("Left.Y", robotContainer.m_driverController.getY(GenericHID.Hand.kLeft));
        SmartDashboard.putNumber("Angle", robotContainer.getPigeon().getFusedHeading());
        robotContainer.periodic();

        SmartDashboard.putString("Control Panel Color", DriverStation.getInstance().getGameSpecificMessage());
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