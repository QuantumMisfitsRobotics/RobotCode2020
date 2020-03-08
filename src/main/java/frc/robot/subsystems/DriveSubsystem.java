/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants;
import frc.robot.commands.drive_subsystem.DriveCartesianCommand;

import java.util.List;

public class DriveSubsystem extends SubsystemBase {
    private final CANSparkMax m_frontLeft = new CANSparkMax(DriveConstants.kFrontLeftMotorID, CANSparkMaxLowLevel.MotorType.kBrushed);
    private final CANSparkMax m_rearLeft = new CANSparkMax(DriveConstants.kRearLeftMotorID, CANSparkMaxLowLevel.MotorType.kBrushed);
    private final CANSparkMax m_frontRight = new CANSparkMax(DriveConstants.kFrontRightMotorID, CANSparkMaxLowLevel.MotorType.kBrushed);
    private final CANSparkMax m_rearRight = new CANSparkMax(DriveConstants.kRearRightMotorID, CANSparkMaxLowLevel.MotorType.kBrushed);

    private List<CANSparkMax> controllers = List.of(m_frontLeft, m_rearLeft, m_frontRight, m_rearRight);

    private final MecanumDrive m_drive = new MecanumDrive(m_frontLeft, m_rearLeft, m_frontRight, m_rearRight);

    // The front-left-side drive encoder
    private final Encoder m_frontLeftEncoder = new Encoder(
                    DriveConstants.kFrontLeftEncoderPorts[0],
                    DriveConstants.kFrontLeftEncoderPorts[1],
                    DriveConstants.kFrontLeftEncoderReversed
    );

    // The rear-left-side drive encoder
    private final Encoder m_rearLeftEncoder = new Encoder(
                    DriveConstants.kRearLeftEncoderPorts[0],
                    DriveConstants.kRearLeftEncoderPorts[1],
                    DriveConstants.kRearLeftEncoderReversed
    );

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

    // The gyro sensor
    public final PigeonIMU pigeonIMU = new PigeonIMU(12);

    // Odometry class for tracking robot pose
    MecanumDriveOdometry m_odometry =
            new MecanumDriveOdometry(DriveConstants.kDriveKinematics, getAngle());

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem(XboxController controller, Joystick controlBoard) {

        // Sets the distance per pulse for the encoders
        m_frontLeftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_rearLeftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_frontRightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_rearRightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);

        controllers.forEach((CANSparkMax max) -> max.setInverted(false));

        setDefaultCommand(new DriveCartesianCommand(this, controller, controlBoard));
    }

    /**
     * Returns the angle of the robot as a Rotation2d.
     *
     * @return The angle of the robot.
     */
    public Rotation2d getAngle() {
        // Negating the angle because WPILib gyros are CW positive.
        return Rotation2d.fromDegrees(pigeonIMU.getFusedHeading() * (DriveConstants.kGyroReversed ? 1.0 : -1.0));
    }

    @Override
    public void periodic() {
        // Update the odometry in the periodic block
        m_odometry.update(getAngle(),
                new MecanumDriveWheelSpeeds(
                        m_frontLeftEncoder.getRate(),
                        m_rearLeftEncoder.getRate(),
                        m_frontRightEncoder.getRate(),
                        m_rearRightEncoder.getRate()));
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        m_odometry.resetPosition(pose, getAngle());
    }

    /**
     * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
     * speeds have no effect on the angular speed.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward/backwards).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot           Angular rate of the robot.
     * @param fieldRelative Whether the provided x and y speeds are relative to the field.
     */
    @SuppressWarnings("ParameterName")
    public void drive(double ySpeed, double xSpeed, double rot, boolean fieldRelative) {
        if (fieldRelative) {
            m_drive.driveCartesian(xSpeed, ySpeed, rot, pigeonIMU.getFusedHeading());
        } else {
            m_drive.driveCartesian(xSpeed, ySpeed, rot);
        }

    }

    /**
     * Sets the front left drive SpeedController to a voltage.
     */
    public void setDriveSpeedControllersVolts(MecanumDriveMotorVoltages volts) {
        m_frontLeft.setVoltage(volts.frontLeftVoltage);
        m_rearLeft.setVoltage(volts.rearLeftVoltage);
        m_frontRight.setVoltage(volts.frontRightVoltage);
        m_rearRight.setVoltage(volts.rearRightVoltage);
    }


    /**
     * Resets the drive encoders to currently read a position of 0.
     */
    public void resetEncoders() {
        m_frontLeftEncoder.reset();
        m_rearLeftEncoder.reset();
        m_frontRightEncoder.reset();
        m_rearRightEncoder.reset();
    }

    /**
     * Gets the front left drive encoder.
     *
     * @return the front left drive encoder
     */

    public Encoder getFrontLeftEncoder() {
        return m_frontLeftEncoder;
    }

    /**
     * Gets the rear left drive encoder.
     *
     * @return the rear left drive encoder
     */

    public Encoder getRearLeftEncoder() {
        return m_rearLeftEncoder;
    }

    /**
     * Gets the front right drive encoder.
     *
     * @return the front right drive encoder
     */

    public Encoder getFrontRightEncoder() {
        return m_frontRightEncoder;
    }

    /**
     * Gets the rear right drive encoder.
     *
     * @return the rear right encoder
     */

    public Encoder getRearRightEncoder() {
        return m_rearRightEncoder;
    }

    /**
     * Gets the current wheel speeds.
     *
     * @return the current wheel speeds in a MecanumDriveWheelSpeeds object.
     */

    public MecanumDriveWheelSpeeds getCurrentWheelSpeeds() {
        return new MecanumDriveWheelSpeeds(m_frontLeftEncoder.getRate(),
                m_rearLeftEncoder.getRate(),
                m_frontRightEncoder.getRate(),
                m_rearRightEncoder.getRate());
    }


    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        pigeonIMU.setFusedHeading(0);
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(pigeonIMU.getFusedHeading(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    }

    public double getRawHeading() {
        return pigeonIMU.getFusedHeading();
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return pigeonIMU.getCompassHeading() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    }

    public void stopMotors() {
        m_drive.stopMotor();
    }

    public void checkDrive() {
        MecanumDrive.checkMotors();
    }
}