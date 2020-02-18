/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;

    }

    public static final class DriveConstants {
        // Limit robot speed to 3 meters per second
        public static final double kMaxSpeed = 3.0;

        public static final int kFrontLeftMotorPort = 0;
        public static final int kRearLeftMotorPort = 1;
        public static final int kFrontRightMotorPort = 2;
        public static final int kRearRightMotorPort = 3;

        public static final int[] kFrontLeftEncoderPorts = new int[]{0, 1};
        public static final int[] kRearLeftEncoderPorts = new int[]{2, 3};
        public static final int[] kFrontRightEncoderPorts = new int[]{4, 5};
        public static final int[] kRearRightEncoderPorts = new int[]{5, 6};

        public static final boolean kFrontLeftEncoderReversed = false;
        public static final boolean kRearLeftEncoderReversed = true;
        public static final boolean kFrontRightEncoderReversed = false;
        public static final boolean kRearRightEncoderReversed = true;

        // TODO: Set this distance to the proper value
        // Distance between centers of right and left wheels on robot
        public static final double kTrackWidth = 0.5;

        // TODO: Set this distance to the proper value
        // Distance between centers of front and back wheels on robot
        public static final double kWheelBase = 0.7;

        public static final MecanumDriveKinematics kDriveKinematics =
                new MecanumDriveKinematics(
                        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
                        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

        public static final int kEncoderCPR = 5;
        public static final double kWheelDiameterMeters = 0.1524;
        public static final double kEncoderDistancePerPulse =
                // Assumes the encoders are directly mounted on the wheel shafts
                (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

        public static final boolean kGyroReversed = false;

        // TODO: Set these values using the RobotPy Characterization Toolsuite
        public static final SimpleMotorFeedforward kFeedforward =
                new SimpleMotorFeedforward(1, 0.8, 0.15);

        // TODO: Set these values using the RobotPy Characterization Toolsuite
        public static final double kPFrontLeftVel = 0.5;
        public static final double kPRearLeftVel = 0.5;
        public static final double kPFrontRightVel = 0.5;
        public static final double kPRearRightVel = 0.5;
    }
}