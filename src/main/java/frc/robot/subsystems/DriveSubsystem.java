/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    private final PWMSparkMax m_frontLeft = new PWMSparkMax(DriveConstants.kFrontLeftMotorPort);
    private final PWMSparkMax m_rearLeft = new PWMSparkMax(DriveConstants.kRearLeftMotorPort);
    private final PWMSparkMax m_frontRight = new PWMSparkMax(DriveConstants.kFrontRightMotorPort);
    private final PWMSparkMax m_rearRight = new PWMSparkMax(DriveConstants.kRearRightMotorPort);

    private final PIDController m_frontLeftPIDController = new PIDController(1, 0, 0);
    private final PIDController m_frontRightPIDController = new PIDController(1, 0, 0);
    private final PIDController m_backLeftPIDController = new PIDController(1, 0, 0);
    private final PIDController m_backRightPIDController = new PIDController(1, 0, 0);

    private final AnalogGyro m_gyro = new AnalogGyro(0);

    private final MecanumDrive m_drive = new MecanumDrive(
            m_frontLeft,
            m_rearLeft,
            m_frontRight,
            m_rearRight);

    // The front-left-side drive encoder
    private final Encoder m_frontLeftEncoder =
            new Encoder(DriveConstants.kFrontLeftEncoderPorts[0],
                    DriveConstants.kFrontLeftEncoderPorts[1],
                    DriveConstants.kFrontLeftEncoderReversed);

    // The rear-left-side drive encoder
    private final Encoder m_rearLeftEncoder =
            new Encoder(DriveConstants.kRearLeftEncoderPorts[0],
                    DriveConstants.kRearLeftEncoderPorts[1],
                    DriveConstants.kRearLeftEncoderReversed);

    // The front-right--side drive encoder
    private final Encoder m_frontRightEncoder =
            new Encoder(DriveConstants.kFrontRightEncoderPorts[0],
                    DriveConstants.kFrontRightEncoderPorts[1],
                    DriveConstants.kFrontRightEncoderReversed);

    // The rear-right-side drive encoder
    private final Encoder m_rearRightEncoder =
            new Encoder(DriveConstants.kRearRightEncoderPorts[0],
                    DriveConstants.kRearRightEncoderPorts[1],
                    DriveConstants.kRearRightEncoderReversed);

    /**
     * Returns the angle of the robot as a Rotation2d.
     *
     * @return The angle of the robot.
     */
    public Rotation2d getAngle() {
        // Negating the angle because WPILib gyros are CW positive.
        return Rotation2d.fromDegrees(-m_gyro.getAngle());
    }

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        // Sets the distance per pulse for the encoders
        m_frontLeftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_rearLeftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_frontRightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_rearRightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    }

    /**
     * Set the desired speeds for each wheel.
     *
     * @param speeds The desired wheel speeds.
     */
    public void setSpeeds(MecanumDriveWheelSpeeds speeds) {
        final double frontLeftFeedforward = DriveConstants.kFeedforward.calculate(speeds.frontLeftMetersPerSecond);
        final double frontRightFeedforward = DriveConstants.kFeedforward.calculate(speeds.frontRightMetersPerSecond);
        final double backLeftFeedforward = DriveConstants.kFeedforward.calculate(speeds.rearLeftMetersPerSecond);
        final double backRightFeedforward = DriveConstants.kFeedforward.calculate(speeds.rearRightMetersPerSecond);

        final double frontLeftOutput = m_frontLeftPIDController.calculate(
                m_frontLeftEncoder.getRate(), speeds.frontLeftMetersPerSecond
        );
        final double frontRightOutput = m_frontRightPIDController.calculate(
                m_frontRightEncoder.getRate(), speeds.frontRightMetersPerSecond
        );
        final double backLeftOutput = m_backLeftPIDController.calculate(
                m_rearLeftEncoder.getRate(), speeds.rearLeftMetersPerSecond
        );
        final double backRightOutput = m_backRightPIDController.calculate(
                m_rearRightEncoder.getRate(), speeds.rearRightMetersPerSecond
        );

        m_frontLeft.setVoltage(frontLeftOutput + frontLeftFeedforward);
        m_frontRight.setVoltage(frontRightOutput + frontRightFeedforward);
        m_rearLeft.setVoltage(backLeftOutput + backLeftFeedforward);
        m_rearRight.setVoltage(backRightOutput + backRightFeedforward);
    }

    /**
     * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
     * speeds have no effect on the angular speed.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward/backwards).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot           Angular rate of the robot.
     */
    @SuppressWarnings("ParameterName")
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        var mecanumDriveWheelSpeed = DriveConstants.kDriveKinematics.toWheelSpeeds(
                fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                        xSpeed, ySpeed, rot, getAngle()
                ) : new ChassisSpeeds(xSpeed, ySpeed, rot)
        );
        mecanumDriveWheelSpeed.normalize(DriveConstants.kMaxSpeed);
        setSpeeds(mecanumDriveWheelSpeed);
    }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
    }
}